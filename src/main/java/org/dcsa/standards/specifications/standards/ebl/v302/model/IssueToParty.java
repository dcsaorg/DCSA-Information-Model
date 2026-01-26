package org.dcsa.standards.specifications.standards.ebl.v302.model;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.Data;

@Schema(
    description = "The party that receives **possession** of the original Bill of Lading upon issuance.",
    title = "Issue To Party")
@Data
public class IssueToParty {

  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      description = "Name of the party.",
      example = "Globeteam",
      pattern = "^\\S(?:.*\\S)?$",
      maxLength = 70)
  private String partyName;

  @Schema(
      description =
"""
The eBL platform of the transaction party.
The value **MUST** be one of:
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

**Condition:** Only applicable when `isElectronic=true` and `transportDocumentTypeCode=BOL`. The property **MUST** be absent for paper B/Ls (`isElectronic=false`)
""",
      example = "BOLE",
      pattern = "^\\S+$",
      maxLength = 4)
  private String sendToPlatform;

  @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
  @ArraySchema(minItems = 1)
  private List<IdentifyingCode> identifyingCodes;

  @Schema(description = "A list of `Tax References` for a `Party`")
  private List<TaxLegalReference> taxLegalReferences;
}
