#!/usr/bin/python3
import csv
from collections import defaultdict, namedtuple
from itertools import product
import os
import sys
from typing import Optional, List, Dict, Iterable


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
        'jit1_0',
        is_cancelable=True,
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
    # ETS-Mooring (Inbound)
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

    # Add XTD-Berth except ATD-Berth (different part and phase)
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

    generic_xty_timestamps(
        PUBLISHER_PATTERN_CA2ATH,
        ['ACT'],
        ['DEPA'],
        ['BRTH'],
        [NULL_VALUE, 'OUTB'],
        "Port Departure Execution",
        'jit1_0',
        need_event_location_for=ALL_EVENT_CLASSIFIER_CODES,
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
#    'negotiationCycle',
    'providedInStandard',
    COLUMN_ACCEPT_TS,
    COLUMN_REJECT_TS,
]

TS_ID_COUNTER = 0
UNNAMED = object()

ALL_EVENT_CLASSIFIER_CODES = frozenset({
    'EST',
    'REQ',
    'PLN',
    'ACT',
})
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
    'CRGO': ServiceTypeInfo('Cargo Ops', ['BRTH'], as_publisher_patterns(['TR'], CARRIER_ROLES)),
    'DCRO': ServiceTypeInfo('Cargo Ops Discharge', ['BRTH'], as_publisher_patterns(['TR'], CARRIER_ROLES)),
    'LCRO': ServiceTypeInfo('Cargo Ops Load', ['BRTH'], as_publisher_patterns(['TR'], CARRIER_ROLES)),
    'LASH': ServiceTypeInfo('Lashing', ['BRTH'], as_publisher_patterns(['LSH'], CARRIER_ROLES)),
    'MOOR': ServiceTypeInfo('Mooring', ['BRTH'], as_publisher_patterns(['MOR'], CARRIER_ROLES)),
    'BUNK': ServiceTypeInfo('Bunkering', ['BRTH'], as_publisher_patterns(['BUK'], CARRIER_ROLES)),
    'PILO': ServiceTypeInfo('Pilotage', ['BRTH'], as_publisher_patterns(['PLT'], CARRIER_ROLES)),
    'TOWG': ServiceTypeInfo('Towage', ['BRTH'], as_publisher_patterns(['TWG'], CARRIER_ROLES)),
    'SHPW': ServiceTypeInfo('Shore Power', ['BRTH'], as_publisher_patterns(['SVP'], CARRIER_ROLES)),
    'ANCO': ServiceTypeInfo('Anchorage Operations', ['ANCH'], as_publisher_patterns(['SVP'], CARRIER_ROLES)),
    'SLUG': ServiceTypeInfo('Sludge', ['BRTH', 'ANCH'], as_publisher_patterns(['SVP'], CARRIER_ROLES)),
    # Services that do not have a clear-cut service name (including "null")
    'SAFE': UNNAMED,
    'FAST': UNNAMED,
    'GWAY': UNNAMED,
    'VRDY': UNNAMED,
    NULL_VALUE: UNNAMED,  # "null" is not translated to None
}
REQ_PLN_ACT = frozenset({
    'REQ',
    'PLN',
    'ACT',
})
EST_REQ_PLN = frozenset({
    'EST',
    'REQ',
    'PLN',
})
EST_PLN = frozenset({
  'EST',
  'PLN',
})
VALID_JIT_VERSIONS = frozenset({
    'jit1_0',
    'jit1_1',
    'jit1_2',
})
VALID_NEGOTIATION_CYCLES = set()
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


def next_id():
    global TS_ID_COUNTER
    TS_ID_COUNTER += 1
    return 'TS' + str(TS_ID_COUNTER)


def xty_service_timestamps(
        event_classifier_codes: Iterable[str],
        port_call_service_type_codes: Iterable[str],
        port_call_phase_type_codes: Iterable[str],
        port_call_part: str,
        provided_in_standard: str,
        include_phase_in_name: bool = False,
        need_vessel_position_for=frozenset(),
        need_event_location_for=frozenset(),
        is_cancelable: bool = True,
        backwards_compat_phase_code: bool = True,
):
    if is_cancelable:
        operations_event_type_codes = CANCELABLE_SERVICE_OPERATIONS_EVENT_TYPE_CODE
    else:
        operations_event_type_codes = UNCANCELABLE_SERVICE_OPERATIONS_EVENT_TYPE_CODE
    for port_call_service_type_code in port_call_service_type_codes:
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
        need_event_location: bool = False

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
        need_vessel_position_for=frozenset(),
        need_event_location_for=frozenset(),
        backwards_compat_phase_code: bool = True,
):
    _ensure_known(provided_in_standard, VALID_JIT_VERSIONS, "providedInStandard")
    _ensure_known(port_call_part, VALID_PORT_CALL_PARTS, "portCallPart")
    if port_call_service_type_codes is None:
        # Service timestamps can easily use generate_service_timestamps instead, so we default to have this be NULL
        port_call_service_type_codes = [NULL_VALUE]
    for event_classifier_code, operations_event_type_code, facility_type_code, port_call_service_type_code, port_call_phase_type_code in product(
            event_classifier_codes,
            operations_event_type_codes,
            facility_type_codes,
            port_call_service_type_codes,
            port_call_phase_type_codes
    ):

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
    ts_fields = [
        next_id(),
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
            if alternative_name not in all_ts_table and alternative_name not in ALREADY_WARNED_MISSING_TIMESTAMP:
                print(f"W: Possibly missing timestamp {alternative_name} (discovered via {timestamp.timestamp_name})")
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
        for r in iterable:
            row = dict(zip(field_names, list(r)))
            writer.writerow(row)


if __name__ == '__main__':
    main()
