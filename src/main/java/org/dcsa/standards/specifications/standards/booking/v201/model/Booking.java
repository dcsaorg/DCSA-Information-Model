package org.dcsa.standards.specifications.standards.booking.v201.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Schema(
    description =
        org.dcsa.standards.specifications.standards.booking.v2.model.Booking
            .CLASS_SCHEMA_DESCRIPTION)
@Getter
@Setter
@RequiredArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
public class Booking extends org.dcsa.standards.specifications.standards.booking.v2.model.Booking {

  @Schema(
      description =
"""
The carrier specific identifier of the export Voyage.

**Condition:** Mandatory if `expectedDepartureDate` or `expectedArrivalAtPlaceOfDeliveryStartDate` and `expectedArrivalAtPlaceOfDeliveryEndDate` is not provided. If `routingReference` is provided - this property MUST not be provided.
""",
      example = "2103S",
      maxLength = 50,
      pattern = "^\\S(?:.*\\S)?$")
  private String carrierExportVoyageNumber;

  @Schema(
      description =
"""
The carrier specific code of the service for which the schedule details are published.

**Condition:** Mandatory if `carrierExportVoyageNumber` is provided and Vessel details or `carrierServiceName` are blank. If `routingReference` is provided - this property MUST not be provided.
""",
      example = "FE1",
      maxLength = 11,
      pattern = "^\\S(?:.*\\S)?$")
  private String carrierServiceCode;

  @Schema(
      description =
"""
The name of a service as specified by the carrier.

**Condition:** Mandatory if `carrierExportVoyageNumber` is provided and Vessel details or `carrierServiceCode` are blank. If `routingReference` is provided - this property MUST not be provided.
""",
      example = "Great Lion Service",
      maxLength = 50,
      pattern = "^\\S(?:.*\\S)?$")
  private String carrierServiceName;

  @Schema(
      description =
"""
The end date (provided as a range together with `expectedArrivalAtPlaceOfDeliveryStartDate`) for when the shipment is expected to arrive at `Place Of Delivery`.

**Condition:** Mandatory if vessel/voyage/service details or `expectedDepartureDate` is not provided. If `routingReference` is provided - this property MUST not be provided.
""",
      example = "2021-05-19",
      format = "date")
  private String expectedArrivalAtPlaceOfDeliveryEndDate;

  @Schema(
      description =
"""
The start date (provided as a range together with `expectedArrivalAtPlaceOfDeliveryEndDate`) for when the shipment is expected to arrive at `Place Of Delivery`.

**Condition:** Mandatory if vessel/voyage/service details or `expectedDepartureDate` is not provided. If `routingReference` is provided - this property MUST not be provided.
""",
      example = "2021-05-17",
      format = "date")
  private String expectedArrivalAtPlaceOfDeliveryStartDate;

  @Schema(
      description =
"""
The date when the shipment is expected to be loaded on board a vessel as provided by the shipper or its agent.

**Condition:** Mandatory if vessel/voyage/service details or `expectedArrivalAtPlaceOfDeliveryDate` is not provided. If `routingReference` is provided - this property MUST not be provided.
""",
      example = "2021-05-17",
      format = "date")
  private String expectedDepartureDate;

  @Schema(
      description =
"""
A reference to a predefined `route` specified in **Commercial Schedules - Point to Point**. When specifying this property - it is not needed to specify the following properties:
  - `vessel` which includes:
    - `vesselName`
    - `vesselIMONumber`
  - `carrierServiceName`
  - `carrierServiceCode`
  - `universalServiceReference`
  - `carrierExportVoyageNumber`
  - `universalExportVoyageReference`
  - `expectedDepartureDate`
  - `expectedArrivalAtPlaceOfDeliveryStartDate` or `expectedArrivalAtPlaceOfDeliveryEndDate`
  - the following `locationTypeCode` in `shipmentLocations`:
    - `PRE` (Place of Receipt)
    - `POL` (Port of Loading)
    - `POD` (Port of Discharge)
    - `PDE` (Place of Delivery)
""",
      maxLength = 5000,
      pattern = "^\\S(?:.*\\S)?$",
      example = "Route123")
  private String routingReference;

  @Schema(
      description =
"""
A global unique voyage reference for the export Voyage, as per DCSA standard, agreed by VSA partners for the voyage. The voyage reference must match the regular expression pattern: `\\d{2}[0-9A-Z]{2}[NEWSR]`
- `2 digits` for the year
- `2 alphanumeric characters` for the sequence number of the voyage
- `1 character` for the direction/haul (`N`orth, `E`ast, `W`est, `S`outh or `R`oundtrip).

**Condition:** If `routingReference` is provided - this property MUST not be provided.
""",
      example = "2103N",
      maxLength = 5,
      minLength = 5,
      pattern = "^\\d{2}[0-9A-Z]{2}[NEWSR]$")
  private String universalExportVoyageReference;

  @Schema(
      description =
"""
A global unique service reference, as per DCSA standard, agreed by VSA partners for the service. The service reference must match the regular expression pattern: `SR\\d{5}[A-Z]`. The letters `SR` followed by `5 digits`, followed by a checksum-character as a capital letter from `A to Z`.

**Condition:** If `routingReference` is provided - this property MUST not be provided.
""",
      example = "SR12345A",
      maxLength = 8,
      minLength = 8,
      pattern = "^SR\\d{5}[A-Z]$")
  private String universalServiceReference;
}
