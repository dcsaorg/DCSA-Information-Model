package org.dcsa.standards.specifications.standards.an.v101.model;

import io.swagger.v3.oas.annotations.media.Schema;
import org.dcsa.standards.specifications.generator.ClearSchemaConstraints;

@Schema(
    description =
        org.dcsa.standards.specifications.standards.dt.v100.model.IdentifyingCode
            .CLASS_SCHEMA_DESCRIPTION)
@ClearSchemaConstraints
public class IdentifyingCode
    extends org.dcsa.standards.specifications.standards.dt.v100.model.IdentifyingCode {

  @Schema(
      description =
"""
A list of codes identifying a party. Possible values are:
- `WAVE` (Wave)
- `CARX` (CargoX)
- `IDT` (ICE Digital Trade)
- `BOLE` (Bolero)
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
}
