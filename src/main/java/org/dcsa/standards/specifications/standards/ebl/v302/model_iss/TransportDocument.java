package org.dcsa.standards.specifications.standards.ebl.v302.model_iss;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.dcsa.standards.specifications.standards.dt.v100.model.PartyContactDetail;

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
    extends org.dcsa.standards.specifications.standards.ebl.v301.model_td.TransportDocument {

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

  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      description =
          "The contact details of the Shipping Instructions requestor(s) to contact in relation to the **Transport Document** (changes, notifications etc.)")
  @ArraySchema(minItems = 1)
  private List<PartyContactDetail> partyContactDetails;
}
