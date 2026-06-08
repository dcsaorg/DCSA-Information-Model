package org.dcsa.standards.specifications.standards.dgd.v100.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.dcsa.standards.specifications.standards.core.v200.model.Location;
import org.dcsa.standards.specifications.standards.core.v200.model.VoyageNumberOrReference;
import org.dcsa.standards.specifications.standards.core.v200.types.FormattedDate;

@Schema(description = "DG declaration details specific to the vessel voyage")
@Data
public class VesselVoyageDetails {

  @Schema(
      description =
"""
The `SCAC` code (provided by [NMFTA](https://nmfta.org/scac/)) or `SMDG` code (provided by [SMDG](https://smdg.org/documents/smdg-code-lists/smdg-liner-code-list/)) of the carrier.

The `carrierCodeListProvider` specifies the provider of the list in which the `carrierCode` is defined.
""",
      example = "MMCU",
      maxLength = 4)
  private String carrierCode;

  @Schema(
      description =
"""
The code list provider for the `carrierCode`. Possible values are:
- `SMDG` (Ship Message Design Group)
- `NMFTA` (National Motor Freight Traffic Association)
""",
      example = "NMFTA",
      maxLength = 10)
  private String carrierCodeListProvider;

  @Schema(description = "Export voyage number or reference")
  private VoyageNumberOrReference exportVoyageNumberOrReference;

  @Schema(description = "Import voyage number or reference")
  private VoyageNumberOrReference importVoyageNumberOrReference;

  @Schema(
      description =
          "The location where the cargo is loaded onto a first sea-going vessel for water transportation.")
  private Location portOfLoading;

  @Schema(
      description = "The location where the cargo is discharged from the last sea-going vessel.")
  private Location portOfDischarge;

  @Schema(
      maxLength = 50,
      example = "King of the Seas",
      description =
          "Name of the first sea-going vessel on which the cargo is loaded or intended to be loaded.")
  private String vesselName;

  @Schema(
      description =
"""
The date of departure from the location where the cargo is handed over by the shipper, or his agent, to the shipping line. This can refer either to the Place of Receipt or the Port of Loading.
""")
  private FormattedDate departureDate;

  @Schema(description = "The final delivery destination of the goods")
  private Location destination;
}
