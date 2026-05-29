package org.dcsa.standards.specifications.standards.dgd.v100.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.dcsa.standards.specifications.standards.core.v200.model.Address;
import org.dcsa.standards.specifications.standards.core.v200.types.FormattedDate;

@Schema(
    description =
"""
"I hereby declare that the goods described above have been packed/loaded into the container/vehicle identified above in accordance with the applicable provisions". MUST BE COMPLETED AND SIGNED FOR ALL CONTAINER/VEHICLE LOADS BY PERSON RESPONSIBLE FOR PACKING/LOADING
""")
@Data
public class ContainerPackingCertificate {

  @Schema(
      description = "Name of the company responsible for packing/loading the container or vehicle.",
      example = "ABC Warehouse",
      maxLength = 100)
  private String companyName;

  @Schema(
      description = "Name of the person responsible for the packing/loading declaration.",
      example = "John Loader",
      maxLength = 100)
  private String declarantName;

  @Schema(
      description = "Role/status of the person signing the container/vehicle packing certificate.",
      example = "Warehouse Supervisor",
      maxLength = 100)
  private String declarantStatus;

  @Schema(description = "Place where the packing/loading declaration was made.")
  private Address declarationPlace;

  @Schema(description = "Date when the container/vehicle packing certificate was signed.")
  private FormattedDate declarationDate;

  @Schema(
      description =
"""
Upper case name of the person authorized to sign the certificate.

According to IMDG Code 5.4.2.3: "If the container/vehicle packing certificate is presented to the carrier by means of EDP or EDI transmission techniques, the signature(s) may be electronic signature(s) or may be replaced by the name(s) (in capitals) of the person authorized to sign."
""",
      example = "JOHN LOADER",
      maxLength = 100)
  private String signatory;
}
