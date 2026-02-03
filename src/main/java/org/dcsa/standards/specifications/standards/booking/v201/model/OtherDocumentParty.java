package org.dcsa.standards.specifications.standards.booking.v201.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Schema(
    description =
        org.dcsa.standards.specifications.standards.booking.v2.model.OtherDocumentParty
            .CLASS_SCHEMA_DESCRIPTION)
@Getter
@Setter
@RequiredArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
public class OtherDocumentParty
    extends org.dcsa.standards.specifications.standards.booking.v2.model.OtherDocumentParty {

  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      description =
"""
Specifies the role of the party in a given context. Possible values are:

- `DDR` (Consignor's freight forwarder)
- `DDS` (Consignee's freight forwarder)
- `COW` (Invoice payer on behalf of the consignor (shipper))
- `COX` (Invoice payer on behalf of the consignee)
- `N1` (First Notify Party)
- `N2` (Second Notify Party)
- `NI` (Other Notify Party)
- `NAC` (Named Account Customer)
""",
      example = "DDS",
      maxLength = 3)
  private String partyFunction;
}
