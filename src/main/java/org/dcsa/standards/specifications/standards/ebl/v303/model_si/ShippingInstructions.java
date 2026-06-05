package org.dcsa.standards.specifications.standards.ebl.v303.model_si;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Schema(
    description =
"""
The `Shipping Instructions` as provided by the Shipper.
""")
@Getter
@Setter
@RequiredArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
public class ShippingInstructions
    extends org.dcsa.standards.specifications.standards.ebl.v302.model_si.ShippingInstructions {

  @Schema(
      description =
          "The version number of the **Shipping Instructions** provided by the customer to ensure the carrier uses the most recent update when creating the **Transport Document**. This value is expected to increment by one with each **Shipping Instructions** update, with higher values representing newer versions.",
      example = "1",
      format = "int32")
  private Integer shippingInstructionsRevisionNumber;

  @Schema(
      description =
          "Indicates whether the **Carrier's agent at destination** name, address and contact details should be included in the `Transport Document`.",
      example = "false")
  private Boolean isCarriersAgentAtDestinationRequired;

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
The status of the `Shipping Instructions`. Possible values are:
- `RECEIVED` (Shipping Instructions have been received)
- `PENDING_UPDATE` (An update is required to the Shipping Instructions)
- `COMPLETED` (The Shipping Instructions can no longer be modified - the related Transport Document has been surrendered for delivery)
- `CANCELLED` (The Shipping Instructions have been cancelled by Shipper)
- `DECLINED` (The Shipping Instructions have been declined by Carrier)

**Condition:** When communicating with providers **or** consumers implementing API **v3.0.2 or earlier**, a sender implementing API **v3.0.3 or later MUST NOT** use `CANCELLED` or `DECLINED` values. Recipients implementing earlier versions **MAY ignore these values or treat the request as invalid**.
""",
      example = "RECEIVED",
      maxLength = 50)
  private String shippingInstructionsStatus;

  @Schema(
      description =
"""
The status of the latest update to the `Shipping Instructions`. If no update has been requested - then this property is empty. Possible values are:
- `UPDATE_RECEIVED` (An update to a Shipping Instructions has been received and is awaiting to be processed)
- `UPDATE_CONFIRMED` (An update to a Shipping Instructions has been confirmed)
- `UPDATE_CANCELLED` (An update to a Shipping Instructions is discontinued by consumer)
- `UPDATE_DECLINED` (An update to a Shipping Instructions is discontinued by provider)
""",
      example = "UPDATE_RECEIVED",
      maxLength = 50)
  private String updatedShippingInstructionsStatus;
}
