package org.dcsa.standards.specifications.standards.core.v102.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "Identification of a container shipping party")
@Data
public class Party {

  @Schema(
      description =
"""
Code identifying the party as per the `codeListProvider` and `codeListName`.
""",
      example = "MSK",
      maxLength = 150)
  private String partyCode;

  @Schema(
      description =
"""
Code of the provider of a list of codes identifying a party, including but not limited to:
- `SMDG` (Ship Message Design Group)
- `ZZZ` (Mutually defined)

or any other code as defined in the
[DCSA Code List Providers](https://dcsa.atlassian.net/wiki/external/ZjA4M2I3MjA4MDg1NDEwYzlkYTcxNDRhYWQwNzFiYzY).
""",
      example = "W3C",
      maxLength = 100)
  private String codeListProvider;

  @Schema(
      description =
"""
Name of the code list in which the `codeListProvider` defines the `partyCode`, including but not limited to:
- `DID` (Decentralized Identifier) for `codeListProvider` `W3C`
- `LEI` (Legal Entity Identifier) for `codeListProvider` `GLEIF`
- `DUNS` (Data Universal Numbering System) for `codeListProvider` `DNB`
""",
      example = "DID",
      maxLength = 100)
  private String codeListName;

  @Schema(
      description =
"""
Code identifying the function of the party, as defined in
[UN/EDIFACT 16A 3035](https://unece.org/fileadmin/DAM/trade/untdid/d16a/tred/tred3035.htm)
or a subsequent revision.
""",
      example = "CA",
      maxLength = 3)
  private String partyFunction;
}
