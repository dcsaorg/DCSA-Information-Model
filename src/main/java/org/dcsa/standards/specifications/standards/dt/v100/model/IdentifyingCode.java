package org.dcsa.standards.specifications.standards.dt.v100.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = IdentifyingCode.CLASS_SCHEMA_DESCRIPTION)
@Data
public class IdentifyingCode {

  public static final String CLASS_SCHEMA_DESCRIPTION =
      "Code-based identifier for a party. Includes the provider, code, and list name.";

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
- `COVA` (Covantis)
- `ETIT` (e-title)
- `KTNE` (KTNET)
- `CRED` (Credore)
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
      description = "Code to identify the party as provided by the code list provider",
      example = "MSK",
      maxLength = 150)
  protected String partyCode;

  @Schema(
      description =
"""
The name of the code list, code generation mechanism or code authority for the `partyCode`. Example values could be:
- `DID` (Decentralized Identifier) for `codeListProvider` `W3C`
- `LEI` (Legal Entity Identifier) for `codeListProvider` `GLEIF`
- `DUNS` (Data Universal Numbering System) for `codeListProvider` `DNB`
""",
      example = "DID",
      maxLength = 100)
  protected String codeListName;
}
