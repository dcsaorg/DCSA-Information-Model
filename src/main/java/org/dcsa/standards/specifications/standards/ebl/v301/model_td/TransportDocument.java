package org.dcsa.standards.specifications.standards.ebl.v301.model_td;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Schema(
    description =
        org.dcsa.standards.specifications.standards.ebl.v3.model_td.TransportDocument
            .CLASS_SCHEMA_DESCRIPTION)
@Getter
@Setter
@RequiredArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
public class TransportDocument
    extends org.dcsa.standards.specifications.standards.ebl.v3.model_td.TransportDocument {

  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      description =
          "A unique number allocated by the shipping line to the `Transport Document` and the main number used for the tracking of the status of the shipment.",
      example = "HHL71800000",
      maxLength = 20,
      pattern = "^\\S(?:.*\\S)?$")
  private String transportDocumentReference;

  @Schema(
    requiredMode = Schema.RequiredMode.REQUIRED,
    description =
"""
The status of the `Transport Document`. Possible values are:
- `DRAFT` (the Transport Document is currently a Draft)
- `APPROVED` (the Transport Document has been Approved by consumer)
- `ISSUED` (the Transport Document has been Issued by provider)
- `PENDING_SURRENDER_FOR_AMENDMENT` (the Transport Document has a pending Surrender for Amendment)
- `SURRENDERED_FOR_AMENDMENT` (the Transport Document is Surrendered for Amendment)
- `PENDING_SURRENDER_FOR_DELIVERY` (the Transport Document has a pending Surrender for Delivery)
- `SURRENDERED_FOR_DELIVERY` (the Transport Document is Surrendered for Delivery)
- `VOIDED` (the Transport Document has been Voided)
""",
    example = "DRAFT",
    maxLength = 50)
  private String transportDocumentStatus;
}
