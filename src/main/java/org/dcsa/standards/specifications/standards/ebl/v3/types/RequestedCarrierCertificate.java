package org.dcsa.standards.specifications.standards.ebl.v3.types;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
    type = "string",
    description =
"""
Name of the certificate. Detailed information about carrier certificates can be found [here](https://dcsa.org/wp-content/uploads/2023/12/28-12-2023_Carrier-Certificates-shipment-voyage-particulars-and-vessel-particulars.pdf). Possible values are:
- `SHIPMENT_VOYAGE_PARTICULARS_1` (Shipment-Voyage Particulars 1)
- `SHIPMENT_VOYAGE_PARTICULARS_2` (Shipment-Voyage Particulars 2)
- `SHIPMENT_VOYAGE_PARTICULARS_3` (Shipment-Voyage Particulars 3)
- `SHIPMENT_VOYAGE_PARTICULARS_4` (Shipment-Voyage Particulars 4)
- `SHIPMENT_VOYAGE_PARTICULARS_5` (Shipment-Voyage Particulars 5)
- `SHIPMENT_VOYAGE_PARTICULARS_6` (Shipment-Voyage Particulars 6)
- `SHIPMENT_VOYAGE_PARTICULARS_7` (Shipment-Voyage Particulars 7)
- `VESSEL_PARTICULARS_1` (Vessel Particulars 1)
- `VESSEL_PARTICULARS_2` (Vessel Particulars 2)
- `VESSEL_PARTICULARS_3` (Vessel Particulars 3)
- `VESSEL_PARTICULARS_4` (Vessel Particulars 4)
- `VESSEL_PARTICULARS_5` (Vessel Particulars 5)
- `VESSEL_PARTICULARS_6` (Vessel Particulars 6)
- `VESSEL_PARTICULARS_7` (Vessel Particulars 7)
- `VESSEL_PARTICULARS_8` (Vessel Particulars 8)
- `VESSEL_PARTICULARS_9` (Vessel Particulars 9)
- `VESSEL_PARTICULARS_10` (Vessel Particulars 10)
- `VESSEL_PARTICULARS_11` (Vessel Particulars 11)
- `VESSEL_PARTICULARS_12` (Vessel Particulars 12)
- `VESSEL_PARTICULARS_13` (Vessel Particulars 13)
- `VESSEL_PARTICULARS_14` (Vessel Particulars 14)
- `VESSEL_PARTICULARS_15` (Vessel Particulars 15)
- `VESSEL_PARTICULARS_16` (Vessel Particulars 16)
- `VESSEL_PARTICULARS_17` (Vessel Particulars 17)
- `VESSEL_PARTICULARS_18` (Vessel Particulars 18)
""",
    example = "VESSEL_PARTICULARS_1",
    maxLength = 100)
public class RequestedCarrierCertificate {}
