package org.dcsa.standards.specifications.standards.ebl.v303.model_td;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.dcsa.standards.specifications.standards.core.v104.types.AddressLine;

@Schema(
    description =
"""
The party to which goods are consigned in the `Master Bill of Lading`.

**Condition:** Mandatory for non-negotiable BL (`isToOrder=false`). Note `Consignee` and `Endorsee` are mutually exclusive.

**Condition:** If a `displayedAddress` is provided, it must be included in the `Transport Document` instead of the `address` or `addressLines`.
""")
@Getter
@Setter
@RequiredArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
public class Consignee
    extends org.dcsa.standards.specifications.standards.ebl.v301.model_td.Consignee {

  @Schema(
      description =
"""
Unstructured address lines, used as a fallback when structured address fields are unavailable.
""")
  @ArraySchema(maxItems = 5)
  protected List<AddressLine> addressLines;

  @Schema(
      description =
"""
The eBL platform of the party. The value **MUST** be one of:
- `WAVE` (Wave)
- `CARX` (CargoX)
- `ESSD` (EssDOCS - This value has been **deprecated**. Please use `IDT`)
- `IDT` (ICE Digital Trade)
- `BOLE` (Bolero)
- `EDOX` (EdoxOnline)
- `IQAX` (IQAX)
- `SECR` (Secro)
- `TRGO` (TradeGO)
- `ETEU` (eTEU)
- `TRAC` (Enigio trace:original)
- `BRIT` (BRITC eBL)
- `COVA` (Covantis)
- `ETIT` (e-title)
- `KTNE` (KTNET)
- `CRED` (Credore)
- `BLOC` (BlockPeer Technologies)
- `DOCU` (DocuTrade)
- `AEOT` (AEOTrade)
- `SGTD` (SGTraDex)
""",
      example = "BOLE",
      pattern = "^\\S+$",
      maxLength = 4)
  private String eblPlatform;
}
