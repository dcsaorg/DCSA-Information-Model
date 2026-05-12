package org.dcsa.standards.specifications.standards.ebl.v303.model_iss;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Schema(
    description =
        org.dcsa.standards.specifications.standards.ebl.v302.model_iss.TransportDocument
            .CLASS_SCHEMA_DESCRIPTION)
@Getter
@Setter
@RequiredArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
public class TransportDocument
    extends org.dcsa.standards.specifications.standards.ebl.v302.model_iss.TransportDocument {

  @Schema(
      description =
"""
Number of originals of the Bill of Lading that have been requested by the customer with charges.

**Condition:** Only applicable if `transportDocumentType` = `BOL` (Bill of Lading). If `isElectronic = 'True'`, accepted value is `1` (one) or `0` (zero). `numberOfOriginalsWithoutCharges` + `numberOfOriginalsWithCharges` must be ≤ 1.
""",
      example = "1",
      minimum = "0",
      format = "int32")
  private Integer numberOfOriginalsWithCharges;

  @Schema(
      description =
"""
Number of originals of the Bill of Lading that have been requested by the customer without charges.

**Condition:** Only applicable if `transportDocumentType` = `BOL` (Bill of Lading). If `isElectronic = 'True'`, accepted value is `1` (one) or `0` (zero). `numberOfOriginalsWithoutCharges` + `numberOfOriginalsWithCharges` must be ≤ 1.
""",
      example = "1",
      minimum = "0",
      format = "int32")
  private Integer numberOfOriginalsWithoutCharges;

  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      description =
"""
The status of the `Transport Document`. Possible values are:
- `DRAFT` (The Transport Document is currently a Draft)
- `APPROVED` (The Transport Document has been Approved by consumer)
- `ISSUED` (The Transport Document has been Issued by provider)
- `PENDING_SURRENDER_FOR_AMENDMENT` (The Transport Document has a pending Surrender for Amendment)
- `SURRENDERED_FOR_AMENDMENT` (The Transport Document is Surrendered for Amendment)
- `PENDING_SURRENDER_FOR_DELIVERY` (The Transport Document has a pending Surrender for Delivery)
- `SURRENDERED_FOR_DELIVERY` (The Transport Document is Surrendered for Delivery)
- `VOIDED` (The Transport Document has been Voided)
""",
      example = "DRAFT",
      maxLength = 50)
  private String transportDocumentStatus;
}
