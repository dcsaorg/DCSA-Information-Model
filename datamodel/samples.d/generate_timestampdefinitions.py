#!/usr/bin/python3
import csv
from collections import defaultdict, namedtuple
from itertools import product
import os
import sys
from typing import Optional, List, Dict, Iterable, Set, Union, FrozenSet


def declare_timestamps():
    """Function that generates all the timestamps

    Timestamps are added to the CSV in the order this function outputs them.

    Use generic_xty_timestamps(...) for "ETA-Berth" and similar timestamps
    that follow this pattern.

    Use generate_special_timestamp(...) for "EOSP", "AT-All Fast" and similar
    special-case timestamps.
    """
    # Add XTA-Berth
    generic_xty_timestamps(
        PUBLISHER_PATTERN_CA2TR,
        EST_REQ_PLN,
        UNCANCELABLE_ARRI_OPERATIONS_EVENT_TYPE_CODE,
        ['BRTH'],
        [NULL_VALUE, 'INBD'],
        "Berth Arrival Planning",
        'jit1_0',
        need_vessel_position_for=EST_PLN,
        need_event_location_for=REQ_PLN_ACT,
    )

    # ETS-Cargo Ops
    xty_service_timestamps(
        EST_REQ_PLN,  # ATS-Cargo Ops comes later (different port call part)
        ['CRGO'],
        [NULL_VALUE, 'ALGS'],
        "Services Planning",
        'jit1_1',
        # We only generated the STRT here, CMPL (and CANC) comes later
        operations_event_type_codes=['STRT'],
        need_vessel_position_for=EST_PLN,
        need_event_location_for=REQ_PLN_ACT,
    )

    # ETS-Towage (Inbound)
    xty_service_timestamps(
        EST_REQ_PLN,  # ATS-Cargo Ops comes later (different port call part)
        ['TOWG'],
        [NULL_VALUE, 'INBD'],
        "Services Planning",
        'jit1_0',
        include_phase_in_name=True,
        is_cancelable=True,
        need_vessel_position_for=EST_PLN,
        need_event_location_for=REQ_PLN_ACT,
    )

    # ETS-Pilotage (Inbound)
    xty_service_timestamps(
        EST_REQ_PLN,  # ATS-Cargo Ops comes later (different port call part)
        ['PILO'],
        [NULL_VALUE, 'INBD'],
        "Services Planning",
        'jit1_0',
        include_phase_in_name=True,
        is_cancelable=True,
        need_vessel_position_for=EST_PLN,
        need_event_location_for=REQ_PLN_ACT,
    )

    # ETS-Mooring (Inbound) UC 19 - 24
    xty_service_timestamps(
        EST_REQ_PLN,  # ATS-Cargo Ops comes later (different port call part)
        ['MOOR'],
        [NULL_VALUE, 'INBD'],
        "Services Planning",
        'jit1_2',
        include_phase_in_name=True,
        is_cancelable=True,
        need_vessel_position_for=EST_PLN,
        need_event_location_for=REQ_PLN_ACT,
    )

    # Bunkering UC 25 - 30
    xty_service_timestamps(
        EST_REQ_PLN,
        ['BUNK'],
        [NULL_VALUE, 'ALGS'],
        "Services Planning",
        'jit1_2',
        include_phase_in_name=False,
        is_cancelable=True,
        need_event_location_for=ALL_EVENT_CLASSIFIER_CODES,
    )

    # ETA PBP UC 31-33 + 35
    generic_xty_timestamps(
        PUBLISHER_PATTERN_CA2ATH,
        ALL_EVENT_CLASSIFIER_CODES,
        ['ARRI'],
        ['PBPL'],
        [NULL_VALUE, 'INBD'],
        "Pilot Boarding Place Arrival Planning And Execution, Berth Arrival Execution",
        'jit1_0',
        need_event_location_for=ALL_EVENT_CLASSIFIER_CODES,
    )

    # EOSP UC 34
    generate_special_timestamp(
        'ESOP',
        PUBLISHER_PATTERN_CA2ATH,
        'ARRI',
        'BRTH',
        'INBD',
        'Pilot Boarding Place Arrival Planning And Execution, Berth Arrival Execution',
        'jit1_1',
        need_vessel_position=True,  # FIXME: IFS says Optional, we do not support "required" or "absent".
    )

    # ATS-Pilotage (Inbound) UC 36 + 40
    xty_service_timestamps(
        ACT,
        ['PILO'],
        [NULL_VALUE, 'INBD'],
        "Pilot Boarding Place Arrival Planning And Execution, Berth Arrival Execution",
        'jit1_0',
        include_phase_in_name=True,
        is_cancelable=False,
        need_vessel_position_for=ACT,
        need_event_location_for=ACT,
    )

    # ATS-Towage (Inbound) UC 37 + 38
    xty_service_timestamps(
        ACT,
        ['TOWG'],
        [NULL_VALUE, 'INBD'],
        "Pilot Boarding Place Arrival Planning And Execution, Berth Arrival Execution",
        'jit1_1',
        include_phase_in_name=True,
        is_cancelable=False,
        need_vessel_position_for=ACT,
        need_event_location_for=ACT,
    )

    # ATS-Mooring (Inbound) UC 39 + 44
    xty_service_timestamps(
        ACT,
        ['MOOR'],
        [NULL_VALUE, 'INBD'],
        "Pilot Boarding Place Arrival Planning And Execution, Berth Arrival Execution",
        'jit1_2',
        include_phase_in_name=True,
        is_cancelable=False,
        need_event_location_for=ACT,
    )

    # ATA-Berth UC 41
    generic_xty_timestamps(
        as_publisher_patterns(['TR', 'CA', 'AG', 'VSL'], ['ATH']),
        ACT,
        UNCANCELABLE_ARRI_OPERATIONS_EVENT_TYPE_CODE,
        ['BRTH'],
        [NULL_VALUE, 'ALGS'],
        "Pilot Boarding Place Arrival Planning And Execution, Berth Arrival Execution",
        'jit1_0',
    )

    # AT All fast UC 42
    generate_special_timestamp(
        'AT All fast',
        PUBLISHER_PATTERN_CA2ATH,
        'ARRI',
        'BRTH',
        'ALGS',
        "Pilot Boarding Place Arrival Planning And Execution, Berth Arrival Execution",
        'jit1_1',
        'FAST',
        need_vessel_position=False,
    )

    # Gangway Down and Safe UC 43
    generate_special_timestamp(
        'Gangway Down and Safe',
        PUBLISHER_PATTERN_CA2ATH,
        'ARRI',
        'BRTH',
        'ALGS',
        "Pilot Boarding Place Arrival Planning And Execution, Berth Arrival Execution",
        'jit1_1',
        'GWAY',
        need_vessel_position=False,
    )

    # Vessel Readiness for Cargo ops UC 45
    generate_special_timestamp(
        'Vessel Readiness for Cargo operations [JIT 1.2]',
        PUBLISHER_PATTERN_CA2ATH,
        'ARRI',
        'BRTH',
        'ALGS',
        "Start Cargo Operations And Services",
        'jit1_2',
        'VRDY',
        need_vessel_position=False,
        need_event_location=True,
    )
    generate_special_timestamp(
        'Vessel Readiness for Cargo operations [JIT 1.1]',
        PUBLISHER_PATTERN_CA2ATH,
        'ARRI',
        'BRTH',
        'ALGS',
        "Start Cargo Operations And Services",
        'jit1_1',
        'SAFE',
        need_vessel_position=False,
        need_event_location=True,
    )

    # ATS Cargo Ops UC 46
    xty_service_timestamps(
        ACT,
        ['CRGO'],
        [NULL_VALUE, 'ALGS'],
        "Start Cargo Operations And Services",
        'jit1_0',
        # We only generated the STRT here, CMPL (and CANC) comes later
        operations_event_type_codes=['STRT'],
        need_event_location_for=ALL_EVENT_CLASSIFIER_CODES,
    )

    # ATS Cargo Ops discharge UC 47
    generate_special_timestamp(
        'ATS cargo ops discharge start',
        PUBLISHER_PATTERN_TR2CA,
        'STRT',
        'BRTH',
        'ALGS',
        "Start Cargo Operations And Services",
        'jit1_2',
        'DCRO',
        need_vessel_position=False,
        need_event_location=True,
    )

    #ATS Cargo Ops discharge completed UC 48
    generate_special_timestamp(
        'ATS cargo ops discharge completed',
        PUBLISHER_PATTERN_TR2CA,
        'CMPL',
        'BRTH',
        'ALGS',
        "Start Cargo Operations And Services",
        'jit1_2',
        'DCRO',
        need_vessel_position=False,
        need_event_location=True,
    )

    #ATS Cargo Ops Load completed UC 49
    generate_special_timestamp(
        'ATS cargo ops Load',
        PUBLISHER_PATTERN_TR2CA,
        'STRT',
        'BRTH',
        'ALGS',
        "Start Cargo Operations And Services",
        'jit1_2',
        'LCRO',
        need_vessel_position=False,
        need_event_location=True,
    )

    # ETC Cargo Ops UC 50
    xty_service_timestamps(
        EST,
        ['CRGO'],
        ['ALGS'],
        "Start Cargo Operations And Services",
        'jit1_2',
        operations_event_type_codes=['CMPL'],
        need_event_location_for=ALL_EVENT_CLASSIFIER_CODES,
        is_cancelable=True,
    )

    # ETC Cargo Ops UC 51 + 52
    xty_service_timestamps(
        REQ_PLN,
        ['CRGO'],
        ['ALGS'],
        "Start Cargo Operations And Services",
        'jit1_0',
        operations_event_type_codes=['CMPL'],  # UC 50 handles CANC
        need_event_location_for=ALL_EVENT_CLASSIFIER_CODES,
    )

    # ATS UC 53
    xty_service_timestamps(
        ACT,
        ['BUNK'],
        [NULL_VALUE, 'ALGS'],
        "Start Cargo Operations And Services",
        'jit1_1',
        include_phase_in_name=False,
        operations_event_type_codes=['STRT'],
        need_event_location_for=ALL_EVENT_CLASSIFIER_CODES,
    )

    # Add XTD-Berth except ATD-Berth (different part and phase) UC 54 + 67 + 68
    generic_xty_timestamps(
        PUBLISHER_PATTERN_CA2ATH,
        EST_REQ_PLN,
        UNCANCELABLE_DEPA_OPERATIONS_EVENT_TYPE_CODE,
        ['BRTH'],
        [NULL_VALUE, 'ALGS'],
        "Port Departure Planning And Services Completion",
        'jit1_0',
        need_event_location_for=ALL_EVENT_CLASSIFIER_CODES,
    )

    #Pilotage UC 55 - 57 + 61 - 63
    xty_service_timestamps(
        EST_REQ_PLN,
        ['PILO'],
        ['OUTB', 'SHIF'],
        "Port Departure Planning And Services Completion",
        'jit1_1',
        include_phase_in_name=True,
        is_cancelable=True,
        need_event_location_for=EST_REQ_PLN,
    )

    #Towage UC 58 - 60 + UC 64 - 66
    xty_service_timestamps(
        EST_REQ_PLN,
        ['TOWG'],
        ['OUTB', 'SHIF'],
        "Port Departure Planning And Services Completion",
        'jit1_1',
        include_phase_in_name=True,
        is_cancelable=True,
        need_event_location_for=EST_REQ_PLN,
    )

    # ATC Bunkering UC 69
    xty_service_timestamps(
        ACT,
        ['BUNK'],
        [NULL_VALUE, 'ALGS'],
        "Port Departure Planning And Services Completion",
        'jit1_1',
        operations_event_type_codes=['CMPL'],
        need_event_location_for=ALL_EVENT_CLASSIFIER_CODES,
    )

    #ATC Cargo Ops Load UC 70
    generate_special_timestamp(
        'ATC cargo ops Load',
        PUBLISHER_PATTERN_TR2CA,
        'CMPL',
        'BRTH',
        'ALGS',
        "Port Departure Planning And Services Completion",
        'jit1_2',
        'LCRO',
        need_vessel_position=False,
        need_event_location=True,
    )

    # ATC Cargo Ops UC 71
    xty_service_timestamps(
        ACT,
        ['CRGO'],
        ['ALGS'],
        "Port Departure Planning And Services Completion",
        'jit1_2',
        # Reminder: CANC also applies to "STRT"
        operations_event_type_codes=['CMPL', 'CANC'],
        need_event_location_for=ALL_EVENT_CLASSIFIER_CODES,
        is_cancelable=False,  # Generated by UC 50
    )

    #Mooring UC 72 - 77
    xty_service_timestamps(
        EST_REQ_PLN,
        ['MOOR'],
        ['OUTB'],
        "Port Departure Planning And Services Completion",
        'jit1_2',
        include_phase_in_name=True,
        is_cancelable=True,
        need_event_location_for=EST_REQ_PLN,
    )

    #ATC Lashing UC 78
    generate_special_timestamp(
        'ATC Lashing',
        as_publisher_patterns(['MOR'], ['CA']),
        'CMPL',
        'BRTH',
        'ALGS',
        "Port Departure Planning And Services Completion",
        'jit1_1',
        'LASH',
        need_vessel_position=False,
        need_event_location=True,
    )

    #Terminal Ready for vessel departure UC 79
    generate_special_timestamp(
        'Terminal Ready for vessel departure',
        PUBLISHER_PATTERN_TR2CA,
        'DEPA',
        'BRTH',
        'ALGS',
        "Port Departure Execution",
        'jit1_1',
        'SAFE',
        need_vessel_position=False,
        need_event_location=True,
    )

    #Vessel ready to sail UC 80
    generate_special_timestamp(
        'Vessel Ready to sail [JIT 1.2]',
        PUBLISHER_PATTERN_CA2TR,
        'DEPA',
        'BRTH',
        'ALGS',
        "Port Departure Execution",
        'jit1_2',
        'VRDY',
        need_vessel_position=False,
        need_event_location=True,
    )
    generate_special_timestamp(
        'Vessel Ready to sail [JIT 1.1]',
        PUBLISHER_PATTERN_CA2TR,
        'DEPA',
        'BRTH',
        'ALGS',
        "Port Departure Execution",
        'jit1_1',
        'SAFE',
        need_vessel_position=False,
        need_event_location=True,
    )

    #ATS Mooring UC 81 + ATC Mooring UC 84
    xty_service_timestamps(
        ACT,
        ['MOOR'],
        ['OUTB'],
        "Port Departure Execution",
        'jit1_2',
        include_phase_in_name=True,
        is_cancelable=False,
        need_event_location_for=ACT,
    )

    # ATD Berth UC 82
    generic_xty_timestamps(
        PUBLISHER_PATTERN_CA2ATH,
        ACT,
        ['DEPA'],
        ['BRTH'],
        [NULL_VALUE, 'OUTB'],
        "Port Departure Execution",
        'jit1_0',
        need_event_location_for=ALL_EVENT_CLASSIFIER_CODES,
    )

    # ATS Pilotage UC 83 + ATC Pilotage UC 87
    xty_service_timestamps(
        ACT,
        ['PILO'],
        ['OUTB', 'SHIF'],
        "Port Departure Execution",
        'jit1_1',
        include_phase_in_name=True,
        is_cancelable=False,
        need_event_location_for=ACT,
    )

    #ATS Towage UC 85 + ATC Towage UC 86
    xty_service_timestamps(
        ACT,
        ['TOWG'],
        ['OUTB', 'SHIF'],
        "Port Departure Execution",
        'jit1_1',
        include_phase_in_name=True,
        is_cancelable=False,
        need_event_location_for=ACT,
    )

    #SOSP UC 88
    generate_special_timestamp(
        'SOSP',
        PUBLISHER_PATTERN_CA2ATH,
        'DEPA',
        NULL_VALUE,
        'OUTB',
        "Port Departure Execution",
        'jit1_1',
        NULL_VALUE,
        need_vessel_position=False,
        need_event_location=False,
    )

    # Anchorage UC 89 - UC 96
    generic_xty_timestamps(
        PUBLISHER_PATTERN_CA2ATH,
        EST_PLN_REQ_ACT,
        ['ARRI', 'DEPA'],
        ['ANCH'],
        [NULL_VALUE],
        "Other Services - Anchorage Planning And Execution",
        'jit1_2',
        include_phase_in_name=True,
        need_event_location_for=ALL_EVENT_CLASSIFIER_CODES,
        need_vessel_position_for=ALL_EVENT_CLASSIFIER_CODES
    )

    #ATS Anchorage OPS UC 97
    generate_special_timestamp(
        'ATS Anchorage Ops',
        PUBLISHER_PATTERN_CA2ATH,
        'ARRI',
        'ANCH',
        NULL_VALUE,
        "Other Services - Anchorage Planning And Execution",
        'jit1_2',
        'ANCO',
        need_vessel_position=True, #FIXME need to support optional
        need_event_location=True,
    )

    #ATC Anchorage OPS UC 98
    generate_special_timestamp(
        'ATC Anchorage Ops',
        PUBLISHER_PATTERN_CA2ATH,
        'DEPA',
        'ANCH',
        NULL_VALUE,
        "Other Services - Anchorage Planning And Execution",
        'jit1_2',
        'ANCO',
        need_vessel_position=True, #FIXME need to support optional
        need_event_location=True,
    )

    #Sludge UC 99 106
    generic_xty_timestamps(
        as_publisher_patterns(['CA'], ['SVP']),
        EST_PLN_REQ_ACT,
        ['STRT', 'CMPL'],
        ['BRTH'],
        [NULL_VALUE],
        "Other Services - Sludge Planning And Execution",
        'jit1_2',
        ['SLUG'],
        need_vessel_position_for=EST_PLN_REQ_ACT,
        need_event_location_for=EST_PLN_REQ_ACT,
    )

    #Shore power UC 107 + 108
    xty_service_timestamps(
        ACT,
        ['SHPW'],
        ['ALGS'],
        "Other Services - Shore Power Execution",
        'jit1_2',
        is_cancelable=False,
        need_event_location_for=ACT,
    )

    # The standard does not declare these, but
    ignore_nonexistent_timestamps(
        'ETS-Shore Power',
        'RTS-Shore Power',
        'PTS-Shore Power',
        'ETC-Shore Power',
        'RTC-Shore Power',
        'PTC-Shore Power',
    )

    #Omit port call UC 110
    generate_special_timestamp(
        'Omit Port Call',
        PUBLISHER_PATTERN_CA2ATH,
        'OMIT',
        NULL_VALUE,
        NULL_VALUE,
        "OMIT Port Call Or Cancel A Service",
        'jit1_2',
        need_vessel_position=True, #FIXME need to support optional
    )


NULL_VALUE = "null"

COLUMN_ID = 'timestampID'
COLUMN_TIMESTAMP_TYPE_NAME = 'timestampTypeName'
COLUMN_EVENT_CLASSIFIER_CODE = 'eventClassifierCode'
COLUMN_ACCEPT_TS = 'acceptTimestampDefinition'
COLUMN_REJECT_TS = 'rejectTimestampDefinition'

FIELDS_ORDER = [
    'timestampID',
    COLUMN_TIMESTAMP_TYPE_NAME,
    #  'publisherLinkID',
    #  'primaryReceiver',
    COLUMN_EVENT_CLASSIFIER_CODE,
    'operationsEventTypeCode',
    'portCallPhaseTypeCode',
    'portCallServiceTypeCode',
    'facilityTypeCode',
    'portCallPart',
    'isBerthLocationNeeded',
    'isPBPLocationNeeded',
    'isAnchorageLocationNeeded',
    'isTerminalNeeded',
    'isVesselPositionNeeded',
    'providedInStandard',
    COLUMN_ACCEPT_TS,
    COLUMN_REJECT_TS,
    'negotiationCycle',
]

TS_ID_COUNTER = 0
UNNAMED = object()

ALL_EVENT_CLASSIFIER_CODES = [
    'EST',
    'REQ',
    'PLN',
    'ACT',
]
CANCELABLE_SERVICE_OPERATIONS_EVENT_TYPE_CODE = [
    'STRT',
    'CMPL',
    'CANC',
]
UNCANCELABLE_SERVICE_OPERATIONS_EVENT_TYPE_CODE = [
    'STRT',
    'CMPL',
]

# ETA-Anchorage
CANCELABLE_ARRI_DEPA_OPERATIONS_EVENT_TYPE_CODE = [
    'ARRI',
    'DEPA',
    'CANC',
]
# ETA-Berth / ETA-PBPL uses this
UNCANCELABLE_ARRI_OPERATIONS_EVENT_TYPE_CODE = [
    'ARRI',
]
# ETD-Berth uses this (ETA and ETD cannot share the same list as
# they have different phase type codes)
UNCANCELABLE_DEPA_OPERATIONS_EVENT_TYPE_CODE = [
    'DEPA',
]

PHASE_TYPE_CODE2NAME_STEM = {
    NULL_VALUE: UNNAMED,
    'INBD': 'Inbound',
    # No timestamp uses Alongside in their name currently, so mark it UNNAMED for now
    # to catch bugs.
    'ALGS': UNNAMED,
    'SHIF': 'Shifting',
    'OUTB': 'Outbound',
}
FACILITY_TYPE_CODE2NAME_STEM = {
    NULL_VALUE: UNNAMED,
    'BRTH': 'Berth',
    'PBPL': 'PBPL',
    'ANCH': 'Anchorage',
}
CARRIER_ROLES = ['CA', 'AG', 'VSL']
VALID_PUBLISHER_ROLES = frozenset({
    'CA',
    'AG',
    'VSL',
    'TR',
    'ATH',
    'PLT',
    'TWG',
    'MOR',
    'LSH',
    'SLU',
    'BUK',
    'SVP',
})
TS_NOT_IN_STANDARD = set()

PublisherPattern = namedtuple('PublisherPattern', ['publisher_role', 'primary_receiver_role'])


def as_publisher_patterns(publisher_roles_for_est, primary_receivers_for_est):
    return [PublisherPattern(publisher_role=p, primary_receiver_role=r)
            for p, r in product(publisher_roles_for_est, primary_receivers_for_est)]


class ServiceTypeInfo:

    def __init__(self, name, facility_type_codes, publisher_patterns):
        self.name = name
        self.facility_type_codes = facility_type_codes
        self.publisher_patterns = publisher_patterns


SERVICE_TYPE_CODE2INFO = {
    # FIXME: Pull the name from the data file
    #FIXME: need to support multiple facility types e.g. Sludge and Bunkering
    'CRGO': ServiceTypeInfo('Cargo Ops', ['BRTH'], as_publisher_patterns(['TR'], CARRIER_ROLES)),
    'DCRO': ServiceTypeInfo('Cargo Ops Discharge', ['BRTH'], as_publisher_patterns(['TR'], CARRIER_ROLES)),
    'LCRO': ServiceTypeInfo('Cargo Ops Load', ['BRTH'], as_publisher_patterns(['TR'], CARRIER_ROLES)),
    'LASH': ServiceTypeInfo('Lashing', ['BRTH'], as_publisher_patterns(['LSH'], CARRIER_ROLES)),
    'MOOR': ServiceTypeInfo('Mooring', ['BRTH'], as_publisher_patterns(['MOR'], CARRIER_ROLES)),
    'BUNK': ServiceTypeInfo('Bunkering', ['BRTH'], as_publisher_patterns(['BUK'], CARRIER_ROLES)),
    #FIXME: Pilo has different facility type codes depending on started or completed
    'PILO': ServiceTypeInfo('Pilotage', ['BRTH'], as_publisher_patterns(['PLT'], CARRIER_ROLES)),
    'TOWG': ServiceTypeInfo('Towage', ['BRTH'], as_publisher_patterns(['TWG'], CARRIER_ROLES)),
    'SHPW': ServiceTypeInfo('Shore Power', ['BRTH'], as_publisher_patterns(['SVP'], CARRIER_ROLES)),
    'ANCO': ServiceTypeInfo('Anchorage Operations', ['ANCH'], as_publisher_patterns(['SVP'], CARRIER_ROLES)),
    'SLUG': ServiceTypeInfo('Sludge', ['BRTH'], as_publisher_patterns(['SVP'], CARRIER_ROLES)),
    # Services that do not have a clear-cut service name (including "null")
    'SAFE': UNNAMED,
    'FAST': UNNAMED,
    'GWAY': UNNAMED,
    'VRDY': UNNAMED,
    NULL_VALUE: UNNAMED,  # "null" is not translated to None
}
REQ_PLN_ACT = [
    'REQ',
    'PLN',
    'ACT',
]
ACT = ['ACT']
EST = ['EST']
REQ_PLN = [
    'REQ',
    'PLN',
]
EST_REQ_PLN = [
    'EST',
    'REQ',
    'PLN',
]
EST_PLN = [
  'EST',
  'PLN',
]
EST_PLN_REQ_ACT = [
    'EST',
    'PLN',
    'REQ',
    'ACT',
]
VALID_JIT_VERSIONS = [
    'jit1_0',
    'jit1_1',
    'jit1_2',
]
VALID_OPERATIONS_EVENT_TYPE_CODES = set()
VALID_PORT_CALL_PARTS = frozenset({
    "Berth Arrival Planning",
    "Services Planning",
    "Pilot Boarding Place Arrival Planning And Execution, Berth Arrival Execution",
    "Start Cargo Operations And Services",
    "Port Departure Planning And Services Completion",
    "Port Departure Execution",
    "Other Services - Anchorage Planning And Execution",
    "Other Services - Sludge Planning And Execution",
    "Other Services - Shore Power Execution",
    "OMIT Port Call Or Cancel A Service"
})


class PublisherPatternDefaultDict(defaultdict):

    def __missing__(self, key):
        # ('CA', 'TR') -> CA2TR
        v = "2".join(key)
        self[key] = v
        return v


ALL_TS = {}
TS_PUBLISHER_PATTERN_LINKS = set()
USED_PUBLISHER_PATTERNS = PublisherPatternDefaultDict()
PUBLISHER_PATTERN_CA2TR = as_publisher_patterns(CARRIER_ROLES, ['TR'])

# Opposite order of PUBLISHER_PATTERN_CA2TR
PUBLISHER_PATTERN_TR2CA = [
    PublisherPattern(publisher_role=r, primary_receiver_role=p)
    for p, r in PUBLISHER_PATTERN_CA2TR
]


PUBLISHER_PATTERN_CA2ATH = as_publisher_patterns(CARRIER_ROLES, ['ATH'])


def ignore_nonexistent_timestamps(*timestamp_names):
    """Useful for silencing irrelevant warnings

    E.g. There is no ETS-Shore Power (etc.), but the automatic checking will assume
    there they should exist because ATS-Shore Power exists.  Calling this method
    can be used to hide these warnings (so we do not overlook real problems)
    """
    global TS_NOT_IN_STANDARD
    TS_NOT_IN_STANDARD.update(timestamp_names)


def xty_service_timestamps(
        event_classifier_codes: Iterable[str],
        port_call_service_type_codes: Iterable[str],
        port_call_phase_type_codes: Iterable[str],
        port_call_part: str,
        provided_in_standard: str,
        include_phase_in_name: bool = False,
        need_vessel_position_for: Union[FrozenSet, Set, List] = frozenset(),
        need_event_location_for: Union[FrozenSet, Set, List] = frozenset(),
        operations_event_type_codes=None,
        is_cancelable: bool = True,  # FIXME Cancel events must always have the eventClassifierCode=ACT
        backwards_compat_phase_code: bool = True,
):
    if operations_event_type_codes is None:
        if is_cancelable:
            operations_event_type_codes = CANCELABLE_SERVICE_OPERATIONS_EVENT_TYPE_CODE
        else:
            operations_event_type_codes = UNCANCELABLE_SERVICE_OPERATIONS_EVENT_TYPE_CODE
    for port_call_service_type_code in sorted(port_call_service_type_codes):
        service_info = SERVICE_TYPE_CODE2INFO[port_call_service_type_code]
        _ensure_named(service_info, 'portCallServiceTypeCode', port_call_service_type_code)
        generic_xty_timestamps(
            service_info.publisher_patterns,
            event_classifier_codes,
            operations_event_type_codes,
            service_info.facility_type_codes,
            port_call_phase_type_codes,
            port_call_part,
            provided_in_standard,
            port_call_service_type_codes=[port_call_service_type_code],
            include_phase_in_name=include_phase_in_name,
            need_vessel_position_for=need_vessel_position_for,
            need_event_location_for=need_event_location_for,
            backwards_compat_phase_code=backwards_compat_phase_code,
        )


def generate_special_timestamp(
        timestamp_name: str,
        publisher_pattern: List['PublisherPattern'],
        operations_event_type_code: str,
        facility_type_code: str,
        port_call_phase_type_code: str,
        port_call_part: str,
        provided_in_standard: str,
        port_call_service_type_code: str = NULL_VALUE,
        event_classifier_code: str = 'ACT',
        need_vessel_position: bool = False,
        need_event_location: bool = False,

):
    _ensure_known(provided_in_standard, VALID_JIT_VERSIONS, "providedInStandard")
    _ensure_known(port_call_part, VALID_PORT_CALL_PARTS, "portCallPart")
    _ensure_known(event_classifier_code, ALL_EVENT_CLASSIFIER_CODES, "eventClassifierCoder")
    _ensure_known(operations_event_type_code, VALID_OPERATIONS_EVENT_TYPE_CODES, "operationsEventTypeCode")
    _ensure_known(facility_type_code, FACILITY_TYPE_CODE2NAME_STEM, "facilityTypeCode")
    _ensure_known(port_call_service_type_code, SERVICE_TYPE_CODE2INFO, "portCallServiceTypeCode")
    _ensure_known(port_call_phase_type_code, PHASE_TYPE_CODE2NAME_STEM, "portCallPhaseTypeCode")

    _make_timestamp(
        timestamp_name,
        event_classifier_code,
        operations_event_type_code,
        port_call_phase_type_code,
        port_call_service_type_code,
        facility_type_code,
        port_call_part,
        provided_in_standard,
        publisher_pattern,
        need_vessel_position=need_vessel_position,
        need_event_location=need_event_location,
        is_pattern_timestamp=False
    )


def generic_xty_timestamps(
        initial_publisher_pattern: List['PublisherPattern'],
        event_classifier_codes: Iterable[str],
        operations_event_type_codes: Iterable[str],
        facility_type_codes: Iterable[str],
        port_call_phase_type_codes: Iterable[str],
        port_call_part: str,
        provided_in_standard: str,
        port_call_service_type_codes: Optional[List[str]] = None,
        include_phase_in_name: bool = False,
        need_vessel_position_for: Union[FrozenSet, Set, List] = frozenset(),
        need_event_location_for: Union[FrozenSet, Set, List] = frozenset(),
        backwards_compat_phase_code: bool = True,
):
    _ensure_known(provided_in_standard, VALID_JIT_VERSIONS, "providedInStandard")
    _ensure_known(port_call_part, VALID_PORT_CALL_PARTS, "portCallPart")
    if port_call_service_type_codes is None:
        # Service timestamps can easily use generate_service_timestamps instead, so we default to have this be NULL
        port_call_service_type_codes = [NULL_VALUE]
    for event_classifier_code, operations_event_type_code, facility_type_code, port_call_service_type_code, port_call_phase_type_code in sorted(product(
            event_classifier_codes,
            operations_event_type_codes,
            facility_type_codes,
            port_call_service_type_codes,
            port_call_phase_type_codes
    )):

        _ensure_known(event_classifier_code, ALL_EVENT_CLASSIFIER_CODES, "eventClassifierCoder")
        _ensure_known(operations_event_type_code, VALID_OPERATIONS_EVENT_TYPE_CODES, "operationsEventTypeCode")
        _ensure_known(facility_type_code, FACILITY_TYPE_CODE2NAME_STEM, "facilityTypeCode")
        _ensure_known(port_call_service_type_code, SERVICE_TYPE_CODE2INFO, "portCallServiceTypeCode")
        _ensure_known(port_call_phase_type_code, PHASE_TYPE_CODE2NAME_STEM, "portCallPhaseTypeCode")

        if port_call_service_type_code != NULL_VALUE:
            service_info = SERVICE_TYPE_CODE2INFO[port_call_service_type_code]
            _ensure_named(service_info, 'portCallServiceTypeCode', port_call_service_type_code)
            name_stem = service_info.name
        else:
            name_stem = FACILITY_TYPE_CODE2NAME_STEM[facility_type_code]
            _ensure_named(name_stem, 'facilityTypeCode', facility_type_code)
        if port_call_phase_type_code == NULL_VALUE:
            phase_name_part = ' (<implicit>)' if backwards_compat_phase_code else ''
        elif include_phase_in_name:
            n = PHASE_TYPE_CODE2NAME_STEM[port_call_phase_type_code]
            _ensure_named(n, 'portCallPhaseTypeCode', port_call_phase_type_code)
            phase_name_part = ' (' + n + ')'
        else:
            phase_name_part = ''
        publisher_pattern = initial_publisher_pattern
        if event_classifier_code == 'REQ':
            # In the default pattern, REQ reverses publisher and receiver
            publisher_pattern = [(r, p) for p, r in initial_publisher_pattern]
        elif event_classifier_code == 'ACT':
            # In the default pattern, anyone is allowed to sent ACT
            publisher_pattern = initial_publisher_pattern.copy()
            publisher_pattern.extend((r, p) for p, r in initial_publisher_pattern)
        if operations_event_type_code == 'CANC':
            full_name = ''.join(('Cancel ', event_classifier_code[0], 'T<ALL>-',
                                 name_stem, phase_name_part))
        else:
            full_name = ''.join((event_classifier_code[0], 'T', operations_event_type_code[0], '-',
                                 name_stem, phase_name_part))
        _make_timestamp(
            full_name,
            event_classifier_code,
            operations_event_type_code,
            port_call_phase_type_code,
            port_call_service_type_code,
            facility_type_code,
            port_call_part,
            provided_in_standard,
            publisher_pattern,
            need_vessel_position=event_classifier_code in need_vessel_position_for,
            need_event_location=event_classifier_code in need_event_location_for,
            # 'CANC' does not follow the pattern.
            is_pattern_timestamp=operations_event_type_code != 'CANC'
        )


class Timestamp:

    def __init__(self, row_data, is_pattern_timestamp):
        self.row_data = {n: d for n, d in zip(FIELDS_ORDER, row_data)}
        self.is_pattern_timestamp = is_pattern_timestamp

    @property
    def id(self):
        return self.row_data[COLUMN_ID]

    @property
    def timestamp_name(self):
        return self.row_data[COLUMN_TIMESTAMP_TYPE_NAME]

    @property
    def event_classifier_code(self):
        return self.row_data[COLUMN_EVENT_CLASSIFIER_CODE]

    @property
    def accept_ts(self):
        return self.row_data[COLUMN_ACCEPT_TS]

    @accept_ts.setter
    def accept_ts(self, new_value):
        self.row_data[COLUMN_ACCEPT_TS] = new_value

    @property
    def reject_ts(self):
        return self.row_data[COLUMN_REJECT_TS]

    @reject_ts.setter
    def reject_ts(self, new_value):
        self.row_data[COLUMN_REJECT_TS] = new_value


def _make_timestamp(timestamp_name,
                    event_classifier_code,
                    operations_event_type_code,
                    port_call_phase_type_code,
                    port_call_service_type_code,
                    facility_type_code,
                    port_call_part,
                    provided_in_standard,
                    publisher_pattern,
                    is_pattern_timestamp=False,
                    need_vessel_position=False,
                    need_event_location=False,
                    ):

    if timestamp_name in TS_NOT_IN_STANDARD:
        raise ValueError(f'\"{timestamp_name}\" was created but also marked as a "Do not warn about this TS being missing as the standard does not declare it"')

    ts_fields = [
        timestamp_name,
        timestamp_name,
        # publisher_link_id,  # Publisher Link ID
        event_classifier_code,
        operations_event_type_code,
        port_call_phase_type_code,
        port_call_service_type_code,
        facility_type_code,
        port_call_part,  # Port Call Part
        need_event_location and facility_type_code == 'BRTH',  # isBerthLocationNeeded
        need_event_location and facility_type_code == 'PBPL',  # isPBPLocationNeeded
        need_event_location and facility_type_code == 'ANCH',  # isANCHLocationNeeded
        facility_type_code == 'BRTH',  # isTerminalNeeded
        need_vessel_position,  # isVesselPositionNeeded
        provided_in_standard,  # providedInStandard
        NULL_VALUE,  # acceptTimestampDefinition
        NULL_VALUE,  # rejectTimestampDefinition
        'FIXME: fill out',  # negotiationCycle
    ]
    global FIELDS_ORDER
    assert len(ts_fields) == len(FIELDS_ORDER)
    ts = Timestamp(ts_fields, is_pattern_timestamp)
    if ts.timestamp_name in ALL_TS:
        existing_ts = ALL_TS[ts.timestamp_name]
        print(f"Existing: {existing_ts.row_data}")
        print(f"Duplicate: {ts.row_data}")
        raise ValueError(f"Timestamp {ts.timestamp_name} defined twice!")
    ALL_TS[ts.timestamp_name] = ts
    for publisher_role, primary_receiver in publisher_pattern:
        _ensure_known(publisher_role, VALID_PUBLISHER_ROLES, "publisherRole")
        _ensure_known(primary_receiver, VALID_PUBLISHER_ROLES, "primaryReceiver")
        publisher_link_id = USED_PUBLISHER_PATTERNS[(publisher_role, primary_receiver)]
        TS_PUBLISHER_PATTERN_LINKS.add((ts.id, publisher_link_id))
    return ts


def _ensure_known(v, container, attribute_name):
    if v not in container:
        raise ValueError(f"Unknown value \"{v}\" for \"{attribute_name}\"."
                         f" Please pick one of: {', '.join(sorted(container))}")


def _ensure_named(v, attribute_name, code):
    if v is UNNAMED:
        raise ValueError(f"Attempted to generate name via {attribute_name} {code}, but it does not have a name")


def usage():
    print("Usage: python3 " + os.path.basename(sys.argv[0]) + ".py [path/to/datamodel/samples.d]")
    sys.exit(1)


def load_data_set(filename, column: str):
    with open(filename) as fd:
        csv_reader = csv.DictReader(fd)
        yield from (x[column] for x in csv_reader)


ALREADY_WARNED_MISSING_TIMESTAMP = set()


def _check_missing_related_timestamps(timestamp: Timestamp, all_ts_table: Dict[str, Timestamp]):
    if timestamp.is_pattern_timestamp:
        for code in ALL_EVENT_CLASSIFIER_CODES:
            alternative_name = code[0] + timestamp.timestamp_name[1:]
            if alternative_name == timestamp.timestamp_name:
                continue
            if (alternative_name not in all_ts_table
                    and alternative_name not in TS_NOT_IN_STANDARD
                    and alternative_name not in ALREADY_WARNED_MISSING_TIMESTAMP):
                print(f"W: Possibly missing timestamp \"{alternative_name}\" (discovered via {timestamp.timestamp_name})")
                ALREADY_WARNED_MISSING_TIMESTAMP.add(alternative_name)


def _process_accept_reject_links(timestamp: Timestamp, all_ts_table: Dict[str, Timestamp]):
    if timestamp.is_pattern_timestamp:
        if timestamp.event_classifier_code == 'EST':
            successor_name = 'R' + timestamp.timestamp_name[1:]
            successor_ts = all_ts_table.get(successor_name)
            if successor_ts:
                # Warning if missing ?
                timestamp.accept_ts = successor_ts.id
                timestamp.reject_ts = successor_ts.id
        elif timestamp.event_classifier_code == 'REQ':
            accept_name = 'P' + timestamp.timestamp_name[1:]
            accept_ts = all_ts_table.get(accept_name)
            reject_name = 'E' + timestamp.timestamp_name[1:]
            reject_ts = all_ts_table.get(reject_name)
            if accept_ts:
                # Warning if missing ?
                timestamp.accept_ts = accept_ts.id
            if reject_ts:
                # Warning if missing ?
                timestamp.reject_ts = reject_ts.id


def _load_data_and_self_check(reference_data_dir):
    VALID_OPERATIONS_EVENT_TYPE_CODES.update(
        load_data_set(
            os.path.join(reference_data_dir, 'operationseventtypecodes.csv'),
            'operations_event_type_code',
        ))

    for service_type_code in load_data_set(
            os.path.join(reference_data_dir, 'portcallservicetypecodes.csv'),
            'port_call_service_type_code',
    ):
        if service_type_code not in SERVICE_TYPE_CODE2INFO:
            raise ValueError(f"SERVICE_TYPE_CODE2INFO does not know about {service_type_code}")

    for phase_type_code in load_data_set(
            os.path.join(reference_data_dir, 'portcallphasetypecodes.csv'),
            'port_call_phase_type_code',
    ):
        if phase_type_code not in PHASE_TYPE_CODE2NAME_STEM:
            raise ValueError(f"PHASE_TYPE_CODE2NAME_STEM does not know about {phase_type_code}")


def main():
    if len(sys.argv) > 2:
        usage()
    if len(sys.argv) == 1:
        sample_data_dir = os.path.dirname(os.path.realpath(sys.argv[0]))
    else:
        sample_data_dir = sys.argv[1]
    reference_data_dir = os.path.join(os.path.dirname(sample_data_dir), 'referencedata.d')
    timestamp_def_filename = os.path.join(sample_data_dir, 'timestampdefinitions.csv')
    publisher_pattern_filename = os.path.join(sample_data_dir, 'publisherpattern.csv')
    relation_file_filename = os.path.join(sample_data_dir, 'timestampdefinitions_publisherpattern.csv')

    _load_data_and_self_check(reference_data_dir)

    declare_timestamps()
    if not ALL_TS:
        print("E: No timestamps generated in declare_timestamps()!?")
        sys.exit(1)
    assert bool(USED_PUBLISHER_PATTERNS)
    print(f"Generated {publisher_pattern_filename}")
    _write_csv_from_iter(publisher_pattern_filename,
                         ['Pattern ID', 'Publisher Role', 'Primary Receiver'],
                         # Slightly more complex because we have to align the value order
                         sorted((pattern_id, publisher_role, primary_receiver)
                                for (publisher_role, primary_receiver), pattern_id in USED_PUBLISHER_PATTERNS.items())
                         )
    print(f"Generated {relation_file_filename}")
    _write_csv_from_iter(relation_file_filename,
                         ['Timestamp ID', 'Pattern ID'],
                         TS_PUBLISHER_PATTERN_LINKS
                         )
    print(f"Generated {timestamp_def_filename}")
    with open(timestamp_def_filename, 'w', newline='') as fd:
        writer = csv.DictWriter(fd, fieldnames=FIELDS_ORDER)
        writer.writeheader()
        for timestamp in ALL_TS.values():
            _process_accept_reject_links(timestamp, ALL_TS)
            _check_missing_related_timestamps(timestamp, ALL_TS)
            writer.writerow(timestamp.row_data)


def _write_csv_from_iter(filename, field_names, iterable):
    with open(filename, 'w', newline='') as fd:
        writer = csv.DictWriter(fd, fieldnames=field_names)
        writer.writeheader()
        for r in sorted(iterable, key=lambda v: tuple(v)):
            row = dict(zip(field_names, list(r)))
            writer.writerow(row)


if __name__ == '__main__':
    main()
