#!/usr/bin/python3

import csv
import os
import sys


DOWNLOAD_URL = 'https://raw.githubusercontent.com/smdg-org/Terminal-Code-List/master/SMDG%20Terminal%20Code%20List.csv'
FIELDS_TO_KEEP = ['Terminal Facility Name', 'UNLOCODE', 'Terminal Code']
UN_LOCODE_FIELD = 'UNLOCODE'
FILENAME = "facilities.csv"


def usage():
    print("Usage: python3 " + os.path.basename(sys.argv[0]) + ".py path/to/datamodel/samples.d")
    sys.exit(1)


def parse_known_unlocodes(output_dir):
    known_unlocodes = None
    unlocode_file = os.path.join(output_dir, "unlocationcodes.csv")
    try:
        with open(unlocode_file) as fd:
            csv_reader = csv.DictReader(fd)
            known_unlocodes = {r["UN Location Code"] for r in csv_reader}
    except FileNotFoundError:
        pass

    if known_unlocodes is None:
        print("Expected to find " + unlocode_file + " to see which UN Location codes would imported.")
        print("Will generate the full list instead")
        known_unlocodes = None

    return known_unlocodes


def main():
    if len(sys.argv) > 2:
        usage()

    if len(sys.argv) == 1:
        output_dir = os.path.dirname(os.path.realpath(sys.argv[0]))
    else:
        output_dir = sys.argv[1]

    if not os.path.isdir(output_dir):
        print(output_dir + " must be an existing directory")
        sys.exit(1)

    try:
        import requests
    except ImportError:
        print("This script requires requests (pip3 install requests or apt install python3-requests)")
        sys.exit(1)

    known_unlocodes = parse_known_unlocodes(output_dir)

    content = requests.get(DOWNLOAD_URL).text
    if content[0] == '\ufeff':
        # Excel provided BOM character - remove it
        # https://www.freecodecamp.org/news/a-quick-tale-about-feff-the-invisible-character-cd25cd4630e7/
        content = content[1:]
    facilities_file = os.path.join(output_dir, FILENAME)
    new_facilities_file = facilities_file + ".new"
    with open(new_facilities_file, "w") as fd:
        csv_reader = csv.DictReader(content.splitlines())
        csv_writer = csv.DictWriter(fd, fieldnames=FIELDS_TO_KEEP)
        csv_writer.writeheader()
        for row in csv_reader:
            if known_unlocodes is not None and row[UN_LOCODE_FIELD] not in known_unlocodes:
                continue
            replacement_row = {f: row[f] for f in FIELDS_TO_KEEP}
            csv_writer.writerow(replacement_row)
    os.rename(new_facilities_file, facilities_file)
    print("Updated " + facilities_file)


if __name__ == '__main__':
    main()
