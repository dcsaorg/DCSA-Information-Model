#!/usr/bin/python3
import csv
import operator
import os
from collections import defaultdict, namedtuple
from typing import Optional, List, Dict, Iterable, Protocol, Union, TypeVar, Generic, Mapping, Callable

import sys
from itertools import product, repeat


def declare_timestamps():
    """Function that generates all the timestamps

    Timestamps are added to the CSV in the order this function outputs them.

    Use generic_xty_timestamps(...) for "ETA-Berth" and similar timestamps
    that follow this pattern.

    Use generate_special_timestamp(...) for "EOSP", "AT-All Fast" and similar
    special-case timestamps.
    """
    # Add XTA-Berth UC 1-3 + 41
    generic_xty_timestamps(
        event_classifier_code_matches(ifelse(ACT,
                                             # ACT follows a different pattern here
                                             as_publisher_patterns(['TR', 'CA', 'AG', 'VSL'], ['ATH']),
                                             PUBLISHER_PATTERN_CA2TR)),
        ALL_EVENT_CLASSIFIER_CODES,
        UNCANCELABLE_ARRI_OPERATIONS_EVENT_TYPE_CODE,
        ['BRTH'],
        ['INBD', NULL_VALUE],
        event_classifier_code_matches(ifelse(EST_REQ_PLN, "Berth Arrival Planning",
                                             "Pilot Boarding Place Arrival Planning And Execution, Berth Arrival Execution")),
        port_call_phase_type_code_matches(ifelse(NULL_VALUE, 'jit1_0', 'jit1_1')),
        vessel_position_requirement=event_classifier_code_matches(ifelse(EST_PLN, OPTIONAL, EXCLUDED)),
        event_location_requirement=event_classifier_code_matches(ifelse(REQ_PLN_ACT, REQUIRED, OPTIONAL)),
        negotiation_cycle='TA-Berth',
        is_miles_to_destination_relevant=event_classifier_code_matches(ifelse(EST_PLN, True, False)),
    )

    # ETS-Cargo Ops
    xty_service_timestamps(
        EST_REQ_PLN,  # ATS-Cargo Ops comes later (different port call part)
        ['CRGO'],
        ['ALGS', NULL_VALUE],
        "Services Planning",
        'jit1_1',
        # We only generated the STRT here, CMPL (and CANC) comes later
        operations_event_type_codes=['STRT'],
        vessel_position_requirement=event_classifier_code_matches(ifelse(EST_PLN, OPTIONAL, EXCLUDED)),
        event_location_requirement=REQUIRED,
        negotiation_cycle='T-Cargo Ops'
    )

    # XTY-Pilotage (Inbound) UC 7-12 + 36 + 40
    xty_service_timestamps(
        ALL_EVENT_CLASSIFIER_CODES,
        ['PILO'],
        ['INBD', NULL_VALUE],
        event_classifier_code_matches(ifelse(REQ_PLN_ACT, "Services Planning",
                                             "Pilot Boarding Place Arrival Planning And Execution, Berth Arrival Execution")),
        port_call_phase_type_code_matches(ifelse(NULL_VALUE, 'jit1_0', 'jit1_1')),
        include_phase_in_name=True,
        is_cancelable=True,
        vessel_position_requirement=event_classifier_code_matches(ifelse(EST_PLN_ACT, OPTIONAL, EXCLUDED)),
        event_location_requirement=REQUIRED,
        # The implicit one also counts as Inbound
        negotiation_cycle='T-Pilotage (Inbound)',
        is_miles_to_destination_relevant=event_classifier_and_operations_event_type_code_matches(
            ifelse(('ACT', 'STRT'), True, False)
        )
    )

    # XTY-Towage (Inbound)  UC 13-18 + 37 + 38
    xty_service_timestamps(
        ALL_EVENT_CLASSIFIER_CODES,
        ['TOWG'],
        ['INBD', NULL_VALUE],
        event_classifier_code_matches(ifelse(REQ_PLN_ACT, "Services Planning",
                                             "Pilot Boarding Place Arrival Planning And Execution, Berth Arrival Execution")),
        'jit1_1',
        include_phase_in_name=True,
        is_cancelable=True,
        vessel_position_requirement=event_classifier_code_matches(ifelse(EST_PLN_ACT, OPTIONAL, EXCLUDED)),
        event_location_requirement=REQUIRED,
        negotiation_cycle='T-Towage (Inbound)',
        is_miles_to_destination_relevant=event_classifier_and_operations_event_type_code_matches(
            ifelse(('ACT', 'STRT'), True, False)
        )
    )

    # ETS-Mooring (Inbound) UC 19 - 24 +  39 + 44
    xty_service_timestamps(
        ALL_EVENT_CLASSIFIER_CODES,
        ['MOOR'],
        ['INBD', NULL_VALUE],
        event_classifier_code_matches(ifelse(REQ_PLN_ACT, "Services Planning",
                                             "Pilot Boarding Place Arrival Planning And Execution, Berth Arrival Execution")),
        'jit1_2',
        include_phase_in_name=True,
        is_cancelable=True,
        vessel_position_requirement=event_classifier_code_matches(ifelse(EST_PLN, OPTIONAL, EXCLUDED)),
        event_location_requirement=REQUIRED,
        negotiation_cycle='T-Mooring (Inbound)'
    )

    # Bunkering UC 25 - 30
    xty_service_timestamps(
        EST_REQ_PLN,
        ['BUNK'],
        ['ALGS', NULL_VALUE],
        "Services Planning",
        'jit1_2',
        include_phase_in_name=False,
        is_cancelable=True,
        event_location_requirement=REQUIRED,
        negotiation_cycle='T-Bunkering',
    )

    # ETA PBP UC 31-33 + 35
    generic_xty_timestamps(
        PUBLISHER_PATTERN_CA2ATH,
        ALL_EVENT_CLASSIFIER_CODES,
        ['ARRI'],
        ['PBPL'],
        ['INBD', NULL_VALUE],
        "Pilot Boarding Place Arrival Planning And Execution, Berth Arrival Execution",
        'jit1_0',
        event_location_requirement=REQUIRED,
        negotiation_cycle='TA-PBPL',
        # IFS says EST, PLN *and* ACT can use MtD
        is_miles_to_destination_relevant=event_classifier_code_matches(ifelse('REQ', False, True)),
    )

    # EOSP UC 34
    generate_special_timestamp(
        'ESOP',
        PUBLISHER_PATTERN_CA2ATH,
        'ARRI',
        NULL_VALUE,
        'INBD',
        'Pilot Boarding Place Arrival Planning And Execution, Berth Arrival Execution',
        'jit1_1',
        vessel_position_requirement=OPTIONAL,
        is_miles_to_destination_relevant=True,
    )

    # AT All fast UC 42 + Gangway Down and Safe UC 43
    for (name, service_code) in [('AT All fast', 'FAST'),
                                 ('Gangway Down and Safe', 'GWAY')]:
        generate_special_timestamp(
            name,
            PUBLISHER_PATTERN_CA2ATH,
            'ARRI',
            'BRTH',
            'ALGS',
            "Pilot Boarding Place Arrival Planning And Execution, Berth Arrival Execution",
            'jit1_1',
            service_code,
            vessel_position_requirement=EXCLUDED,
        )

    # Vessel Readiness for Cargo ops UC 45
    for (name, service_code, version) in [('Vessel Readiness for Cargo operations [JIT 1.2]', 'VRDY', 'jit1_2'),
                                          ('Vessel Readiness for Cargo operations [JIT 1.1]', 'SAFE', 'jit1_1')]:
        generate_special_timestamp(
            name,
            PUBLISHER_PATTERN_CA2TR,
            'ARRI',
            'BRTH',
            'ALGS',
            "Start Cargo Operations And Services",
            version,
            service_code,
            vessel_position_requirement=EXCLUDED,
            event_location_requirement=REQUIRED,
        )

    # ATS Cargo Ops UC 46
    xty_service_timestamps(
        ACT,
        ['CRGO'],
        ['ALGS', NULL_VALUE],
        "Start Cargo Operations And Services",
        'jit1_0',
        # We only generated the STRT here, CMPL (and CANC) comes later
        operations_event_type_codes=['STRT'],
        event_location_requirement=REQUIRED,
        negotiation_cycle='T-Cargo Ops'
    )

    # ATS / ATC Cargo Ops Discharge UC 47 + 48
    xty_service_timestamps(
        ACT,
        ['DCRO'],
        ['ALGS'],
        "Start Cargo Operations And Services",
        'jit1_2',
        vessel_position_requirement=EXCLUDED,
        event_location_requirement=REQUIRED,
        is_cancelable=False,
    )

    # ATC Cargo Ops Load UC 49
    xty_service_timestamps(
        ACT,
        ['LCRO'],
        ['ALGS'],
        "Start Cargo Operations And Services",
        'jit1_2',
        operations_event_type_codes=['STRT'],
        event_location_requirement=REQUIRED,
        is_cancelable=False,
    )

    # ETC Cargo Ops UC 50 - 52
    xty_service_timestamps(
        EST_REQ_PLN,
        ['CRGO'],
        ['ALGS'],
        "Start Cargo Operations And Services",
        event_classifier_code_matches(ifelse(EST, 'jit1_2', 'jit1_0')),
        operations_event_type_codes=['CMPL'],
        event_location_requirement=REQUIRED,
        is_cancelable=True,
    )

    # ATS/ATC-Bunkering UC 53 + 69
    xty_service_timestamps(
        ACT,
        ['BUNK'],
        ['ALGS', NULL_VALUE],
        operations_event_type_code_matches(ifelse('STRT', "Start Cargo Operations And Services",
                                                  "Port Departure Planning And Services Completion")),
        'jit1_1',
        include_phase_in_name=False,
        event_location_requirement=REQUIRED,
        negotiation_cycle='T-Bunkering'
    )

    # Add XTD-Berth except ATD-Berth (different part and phase) UC 54 + 67 + 68
    generic_xty_timestamps(
        PUBLISHER_PATTERN_CA2ATH,
        EST_REQ_PLN,
        UNCANCELABLE_DEPA_OPERATIONS_EVENT_TYPE_CODE,
        ['BRTH'],
        ['ALGS', NULL_VALUE],
        "Port Departure Planning And Services Completion",
        port_call_phase_type_code_matches(ifelse(NULL_VALUE, 'jit1_0', 'jit1_1')),
        event_location_requirement=REQUIRED,
        negotiation_cycle='TD-Berth',
    )

    #Pilotage UC 55 - 57 + 61 - 63 + Towage UC 58 - 60 + UC 64 - 66
    xty_service_timestamps(
        EST_REQ_PLN,
        ['PILO', 'TOWG'],
        ['OUTB', 'SHIF'],
        "Port Departure Planning And Services Completion",
        'jit1_1',
        include_phase_in_name=True,
        is_cancelable=True,
        event_location_requirement=REQUIRED,
    )

    # ATC Cargo Ops Load UC 70
    xty_service_timestamps(
        ACT,
        ['LCRO'],
        ['ALGS'],
        "Port Departure Planning And Services Completion",
        'jit1_2',
        operations_event_type_codes=['CMPL'],
        event_location_requirement=REQUIRED,
        is_cancelable=False,
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
        event_location_requirement=REQUIRED,
        is_cancelable=False,  # Generated by UC 50
    )

    # XTY-Mooring (Outbound) UC 72 - 77 + 81 + 84
    xty_service_timestamps(
        ALL_EVENT_CLASSIFIER_CODES,
        ['MOOR'],
        ['OUTB'],
        event_classifier_code_matches(ifelse(EST_REQ_PLN,
                                             "Port Departure Planning And Services Completion",
                                             "Port Departure Execution")),
        'jit1_2',
        include_phase_in_name=True,
        is_cancelable=True,
        event_location_requirement=REQUIRED,
    )


    #ATC Lashing UC 78
    generate_special_timestamp(
        'ATC Lashing',
        as_publisher_patterns(['LSH'], ['CA']),
        'CMPL',
        'BRTH',
        'ALGS',
        "Port Departure Planning And Services Completion",
        'jit1_1',
        'LASH',
        vessel_position_requirement=EXCLUDED,
        event_location_requirement=REQUIRED,
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
        vessel_position_requirement=EXCLUDED,
        event_location_requirement=REQUIRED,
    )

    # Vessel ready to sail UC 80
    for (name, service_code, version) in [('Vessel Ready to sail [JIT 1.2]', 'VRDY', 'jit1_2'),
                                          ('Vessel Ready to sail [JIT 1.1]', 'SAFE', 'jit1_1')]:
        generate_special_timestamp(
            name,
            PUBLISHER_PATTERN_CA2TR,
            'DEPA',
            'BRTH',
            'ALGS',
            "Port Departure Execution",
            version,
            service_code,
            vessel_position_requirement=EXCLUDED,
            event_location_requirement=REQUIRED,
        )

    # ATD Berth UC 82
    generic_xty_timestamps(
        PUBLISHER_PATTERN_CA2ATH,
        ACT,
        ['DEPA'],
        ['BRTH'],
        ['OUTB', NULL_VALUE],
        "Port Departure Execution",
        'jit1_0',
        event_location_requirement=REQUIRED,
        negotiation_cycle='TD-Berth',
    )

    # ATY Pilotage / Towage UC 83 - 87
    xty_service_timestamps(
        ACT,
        ['PILO', 'TOWG'],
        ['OUTB', 'SHIF'],
        "Port Departure Execution",
        'jit1_1',
        include_phase_in_name=True,
        is_cancelable=False,
        event_location_requirement=operations_event_type_code_matches(ifelse('STRT', REQUIRED, EXCLUDED)),
    )

    # SOSP UC 88
    generate_special_timestamp(
        'SOSP',
        PUBLISHER_PATTERN_CA2ATH,
        'DEPA',
        NULL_VALUE,
        'OUTB',
        "Port Departure Execution",
        'jit1_1',
        NULL_VALUE,
        vessel_position_requirement=EXCLUDED,
        event_location_requirement=EXCLUDED,
    )

    # XTY Anchorage UC 89 - UC 96
    generic_xty_timestamps(
        PUBLISHER_PATTERN_CA2ATH,
        ALL_EVENT_CLASSIFIER_CODES,
        ['ARRI', 'DEPA'],
        ['ANCH'],
        [NULL_VALUE],
        "Other Services - Anchorage Planning And Execution",
        'jit1_2',
        event_location_requirement=event_classifier_code_matches(ifelse(EST_PLN_ACT, OPTIONAL, EXCLUDED)),
        vessel_position_requirement=OPTIONAL,
        # All except REQ has MtD in the IFS (even ATD-Anchorage, which seems weird)
        is_miles_to_destination_relevant=event_classifier_code_matches(ifelse('REQ', False, True)),
    )

    # ATY Anchorage OPS UC 97 + 98
    xty_service_timestamps(
        ACT,
        ['ANCO'],
        [NULL_VALUE],
        "Other Services - Anchorage Planning And Execution",
        'jit1_2',
        vessel_position_requirement=OPTIONAL,
        event_location_requirement=REQUIRED,
        is_miles_to_destination_relevant=True,
        # Group these with the XTY-Anchorage timestamps (seems to make more sense than having them float their own)
        negotiation_cycle='T-Anchorage',
        is_cancelable=False,
    )

    # Sludge UC 99 - 106
    xty_service_timestamps(
        EST_PLN_REQ_ACT,
        ['SLUG'],
        [NULL_VALUE],
        "Other Services - Sludge Planning And Execution",
        'jit1_2',
        vessel_position_requirement=OPTIONAL,
        event_location_requirement=REQUIRED,
    )

    # Shore power UC 107 + 108
    xty_service_timestamps(
        ACT,
        ['SHPW'],
        ['ALGS'],
        "Other Services - Shore Power Execution",
        'jit1_2',
        is_cancelable=False,
        event_location_requirement=REQUIRED,
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
        vessel_position_requirement=OPTIONAL,
    )


def ifelse(if_one_of: Union[List['R'], 'R'], value: 'T', else_value: 'T') -> Dict['R', 'T']:
    if not isinstance(if_one_of, list):
        if_one_of = [if_one_of]
    d = defaultdict(lambda: else_value)
    d.update(zip(if_one_of, repeat(value)))
    return d


NULL_VALUE = "null"

COLUMN_ID = 'timestampID'
COLUMN_TIMESTAMP_TYPE_NAME = 'timestampTypeName'
COLUMN_EVENT_CLASSIFIER_CODE = 'eventClassifierCode'
COLUMN_PORT_CALL_SERVICE_TYPE_CODE = 'portCallServiceTypeCode'
COLUMN_ACCEPT_TS = 'acceptTimestampDefinition'
COLUMN_REJECT_TS = 'rejectTimestampDefinition'

FIELDS_ORDER = [
    'timestampID',
    COLUMN_TIMESTAMP_TYPE_NAME,
    COLUMN_EVENT_CLASSIFIER_CODE,
    'operationsEventTypeCode',
    'portCallPhaseTypeCode',
    'portCallServiceTypeCode',
    'facilityTypeCode',
    'portCallPart',
    'eventLocationRequirement',
    'isTerminalNeeded',
    'vesselPositionRequirement',
    'isMilesToDestinationRelevant',
    'providedInStandard',
    COLUMN_ACCEPT_TS,
    COLUMN_REJECT_TS,
    'negotiationCycle',
    'implicitVariantOf',
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
EXCLUDED = 'EXCLUDED'
OPTIONAL = 'OPTIONAL'
REQUIRED = 'REQUIRED'
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
    'PBPL': 'PBP',
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


class TimestampDetail(namedtuple('_TimestampDetail', ['event_classifier_code', 'operations_event_type_code',
                                                      'facility_type_code', 'port_call_service_type_code',
                                                      'port_call_phase_type_code'])):

    def replace(self, **kwargs):
        return self._replace(**kwargs)


select_event_classifier_code = operator.attrgetter('event_classifier_code')
select_operations_event_type_code = operator.attrgetter('operations_event_type_code')
select_port_call_phase_type_code = operator.attrgetter('port_call_phase_type_code')


T = TypeVar('T', bool, str, List[str], List['PublisherPattern'])
R = TypeVar('R')


def as_condition(v: Union[T, "GetItemProtocol[T]"]) -> "GetItemProtocol[T]":
    if isinstance(v, (bool, str, list)):
        return Unconditionally(v)
    return v


def port_call_phase_type_code_matches(values):
    return IfValueMatchesCondition(select_port_call_phase_type_code, values)


def event_classifier_code_matches(values):
    return IfValueMatchesCondition(select_event_classifier_code, values)


def operations_event_type_code_matches(values):
    return IfValueMatchesCondition(select_operations_event_type_code, values)


def event_classifier_and_operations_event_type_code_matches(values):
    return IfValueMatchesCondition(lambda t: (t.event_classifier_code, t.operations_event_type_code), values)


class GetItemProtocol(Protocol[T]):

    def __getitem__(self, item: TimestampDetail) -> T:
        # SonarLint - it is a protocol, it is not supposed to have an implementation.
        pass


class IfValueMatchesCondition(Generic[T, R]):
    def __init__(self, attrgetter: Callable[[TimestampDetail], R], values: Mapping[R, T]):
        self._attrgetter = attrgetter
        self._values = values

    def __getitem__(self, item: TimestampDetail) -> T:
        key_value = self._attrgetter(item)
        return self._values[key_value]


class Unconditionally(Generic[T]):

    def __init__(self, value: T):
        self._value = value

    def __getitem__(self, item: TimestampDetail) -> T:
        return self._value


class ServiceTypeInfo:

    def __init__(self, name, facility_type_codes, publisher_patterns, negotiation_cycle=None):
        self.name = name
        self.facility_type_codes = [facility_type_codes] if isinstance(facility_type_codes, str) else facility_type_codes
        self.publisher_patterns = publisher_patterns
        self.negotiation_cycle = negotiation_cycle

    def facility_type_codes_for(self, key):
        if isinstance(self.facility_type_codes, list):
            return self.facility_type_codes
        return [self.facility_type_codes[key]]


PUBLISHER_PATTERN_CA2TR = as_publisher_patterns(CARRIER_ROLES, ['TR'])

# Opposite order of PUBLISHER_PATTERN_CA2TR
PUBLISHER_PATTERN_TR2CA = [
    PublisherPattern(publisher_role=r, primary_receiver_role=p)
    for p, r in PUBLISHER_PATTERN_CA2TR
]
PUBLISHER_PATTERN_CA2ATH = as_publisher_patterns(CARRIER_ROLES, ['ATH'])

PILO_TOWAGE_FACILITY_TYPE_CODE_TABLE = {
    # Inbound (+ implicit) is PBPL -> BRTH
    ('INBD', 'STRT'): 'PBPL',
    ('INBD', 'CMPL'): 'BRTH',
    ('INBD', 'CANC'): 'BRTH',

    (NULL_VALUE, 'STRT'): 'PBPL',
    (NULL_VALUE, 'CMPL'): 'BRTH',
    (NULL_VALUE, 'CANC'): 'BRTH',

    # Shift is BRTH -> BRTH
    ('SHIF', 'STRT'): 'BRTH',
    ('SHIF', 'CMPL'): 'BRTH',
    ('SHIF', 'CANC'): 'BRTH',

    # Outbound is BRTH -> Nothing
    ('OUTB', 'STRT'): 'BRTH',
    ('OUTB', 'CMPL'): NULL_VALUE,
    ('OUTB', 'CANC'): NULL_VALUE,
}


SERVICE_TYPE_CODE2INFO = {
    # Note that the timestamp name is not always the same as the service name, so there is no automatic cross
    # check between the portcallservicetypecodes.csv file and the names used here.
    'CRGO': ServiceTypeInfo('Cargo Ops', 'BRTH', as_publisher_patterns(['TR'], CARRIER_ROLES)),
    'DCRO': ServiceTypeInfo('Cargo Ops Discharge', 'BRTH', as_publisher_patterns(['TR'], CARRIER_ROLES),
                            negotiation_cycle='T-Cargo Ops'),
    'LCRO': ServiceTypeInfo('Cargo Ops Load', 'BRTH', as_publisher_patterns(['TR'], CARRIER_ROLES),
                            negotiation_cycle='T-Cargo Ops'),
    'LASH': ServiceTypeInfo('Lashing', 'BRTH', as_publisher_patterns(['LSH'], CARRIER_ROLES)),
    'MOOR': ServiceTypeInfo('Mooring', 'BRTH', as_publisher_patterns(['MOR'], ['ATH'])),
    'BUNK': ServiceTypeInfo('Bunkering', ['ANCH', 'BRTH'], as_publisher_patterns(['BUK'], CARRIER_ROLES)),

    'PILO': ServiceTypeInfo('Pilotage', PILO_TOWAGE_FACILITY_TYPE_CODE_TABLE, as_publisher_patterns(['PLT'], ['ATH'])),
    'TOWG': ServiceTypeInfo('Towage', PILO_TOWAGE_FACILITY_TYPE_CODE_TABLE, as_publisher_patterns(['TWG'], ['ATH'])),
    'SHPW': ServiceTypeInfo('Shore Power', 'BRTH', as_publisher_patterns(['SVP'], CARRIER_ROLES)),
    'ANCO': ServiceTypeInfo('Anchorage Ops', 'ANCH', as_publisher_patterns(CARRIER_ROLES, ['ATH'])),
    'SLUG': ServiceTypeInfo('Sludge', ['ANCH', 'BRTH'], as_publisher_patterns(['SLU'], CARRIER_ROLES)),
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
EST_PLN_ACT = [
    'EST',
    'PLN',
    'ACT',
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
VALID_NEGOTIATION_CYCLE_KEYS = set()
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
USED_PUBLISHER_PATTERNS = PublisherPatternDefaultDict()

TS_PUBLISHER_PATTERN_LINKS = set()


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
        port_call_phase_type_codes: List[str],
        port_call_part: Union[str, "GetItemProtocol[str]"],
        provided_in_standard: Union[str, "GetItemProtocol[str]"],
        include_phase_in_name: bool = False,
        event_location_requirement: Union[str, "GetItemProtocol[str]"] = EXCLUDED,
        vessel_position_requirement: Union[str, "GetItemProtocol[str]"] = EXCLUDED,
        operations_event_type_codes=None,
        is_cancelable: bool = True,
        implicit_implies_phase: Optional[str] = None,
        is_miles_to_destination_relevant: Union[bool, "GetItemProtocol[bool]"] = False,
        negotiation_cycle: Optional[str] = None,
):
    if operations_event_type_codes is None:
        if is_cancelable:
            operations_event_type_codes = CANCELABLE_SERVICE_OPERATIONS_EVENT_TYPE_CODE
        else:
            operations_event_type_codes = UNCANCELABLE_SERVICE_OPERATIONS_EVENT_TYPE_CODE

    if implicit_implies_phase is None and len(port_call_phase_type_codes) == 2 \
            and NULL_VALUE in port_call_phase_type_codes:
        explicit_phase = port_call_phase_type_codes[0]
        if explicit_phase == NULL_VALUE:
            raise ValueError("Explicit phase must come before implicit phase (NULL_VALUE)")
        implicit_implies_phase = explicit_phase

    for port_call_service_type_code in port_call_service_type_codes:
        service_info = SERVICE_TYPE_CODE2INFO[port_call_service_type_code]
        _ensure_named(service_info, 'portCallServiceTypeCode', port_call_service_type_code)
        if negotiation_cycle is None:
            negotiation_cycle = service_info.negotiation_cycle
        for port_call_phase_type_code, operations_event_type_code in product(port_call_phase_type_codes, operations_event_type_codes):
            facility_type_code_key = (port_call_phase_type_code, operations_event_type_code)
            facility_type_codes = service_info.facility_type_codes_for(facility_type_code_key)
            generic_xty_timestamps(
                service_info.publisher_patterns,
                event_classifier_codes,
                [operations_event_type_code],
                facility_type_codes,
                [port_call_phase_type_code],
                port_call_part,
                provided_in_standard,
                implicit_implies_phase=implicit_implies_phase,
                port_call_service_type_codes=[port_call_service_type_code],
                include_phase_in_name=include_phase_in_name,
                vessel_position_requirement=vessel_position_requirement,
                event_location_requirement=event_location_requirement,
                include_facility_type_in_name=len(facility_type_codes) > 1,
                negotiation_cycle=negotiation_cycle,
                is_miles_to_destination_relevant=is_miles_to_destination_relevant,
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
        vessel_position_requirement: str = EXCLUDED,
        event_location_requirement: str = EXCLUDED,
        negotiation_cycle: str = 'Special',
        is_miles_to_destination_relevant=False,
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
        event_location_requirement,
        vessel_position_requirement,
        provided_in_standard,
        publisher_pattern,
        negotiation_cycle,  # negotiation cycle
        NULL_VALUE,  # implicitVariantOf
        is_pattern_timestamp=False,
        is_miles_to_destination_relevant=is_miles_to_destination_relevant,
    )


def _determine_pattern_name_parts(port_call_service_type_code,
                                  port_call_phase_type_code,
                                  facility_type_code,
                                  include_implicit_phase_in_name,
                                  include_phase_in_name,
                                  include_facility_type_in_name
                                  ):
    if port_call_service_type_code != NULL_VALUE:
        service_info = SERVICE_TYPE_CODE2INFO[port_call_service_type_code]
        _ensure_named(service_info, 'portCallServiceTypeCode', port_call_service_type_code)
        name_stem = service_info.name
    else:
        name_stem = FACILITY_TYPE_CODE2NAME_STEM[facility_type_code]
        _ensure_named(name_stem, 'facilityTypeCode', facility_type_code)
        service_info = None
    if port_call_phase_type_code == NULL_VALUE:
        phase_name_part = ' (<implicit>)' if include_implicit_phase_in_name or include_phase_in_name else ''
    elif include_phase_in_name:
        n = PHASE_TYPE_CODE2NAME_STEM[port_call_phase_type_code]
        _ensure_named(n, 'portCallPhaseTypeCode', port_call_phase_type_code)
        phase_name_part = ' (' + n + ')'
    else:
        phase_name_part = ''

    facility_type_name_part = ''
    if include_facility_type_in_name:
        facility_type_code_name = FACILITY_TYPE_CODE2NAME_STEM[facility_type_code]
        _ensure_named(facility_type_code_name, 'facilityTypeCode', facility_type_code)
        facility_type_name_part = f'@{facility_type_code_name}'

    return name_stem, facility_type_name_part, phase_name_part, service_info


def generic_xty_timestamps(
        initial_publisher_pattern: Union[List['PublisherPattern'], "GetItemProtocol[List[PublisherPattern]]" ],
        event_classifier_codes: Iterable[str],
        operations_event_type_codes: Iterable[str],
        facility_type_codes: Iterable[str],
        port_call_phase_type_codes: List[str],
        port_call_part: Union[str, "GetItemProtocol[str]"],
        provided_in_standard: Union[str, "GetItemProtocol[str]"],
        port_call_service_type_codes: Optional[List[str]] = None,
        include_phase_in_name: bool = False,
        include_facility_type_in_name: bool = False,
        event_location_requirement: Union[str, "GetItemProtocol[str]"] = EXCLUDED,
        vessel_position_requirement: Union[str, "GetItemProtocol[str]"] = EXCLUDED,
        implicit_implies_phase: Optional[str] = None,
        negotiation_cycle: Optional[str] = None,
        is_miles_to_destination_relevant: Union[bool, "GetItemProtocol[bool]"] = False,
):

    if implicit_implies_phase is None and len(port_call_phase_type_codes) == 2 \
            and NULL_VALUE in port_call_phase_type_codes:
        explicit_phase = port_call_phase_type_codes[0]
        if explicit_phase == NULL_VALUE:
            raise ValueError("Explicit phase must come before implicit phase (NULL_VALUE)")
        implicit_implies_phase = explicit_phase

    include_implicit_phase_in_name = True if implicit_implies_phase and implicit_implies_phase != NULL_VALUE else False

    if port_call_service_type_codes is None:
        # Service timestamps can easily use generate_service_timestamps instead, so we default to have this be NULL
        port_call_service_type_codes = [NULL_VALUE]

    negotiation_cycle_prefix = None
    port_call_part = as_condition(port_call_part)
    provided_in_standard = as_condition(provided_in_standard)
    event_location_requirement = as_condition(event_location_requirement)
    vessel_position_requirement = as_condition(vessel_position_requirement)

    initial_publisher_pattern = as_condition(initial_publisher_pattern)
    is_miles_to_destination_relevant = as_condition(is_miles_to_destination_relevant)

    if negotiation_cycle is None:
        negotiation_cycle_prefix = 'T-'

    for timestamp_detail in (
            TimestampDetail(*x) for x in product(
                event_classifier_codes,
                operations_event_type_codes,
                facility_type_codes,
                port_call_service_type_codes,
                port_call_phase_type_codes
            )):

        ts_version = provided_in_standard[timestamp_detail]
        ts_port_call_part = port_call_part[timestamp_detail]

        _ensure_known(timestamp_detail.event_classifier_code, ALL_EVENT_CLASSIFIER_CODES, "eventClassifierCoder")
        _ensure_known(timestamp_detail.operations_event_type_code, VALID_OPERATIONS_EVENT_TYPE_CODES,
                      "operationsEventTypeCode")
        _ensure_known(timestamp_detail.facility_type_code, FACILITY_TYPE_CODE2NAME_STEM, "facilityTypeCode")
        _ensure_known(timestamp_detail.port_call_service_type_code, SERVICE_TYPE_CODE2INFO, "portCallServiceTypeCode")
        _ensure_known(timestamp_detail.port_call_phase_type_code, PHASE_TYPE_CODE2NAME_STEM, "portCallPhaseTypeCode")

        _ensure_known(ts_version, VALID_JIT_VERSIONS, "providedInStandard")
        _ensure_known(ts_port_call_part, VALID_PORT_CALL_PARTS, "portCallPart")

        name_stem, facility_type_name_part, phase_name_part, service_info = _determine_pattern_name_parts(
            timestamp_detail.port_call_service_type_code,
            timestamp_detail.port_call_phase_type_code,
            timestamp_detail.facility_type_code,
            include_implicit_phase_in_name,
            include_phase_in_name,
            include_facility_type_in_name
        )

        selected_publisher_pattern = initial_publisher_pattern[timestamp_detail]
        if timestamp_detail.operations_event_type_code == 'CANC':
            if implicit_implies_phase and timestamp_detail.port_call_phase_type_code == NULL_VALUE:
                # Do not create implicit cancels.
                continue
            full_name = ''.join(('Cancel ', name_stem, facility_type_name_part, phase_name_part))
            if full_name in ALL_TS:
                # Forgive multiple cancels - it is not worth the hassle to report / deal with
                continue
            # Cancel was introduced in JIT 1.2, so any timestamp with cancel before 1.2 had the cancel
            # version introduced in 1.2
            ts_version = max(ts_version, 'jit1_2')
            # Cancel is always ACT
            timestamp_detail = timestamp_detail.replace(event_classifier_code='ACT')
            # Cancel has its own part together with OMIT (distinct from the part of the actual service itself)
            ts_port_call_part = 'OMIT Port Call Or Cancel A Service'
        else:
            full_name = ''.join((timestamp_detail.event_classifier_code[0], 'T',
                                 timestamp_detail.operations_event_type_code[0], '-',
                                 name_stem, facility_type_name_part, phase_name_part))

        implicit_variant_of = NULL_VALUE
        if implicit_implies_phase and timestamp_detail.port_call_phase_type_code == NULL_VALUE:
            _, _, explicit_phase_name_part, _ = _determine_pattern_name_parts(
                timestamp_detail.port_call_service_type_code,
                implicit_implies_phase,
                timestamp_detail.facility_type_code,
                include_implicit_phase_in_name,
                include_phase_in_name,
                include_facility_type_in_name
            )
            explicit_full_name = ''.join((timestamp_detail.event_classifier_code[0], 'T',
                                          timestamp_detail.operations_event_type_code[0], '-',
                                          name_stem, facility_type_name_part, explicit_phase_name_part))
            try:
                explicit_ts = ALL_TS[explicit_full_name]
            except KeyError:
                raise ValueError(f"{explicit_full_name} was not defined (yet) but referenced by {full_name}."
                                 f" Please ensure they are defined in the correct order")
            implicit_variant_of = explicit_ts.id

        publisher_pattern = selected_publisher_pattern
        if timestamp_detail.event_classifier_code == 'REQ':
            # In the default pattern, REQ reverses publisher and receiver
            publisher_pattern = [(r, p) for p, r in selected_publisher_pattern]
        elif timestamp_detail.event_classifier_code == 'ACT':
            # In the default pattern, anyone is allowed to sent ACT (this also applies to CANC,
            # where either party can emit the CANC - it happens to work because CANC is always an ACT)
            publisher_pattern = selected_publisher_pattern.copy()
            publisher_pattern.extend((r, p) for p, r in selected_publisher_pattern)

        ts_negotiation_cycle = negotiation_cycle
        if ts_negotiation_cycle is None:
            assert negotiation_cycle_prefix is not None
            ts_negotiation_cycle = ''.join((negotiation_cycle_prefix, name_stem, phase_name_part))

        ts_event_location_requirement = event_location_requirement[timestamp_detail]
        ts_vessel_position_requirement = vessel_position_requirement[timestamp_detail]
        ts_is_miles_to_destination_relevant = is_miles_to_destination_relevant[timestamp_detail]

        _make_timestamp(
            full_name,
            timestamp_detail.event_classifier_code,
            timestamp_detail.operations_event_type_code,
            timestamp_detail.port_call_phase_type_code,
            timestamp_detail.port_call_service_type_code,
            timestamp_detail.facility_type_code,
            ts_port_call_part,
            ts_event_location_requirement,
            ts_vessel_position_requirement,
            ts_version,
            publisher_pattern,
            ts_negotiation_cycle,
            implicit_variant_of,
            # 'CANC' does not follow the pattern.
            is_pattern_timestamp=timestamp_detail.operations_event_type_code != 'CANC',
            is_miles_to_destination_relevant=ts_is_miles_to_destination_relevant,
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
    def port_call_service_type_code(self):
        return self.row_data[COLUMN_PORT_CALL_SERVICE_TYPE_CODE]

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
                    event_location_requirement,
                    vessel_position_requirement,
                    provided_in_standard,
                    publisher_pattern,
                    negotiation_cycle,
                    implicit_variant_of,
                    is_pattern_timestamp=False,
                    is_miles_to_destination_relevant=False,
                    ):

    if timestamp_name in TS_NOT_IN_STANDARD:
        raise ValueError(f'"{timestamp_name}" was created but also marked as a "Do not warn about this TS being missing as the standard does not declare it"')
    if negotiation_cycle not in VALID_NEGOTIATION_CYCLE_KEYS:
        raise ValueError(f'"{timestamp_name}" had unknown negotiation cycle "{negotiation_cycle}"')

    ts_fields = [
        timestamp_name,
        timestamp_name,
        event_classifier_code,
        operations_event_type_code,
        port_call_phase_type_code,
        port_call_service_type_code,
        facility_type_code,
        port_call_part,  # Port Call Part
        event_location_requirement,
        facility_type_code == 'BRTH',  # isTerminalNeeded
        vessel_position_requirement,  # vesselPositionRequirement
        is_miles_to_destination_relevant,
        provided_in_standard,  # providedInStandard
        NULL_VALUE,  # acceptTimestampDefinition
        NULL_VALUE,  # rejectTimestampDefinition
        negotiation_cycle,  # negotiationCycle
        implicit_variant_of,
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
        missing = []
        for code in ALL_EVENT_CLASSIFIER_CODES:
            alternative_name = code[0] + timestamp.timestamp_name[1:]
            if alternative_name == timestamp.timestamp_name:
                continue
            if alternative_name not in all_ts_table:
                missing.append(alternative_name)

        # Ignore missing timestamps if the timestamp is an actual for a service (several services
        # only defines "ACT"s - e.g. Shore Power and Anchor Ops)
        if (len(missing) == len(ALL_EVENT_CLASSIFIER_CODES) - 1
                and timestamp.event_classifier_code == 'ACT'
                and timestamp.port_call_service_type_code != NULL_VALUE
        ):
            return
        for alternative_name in missing:
            if alternative_name not in TS_NOT_IN_STANDARD or alternative_name not in ALREADY_WARNED_MISSING_TIMESTAMP:
                continue
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


def _load_data_and_self_check(reference_data_dir, sample_data_dir):
    VALID_OPERATIONS_EVENT_TYPE_CODES.update(
        load_data_set(
            os.path.join(reference_data_dir, 'operationseventtypecodes.csv'),
            'operations_event_type_code',
        ))

    VALID_NEGOTIATION_CYCLE_KEYS.update(
        load_data_set(
            os.path.join(sample_data_dir, 'negotiationcycles.csv'),
            'cycleKey',
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

    _load_data_and_self_check(reference_data_dir, sample_data_dir)

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
