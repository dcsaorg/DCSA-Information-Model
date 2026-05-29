package org.dcsa.standards.specifications.standards.dgd.v100.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.dcsa.standards.specifications.standards.core.v200.types.FormattedDate;

@Schema(
    description =
"""
"Received the above number of packages/containers/trailers in apparent good order and condition, unless stated on the remarks"
""")
@Data
public class ReceivingOrganisationReceipt {

  @Schema(
      description =
          "Indicates whether the packages, containers, or trailers were received in apparent good order and condition at the time of handover. A value of false implies that damages, discrepancies, or irregularities were observed and should be detailed in the remarks field.")
  private Boolean receivedInGoodCondition;

  @Schema(
      description = "Name of the organisation receiving the cargo.",
      example = "ECT Delta Terminal B.V.",
      maxLength = 100)
  private String receivingOrganisationName;

  @Schema(
      description =
          "The date on which the receiving organisation physically accepted the consignment.")
  private FormattedDate receiptDate;

  @Schema(
      description =
          "Free-text field for the receiving organisation to record any observations at the time of receipt.",
      example = "No visible damage",
      maxLength = 500)
  private String remarks;
}
