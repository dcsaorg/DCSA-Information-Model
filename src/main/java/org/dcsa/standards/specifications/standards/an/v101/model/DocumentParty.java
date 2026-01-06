package org.dcsa.standards.specifications.standards.an.v101.model;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.Data;
import org.dcsa.standards.specifications.standards.an.v101.types.PurchaseOrderReference;
import org.dcsa.standards.specifications.standards.core.v103.model.Address;

@Data
@Schema(description = "Document party")
public class DocumentParty {

  // https://www.stylusstudio.com/edifact/D03A/3035.htm
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

  @Schema(maxLength = 70, description = "Party name", example = "Acme Inc.")
  private String partyName;

  @Schema(description = "Party  address")
  private Address address;

  @Schema(description = "List of codes identifying the party")
  private List<IdentifyingCode> identifyingCodes;

  @Schema(description = "List of tax or legal references relevant to the party")
  private List<TaxLegalReference> taxLegalReferences;

  @Schema(description = "Party contact details")
  private List<PartyContactDetail> partyContactDetails;

  @Schema(
      description = "A reference linked to the document party.",
      example = "REF1234",
      maxLength = 35)
  private String reference;

  @Schema(description = "A list of `Purchase Order Reference`s linked to the document party.")
  private List<PurchaseOrderReference> purchaseOrderReferences;
}
