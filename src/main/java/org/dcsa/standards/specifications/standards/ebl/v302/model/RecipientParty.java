package org.dcsa.standards.specifications.standards.ebl.v302.model;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.util.List;

@Schema(description = "Refers to a company or a legal entity.", title = "Recipient Party")
@Data
public class RecipientParty {

  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      description = "Name of the party.",
      example = "Globeteam",
      pattern = "^\\S(?:.*\\S)?$",
      maxLength = 70)
  private String partyName;

  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      description =
          """
The eBL platform of the `Recipient Party`.
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
""",
      example = "BOLE",
      pattern = "^\\S+$",
      maxLength = 4)
  private String eblPlatform;

  @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
  @ArraySchema(minItems = 1)
  private List<IdentifyingCode> identifyingCodes;

  @Schema(description = "A list of `Tax References` for a `Party`")
  private List<TaxLegalReference> taxLegalReferences;

  @Schema private RepresentedRecipientParty representedParty;
}
