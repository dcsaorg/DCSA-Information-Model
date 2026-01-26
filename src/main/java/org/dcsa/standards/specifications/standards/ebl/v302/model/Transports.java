package org.dcsa.standards.specifications.standards.ebl.v302.model;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.Data;

@Schema(
    description =
        "Transport information for the shipment including ports, places, and planned dates.")
@Data
public class Transports {

  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      description = "The planned date of arrival.",
      example = "2024-06-07",
      format = "date")
  private String plannedArrivalDate;

  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      description = "The planned date of departure.",
      example = "2024-06-03",
      format = "date")
  private String plannedDepartureDate;

  @Schema(
      description =
"""
Mode of transportation for pre-carriage when transport to the port of loading is organized by the carrier. If this attributes is populated, then a Place of Receipt must also be defined. The currently supported values include:
- `VESSEL` (Vessel)
- `RAIL` (Rail)
- `TRUCK` (Truck)
- `BARGE` (Barge)
- `MULTIMODAL` (if multiple modes are used)
""",
      example = "RAIL",
      maxLength = 50)
  private String preCarriageBy;

  @Schema(
      description =
"""
Mode of transportation for on-carriage when transport from the port of discharge is organized by the carrier. If this attributes is populated, then a Place of Delivery must also be defined. The currently supported values include:
- `VESSEL` (Vessel)
- `RAIL` (Rail)
- `TRUCK` (Truck)
- `BARGE` (Barge)
- `MULTIMODAL` (if multiple modes are used)
""",
      example = "TRUCK",
      maxLength = 50)
  private String onCarriageBy;

  @Schema(
      description = "The place where the carrier takes receipt of the goods for transportation.")
  private PlaceOfReceipt placeOfReceipt;

  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      description = "The port where the cargo is loaded onto the vessel.")
  private PortOfLoading portOfLoading;

  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      description = "The port where the cargo is discharged from the vessel.")
  private PortOfDischarge portOfDischarge;

  @Schema(description = "The place where the carrier delivers the goods to the consignee.")
  private PlaceOfDelivery placeOfDelivery;

  @Schema(description = "Optional information about the onward inland routing.")
  private OnwardInlandRouting onwardInlandRouting;

  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      description =
"""
Allow the possibility to include multiple vessels/voyages in the `Transport Document` (e.g. the first sea going vessel and the mother vessel). At least one is mandatory to provide.
""")
  @ArraySchema(minItems = 1)
  private List<VesselVoyage> vesselVoyages;
}
