package org.dcsa.standards.specifications.standards.ebl.v302.model_td;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Schema(
    description =
"""
A list of document parties that can be optionally provided in the `Transport Document`.
""")
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
