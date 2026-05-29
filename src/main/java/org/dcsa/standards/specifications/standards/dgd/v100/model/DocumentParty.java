package org.dcsa.standards.specifications.standards.dgd.v100.model;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.Data;
import org.dcsa.standards.specifications.standards.core.v200.model.Address;

@Schema(description = "Document party information.")
@Data
public class DocumentParty {

  @Schema(description = "Party address")
  private Address address;

  @Schema(description = "List of codes identifying the party")
  private List<IdentifyingCode> identifyingCodes;

  @Schema(description = "Party contact details")
  private List<PartyContactDetail> partyContactDetails;

  @Schema(
      description =
"""
Specifies the role of the party in a given context. Possible values are:

- `OS` (Shipper)
- `CN` (Consignee)
- `END` (Endorsee)
- `RW` (Issuing Party)
- `CG` (Carrier's Agent at Destination)
- `N1` (First Notify Party)
- `N2` (Second Notify Party)
- `NI` (Other Notify Party)
- `SCO` (Service Contract Owner)
- `DDR` (Consignor's freight forwarder)
- `DDS` (Consignee's freight forwarder)
- `COW` (Invoice payer on behalf of the consignor (shipper))
- `COX` (Invoice payer on behalf of the consignee)
- `CS` (Consolidator)
- `MF` (Manufacturer)
- `WH` (Warehouse Keeper)
""",
      example = "N1",
      maxLength = 3)
  private String partyFunction;

  @Schema(description = "Party name", example = "Acme Inc.", maxLength = 100)
  private String partyName;

  @Schema(
      description = "A reference linked to the document party.",
      example = "REF1234",
      maxLength = 100)
  private String reference;

  @Schema(description = "List of tax or legal references relevant to the party")
  private List<TaxLegalReference> taxLegalReferences;
}
