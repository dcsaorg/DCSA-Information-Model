package org.dcsa.standards.specifications.standards.booking.v202.model;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.dcsa.standards.specifications.standards.booking.v202.types.TransportDocumentReference;
import org.dcsa.standards.specifications.standards.dt.v100.model.PartyContactDetail;

@Schema(
    description =
        org.dcsa.standards.specifications.standards.booking.v201.model.Booking
            .CLASS_SCHEMA_DESCRIPTION)
@Getter
@Setter
@RequiredArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
public class Booking
    extends org.dcsa.standards.specifications.standards.booking.v201.model.Booking {

  @Schema(
      description =
"""
The date when the shipment is expected to be loaded on board a vessel at `Port of Loading` as provided by the shipper or its agent.

**Condition:** Mandatory if vessel/voyage/service details or `expectedArrivalAtPlaceOfDeliveryDate` or `expectedDepartureFromPlaceOfReceiptDate` (at PRE) is not provided. If `routingReference` is provided - this property MUST not be provided.
""",
      example = "2021-05-17",
      format = "date")
  private String expectedDepartureDate;

  @Schema(
      description =
          """
          The date when the shipment is expected to be handed over to the carrier at `Place of Receipt` as provided by the shipper or its agent.

          **Condition:** Mandatory if vessel/voyage/service details or `expectedArrivalAtPlaceOfDeliveryDate` or `expectedDepartureDate` (at POL) is not provided.
          """,
      example = "2021-05-17",
      format = "date")
  private String expectedDepartureFromPlaceOfReceiptDate;

  @Schema(
      description =
"""
The contact details of the Booking requestor(s) to contact in relation to the **Booking** (changes, notifications etc.)
""")
  private List<PartyContactDetail> partyContactDetails;

  @Schema(
      description =
"""
The number of `Transport Documents` requested to be linked to this Booking by the Shipper.
""",
      example = "3",
      minimum = "1")
  private Integer requestedNumberOfTransportDocuments;

  @Schema(
      description =
"""
The `Mode of Transport` requested ​by the shipper for the **pre carriage**. The supported values include:
- `VESSEL` (Vessel)
- `RAIL` (Rail)
- `TRUCK` (Truck)
- `BARGE` (Barge)
- `RAIL_TRUCK`(Rail and truck)
- `BARGE_TRUCK`(Barge and truck)
- `BARGE_RAIL`(Barge and rail)
- `MULTIMODAL` (if multiple modes are used)
""",
      maxLength = 50)
  private String requestedPreCarriageModeOfTransport;

  @Schema(
      description =
"""
The `Mode of Transport` requested ​by the shipper for the **on carriage**. The supported values include:
- `VESSEL` (Vessel)
- `RAIL` (Rail)
- `TRUCK` (Truck)
- `BARGE` (Barge)
- `RAIL_TRUCK`(Rail and truck)
- `BARGE_TRUCK`(Barge and truck)
- `BARGE_RAIL`(Barge and rail)
- `MULTIMODAL` (if multiple modes are used)
""",
      example = "VESSEL",
      maxLength = 50)
  private String requestedOnCarriageModeOfTransport;

  @Schema(
      description =
"""
A unique reference allocated by the shipping line to the `Transport Document` that the booking concerns.

**Deprecated:** In case both provider and consumer are on API v2.0.2 (or later), please use `transportDocumentReferences` property instead
""",
      example = "reserved-HHL123",
      maxLength = 20,
      pattern = "^\\S(?:.*\\S)?$")
  private String transportDocumentReference;

  @Schema(
      description = "An array of `transportDocumentReference` to be associated to this Booking.")
  private List<TransportDocumentReference> transportDocumentReferences;
}
