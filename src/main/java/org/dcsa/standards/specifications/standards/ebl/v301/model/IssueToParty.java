package org.dcsa.standards.specifications.standards.ebl.v301.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Schema(
    description =
        org.dcsa.standards.specifications.standards.ebl.v3.model.IssueToParty
            .CLASS_SCHEMA_DESCRIPTION)
@Getter
@Setter
@RequiredArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
public class IssueToParty
    extends org.dcsa.standards.specifications.standards.ebl.v3.model.IssueToParty {

  @Schema(
      description =
"""
The eBL platform of the transaction party.
The value **MUST** be one of:
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
- `COVA` (Covantis)
- `ETIT` (e-title)
- `KTNE` (KTNET)
- `CRED` (Credore)

**Condition:** Only applicable when `isElectronic=true` and `transportDocumentTypeCode=BOL`. The property **MUST** be absent for paper B/Ls (`isElectronic=false`)
""",
      example = "BOLE",
      pattern = "^\\S+$",
      maxLength = 4)
  private String sendToPlatform;
}
