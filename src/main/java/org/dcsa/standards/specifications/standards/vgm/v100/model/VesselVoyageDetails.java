package org.dcsa.standards.specifications.standards.vgm.v100.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.dcsa.standards.specifications.standards.core.v101.model.Location;
import org.dcsa.standards.specifications.standards.core.v101.model.VoyageNumberOrReference;

@Schema(description = "VGM declaration details specific to the vessel voyage")
@Data
public class VesselVoyageDetails {

  @Schema(
      description =
"""
The `SCAC` code (provided by [NMFTA](https://nmfta.org/scac/)) or `SMDG` code (provided by
[SMDG](https://smdg.org/documents/smdg-code-lists/smdg-liner-code-list/)) of the carrier.

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

  @Schema(maxLength = 50, example = "King of the Seas", description = "Vessel name")
  private String vesselName;
}
