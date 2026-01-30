package org.dcsa.standards.specifications.standards.ebl.v3.model_sur;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Schema(
    description =
        org.dcsa.standards.specifications.standards.ebl.v3.model.IdentifyingCode
            .CLASS_SCHEMA_DESCRIPTION)
@Getter
@Setter
@RequiredArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
public class IdentifyingCode
    extends org.dcsa.standards.specifications.standards.ebl.v3.model.IdentifyingCode {

  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      description =
"""
A list of codes identifying a party. Possible values are:
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
- `GSBN` (Global Shipping Business Network)
- `WISE` (WiseTech)
- `GLEIF` (Global Legal Entity Identifier Foundation)
- `W3C` (World Wide Web Consortium)
- `DNB` (Dun and Bradstreet)
- `FMC` (Federal Maritime Commission)
- `DCSA` (Digital Container Shipping Association)
- `ZZZ` (Mutually defined)
""",
      example = "W3C",
      maxLength = 100)
  protected String codeListProvider;

  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      description = "Code to identify the party as provided by the `codeListProvider`",
      example = "MSK",
      maxLength = 150)
  protected String partyCode;
}
