package org.dcsa.standards.specifications.standards.ebl.v302.model_si;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Schema(
    description =
        org.dcsa.standards.specifications.standards.ebl.v301.model_si.ShippingInstructions
            .CLASS_SCHEMA_DESCRIPTION)
@Getter
@Setter
@RequiredArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
public class ShippingInstructions
    extends org.dcsa.standards.specifications.standards.ebl.v301.model_si.ShippingInstructions {

  @Schema(
      description =
    """
Number of originals of the Bill of Lading that has been requested by the customer with charges.

**Condition:** Only applicable if `transportDocumentType` = `BOL` (Bill of Lading). If `isElectronic = 'True'`, accepted value is `1` (one) or `0` (zero). `numberOfOriginalsWithoutCharges` + `numberOfOriginalsWithCharges` must be ≤ 1.
""",
      example = "1",
      minimum = "0",
      format = "int32")
  private Integer numberOfOriginalsWithCharges;

  @Schema(
    description =
"""
Number of originals of the Bill of Lading that has been requested by the customer without charges.

**Condition:** Only applicable if `transportDocumentType` = `BOL` (Bill of Lading). If `isElectronic = 'True'`, accepted value is `1` (one) or `0` (zero). `numberOfOriginalsWithoutCharges` + `numberOfOriginalsWithCharges` must be ≤ 1.
""",
    example = "1",
    minimum = "0",
    format = "int32")
  private Integer numberOfOriginalsWithoutCharges;
}
