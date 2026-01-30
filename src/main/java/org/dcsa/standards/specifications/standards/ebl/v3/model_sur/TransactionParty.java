package org.dcsa.standards.specifications.standards.ebl.v3.model_sur;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Schema(
    description =
        org.dcsa.standards.specifications.standards.ebl.v3.model_end.ActorParty
            .CLASS_SCHEMA_DESCRIPTION)
@Getter
@Setter
@RequiredArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
public class TransactionParty
    extends org.dcsa.standards.specifications.standards.ebl.v3.model_end.ActorParty {

  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      description =
"""
The eBL platform of the transaction party. The value **MUST** be one of:
- `WAVE` (Wave)
- `CARX` (CargoX)
- `ESSD` (EssDOCS)
- `IDT` (ICE Digital Trade)
- `BOLE` (Bolero)
- `EDOX` (EdoxOnline)
- `IQAX` (IQAX)
- `SECR` (Secro)
- `TRGO` (TradeGO)
- `ETEU` (eTEU)
- `TRAC` (Enigio trace:original)
- `BRIT` (BRITC eBL)
""",
      example = "BOLE",
      pattern = "^\\S+$",
      maxLength = 4)
  private String eblPlatform;
}
