package org.dcsa.standards.specifications.standards.ebl.v303.model_sur;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Schema(
    description =
"""
Refers to a company or a legal entity.

**Note:** It is possible to use a '**No Party**' placeholder when the `actionCode` is one of the following values: `SIGN`, `BLANK ENDORSE` or `SURRENDERED` (as none of these `actionCodes` require a `recipient`). A '**No Party**' object MUST be populated with the following values:
```
  {
    'eblPlatform': 'NONE',
    'partyName': 'NONE',
    'identifyingCodes': [
      {
        'codeListProvider': 'NONE',
        'partyCode': 'NONE',
      }
    ]
  }
```
""")
@Getter
@Setter
@RequiredArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
public class RecipientParty
    extends org.dcsa.standards.specifications.standards.ebl.v303.model_end.RecipientParty {

  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      description =
"""
The eBL platform of the `Recipient Party`. The value **MUST** be one of:
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
- `NONE` (To be used as part of the '**No Party**' object when `actionCode` is `SIGN`, `BLANK ENDORSE` or `SURRENDERED`)
""",
      example = "BOLE",
      pattern = "^\\S+$",
      maxLength = 4)
  private String eblPlatform;
}
