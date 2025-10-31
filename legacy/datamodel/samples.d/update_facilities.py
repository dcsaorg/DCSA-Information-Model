#!/usr/bin/python3

import csv
import os
import sys
import pandas as pd

"""
 SMDG and BIC provide facility codes via API,
 The below urls are either full or partial samples of the facilities they provide codes for.
"""
SMDG_DOWNLOAD_URL = 'https://raw.githubusercontent.com/smdg-org/Terminal-Code-List/master/SMDG%20Terminal%20Code%20List.csv'
BIC_DOWNLOAD_URL = 'https://raw.githubusercontent.com/bic-org/Facility-Code/master/sample-bic-facility-codes.csv'

HEADER_FIELDS = ['Facility Name', 'UNLOCODE', 'Facility SMDG Code', 'Facility BIC Code']
FIELDS_TO_KEEP = ['Terminal Facility Name', 'Facility Name', 'UNLOCODE', 'Terminal Code', 'Facility BIC Code']

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
        import pandas
    except ImportError:
        print("This script requires pandas (pip3 install pandas - https://pandas.pydata.org/docs/getting_started/install.html)")
        sys.exit(1)

    known_unlocodes = parse_known_unlocodes(output_dir)

    # read SMDG and BIC facilities in and align the columns to DCSA facility entity fieldnames
    df = pd.concat(map(pd.read_csv, [SMDG_DOWNLOAD_URL, BIC_DOWNLOAD_URL]))
    # For historical reasons, some facilities are defined in multiple UN Location codes.
    # E.g., The terminal "HJNC" in Busan is defined for both KRPUS and KRBNP.  Therefore, we
    # also need the UN location codes from the "Alternative UNLOCODEs" column
    df['UNLOCODE'] = df[['UNLOCODE', 'Alternative UNLOCODEs']].fillna('')\
        .agg(' '.join, axis='columns')\
        .map(lambda x: x.strip().split())
    df = df.explode('UNLOCODE')
    df = df[FIELDS_TO_KEEP]
    # remove rows with unknown UNLOCODES from list
    df = df[df.UNLOCODE.isin(known_unlocodes)]
    df = df.rename(columns={"Terminal Code": "Facility SMDG Code"})
    df['Facility Name'] = df[['Terminal Facility Name', 'Facility Name']].fillna('').agg(''.join, axis='columns')
    df = df.drop(columns=['Terminal Facility Name'])
    df['Facility BIC Code'] = df['Facility BIC Code'].str[5:]
    # write the facilities.csv file
    facilities_file = os.path.join(output_dir, FILENAME)
    df.to_csv(facilities_file, index=False)
    print("Updated " + facilities_file)

if __name__ == '__main__':
    main()
