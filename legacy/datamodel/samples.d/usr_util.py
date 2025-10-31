#!/usr/bin/python3
import argparse
import csv
import re

import sys
# https://en.wikipedia.org/wiki/Damm_algorithm
# Table is from that page
from itertools import repeat, chain

DIGITS_ONLY_PATTERN = re.compile(r'^\d+$')

DAMM_TABLE = (
    (0, 3, 1, 7, 5, 9, 8, 6, 4, 2),
    (7, 0, 9, 2, 1, 5, 4, 8, 6, 3),
    (4, 2, 0, 6, 8, 7, 1, 3, 5, 9),
    (1, 7, 5, 0, 9, 8, 3, 4, 2, 6),
    (6, 1, 2, 3, 0, 4, 5, 9, 7, 8),
    (3, 6, 7, 4, 2, 0, 9, 5, 8, 1),
    (5, 8, 6, 9, 7, 2, 0, 1, 3, 4),
    (8, 9, 4, 5, 3, 6, 2, 0, 1, 7),
    (9, 4, 3, 8, 6, 1, 7, 2, 0, 5),
    (2, 5, 8, 1, 4, 3, 6, 7, 9, 0)
)
TABLE_SIZE = len(DAMM_TABLE)


def _verify_table():
    assert TABLE_SIZE <= ord('Z') - ord('A')
    for i, row in enumerate(DAMM_TABLE):
        assert len(row) == TABLE_SIZE
        # Diagonal has to be zero
        assert row[i] == 0


def compute_checksum(n):
    # expand it to at least 5 characters (left padding with 0)
    nr = ("0000" + str(n))[-5:]
    i = _run_damm(nr)
    return 'SR' + nr + chr(i + ord('A'))


def _run_damm(nr: str):
    i = 0
    for digit in nr:
        i = DAMM_TABLE[i][int(digit)]
    return i


def _check_number_to_letter(n: int):
    return chr(n + ord('A'))


def _check_letter_to_number(c):
    return ord(c) - ord('A')


def determine_issue_with_usr(usr):
    if len(usr) != len("SRXXXXXY"):
        return False, "Input length is wrong (should be SRXXXXXY)"
    if usr != usr.upper():
        return False, "Input must use all uppercase letters"
    if not usr.startswith("SR"):
        return False, "Input must start with SR"
    check_char = usr[-1]
    nr = usr[2:-1]
    if not ('A' <= check_char < 'Z'):
        return False, "Check character must be a letter"
    actual_check_digit = ord(check_char) - ord('A')
    if actual_check_digit >= TABLE_SIZE:
        return False, "Check number must be in the range A-" + _check_number_to_letter(TABLE_SIZE)
    if not DIGITS_ONLY_PATTERN.fullmatch(nr):
        return False, f"The middle digits must be numbers (the XXXXX in SRXXXXXY, got {nr})"
    expected_check_digit = _run_damm(nr)
    if actual_check_digit != expected_check_digit:
        return False, "Check number does not match - number implies it should have been \"" \
               + _check_number_to_letter(expected_check_digit) + "\""
    return True, "OK"


def is_valid_usr(usr):
    return determine_issue_with_usr(usr)[0]


def _validate_usr(args):
    for usr in args.usr:
        _, msg = determine_issue_with_usr(usr)
        print(f"{usr}: {msg}")


def _generate_csv(args):
    field_names = [
        'Universal Service Reference',
        'Carrier',
        'Carrier Service Code',
        'Service Name'
    ]
    with open(args.output_file, 'w', newline='') as fd:
        writer = csv.DictWriter(fd, fieldnames=field_names)
        writer.writeheader()
        for n in range(0, 100_000):
            usr = compute_checksum(n)
            assert is_valid_usr(usr)
            row = dict(zip(field_names, chain([usr], repeat(''))))
            writer.writerow(row)


CMD_TABLE = {
    'validate-usr': _validate_usr,
    'generate-csv': _generate_csv,
}


def parse_args():
    parser = argparse.ArgumentParser(description="Utility program related to Universal Service References (USR)")
    subparsers = parser.add_subparsers(dest='subcommand', metavar='CMD')
    check = subparsers.add_parser('validate-usr', help="Validate provided USRs")
    check.add_argument("usr", nargs='+', action='extend')
    generate_csv = subparsers.add_parser('generate-csv', help="Generate CSV with USRs")
    generate_csv.add_argument("output_file", help="Where the CSV should be saved")
    parsed_args = parser.parse_args()
    if getattr(parsed_args, 'subcommand') is None:
        parser.print_usage()
        sys.exit(0)
    assert parsed_args.subcommand in CMD_TABLE
    return parsed_args


if __name__ == '__main__':
    args = parse_args()
    CMD_TABLE[args.subcommand](args)
