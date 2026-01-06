package org.dcsa.standards.specifications.standards.an.v101.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.dcsa.standards.specifications.standards.core.v103.model.Location;

@Data
@Schema(description = "Customs clearance information")
public class CustomsClearance {

  @Schema(description = "The location where customs clearance occurs.")
  private Location customsClearancePoint;

  @Schema() private ImmediateTransportationEntry immediateTransportationEntry;

  @Schema(
      maxLength = 1000,
      description =
"""
Instructions on the administrative processes for submitting import customs declarations to the local agency.
""")
  private String customsImportDeclarationProcedure;

  @Schema(
      maxLength = 35,
      example = "24NDMUA430I",
      description =
"""
Manifest reference number for manifest declaration and customs clearance in Korea.
""")
  private String manifestReferenceNumber;

  @Schema(
      maxLength = 35,
      example = "23",
      description =
"""
A unique sequential number assigned by the shipping line to each Master Bill of Lading issued under a specific voyage of
a particular vessel. Customers use this Master Sequence Number (MSN) as a reference when declaring all related House B/Ls.
""")
  private String masterSequenceNumber;
}
