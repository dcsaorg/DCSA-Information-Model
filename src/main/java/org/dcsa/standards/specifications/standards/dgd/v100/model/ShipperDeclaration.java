package org.dcsa.standards.specifications.standards.dgd.v100.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.dcsa.standards.specifications.standards.core.v200.model.Address;
import org.dcsa.standards.specifications.standards.core.v200.types.FormattedDate;

@Schema(
    description =
"""
"I hereby declare that the contents of this consignment are fully and accurately described below by the proper shipping name, and are classified, packaged, marked and labelled/placarded and are in all respects in proper condition for transport according to the applicable international and national governmental regulations."
""")
@Data
public class ShipperDeclaration {

  @Schema(
      description = "Name of the shipper/consignor company making the dangerous goods declaration.",
      example = "ABC Chemicals Ltd",
      maxLength = 100)
  private String companyName;

  @Schema(
      description = "Name of the person signing on behalf of the shipper.",
      example = "Jane Smith",
      maxLength = 100)
  private String declarantName;

  @Schema(
      description = "Role/status of the shipper's authorized signatory.",
      example = "Dangerous Goods Manager",
      maxLength = 100)
  private String declarantStatus;

  @Schema(description = "Place where the shipper declaration was made.")
  private Address declarationPlace;

  @Schema(description = "Date when the shipper declaration was signed.")
  private FormattedDate declarationDate;

  @Schema(
      description =
"""
Upper case name of the person authorized to sign the declaration.

According to IMDG Code 5.4.1.6.2: "If the dangerous goods documentation is presented to the carrier by means of EDP or EDI transmission techniques, the signature(s) may be electronic signature(s) or may be replaced by the name(s) (in capitals) of the person authorized to sign."
""",
      example = "JANE SMITH",
      maxLength = 100)
  private String signatory;
}
