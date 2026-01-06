package org.dcsa.standards.specifications.standards.an.v101.model;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.Data;
import org.dcsa.standards.specifications.standards.core.v101.types.FormattedDate;

@Data
@Schema(description = "Compiles the information relevant to shipment release.")
public class ReleaseInformation {

  @Schema(
      example = "false",
      description = "Indicates whether the shipment has been released via email.")
  private Boolean isReleasedByEmail;

  @Schema(
      example = "false",
      description =
          "Indicates that the Original Bill of Lading (OBL) has been surrendered to the carrier.")
  private Boolean isOBLReceived;

  @Schema(
      example = "false",
      description =
"""
Indicates whether the shipment has been "freight released", meaning the B/L has been surrendered
and all required freight payments have been received by the carrier.
""")
  private Boolean isFreightReleased;

  @Schema(
      description = "Date when the customer has provided the payment and the relevant documents.")
  private FormattedDate freightReleaseDate;

  @Schema(
      example = "false",
      description = "Indicates whether the shipment has been cleared by customs.")
  private Boolean isCustomsReleased;

  @Schema(
      description =
"""
The party to contact in relation to the cargo release (e.g. a shipping agency other than the POD carrier agency).
""")
  private List<PartyContactDetail> carrierInformationForCargoRelease;
}
