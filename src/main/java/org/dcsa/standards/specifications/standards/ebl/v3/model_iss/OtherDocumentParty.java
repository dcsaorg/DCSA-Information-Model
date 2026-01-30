package org.dcsa.standards.specifications.standards.ebl.v3.model_iss;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Schema(
    description =
        org.dcsa.standards.specifications.standards.ebl.v3.model_td.OtherDocumentParty
            .CLASS_SCHEMA_DESCRIPTION)
@Getter
@Setter
@RequiredArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
public class OtherDocumentParty
    extends org.dcsa.standards.specifications.standards.ebl.v3.model_td.OtherDocumentParty {

  @Schema(
    requiredMode = Schema.RequiredMode.REQUIRED,
    description =
"""
Specifies the role of the party in a given context. Possible values are:

- `SCO` (Service Contract Owner)
- `DDR` (Consignor's freight forwarder)
- `DDS` (Consignee's freight forwarder)
- `COW` (Invoice payer on behalf of the consignor (shipper))
- `COX` (Invoice payer on behalf of the consignee)
""",
    example = "DDS",
    maxLength = 3)
  private String partyFunction;
}
