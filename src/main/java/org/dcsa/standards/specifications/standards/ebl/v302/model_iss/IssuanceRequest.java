package org.dcsa.standards.specifications.standards.ebl.v302.model_iss;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.dcsa.standards.specifications.standards.ebl.v3.model.IssueToParty;
import org.dcsa.standards.specifications.standards.ebl.v3.model_td.TransportDocument;

@Schema(
    description =
        org.dcsa.standards.specifications.standards.ebl.v3.model_iss.IssuanceRequest
            .CLASS_SCHEMA_DESCRIPTION)
@Getter
@Setter
@RequiredArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
public class IssuanceRequest
    extends org.dcsa.standards.specifications.standards.ebl.v3.model_iss.IssuanceRequest {

  @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
  private TransportDocument document;

  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      description =
"""
JWS content with compact serialization according to [RFC 7515](https://datatracker.ietf.org/doc/html/rfc7515#section-7.1). JWS-signed payload is defined in schema [IssuanceManifest](#/IssuanceManifest). This attribute is used to provide integrity of various parts of the payload that enable parties to validate whether a payload matches what the carrier issued originally. Accordingly, the payload is signed by the carrier or a party (typically an agent) acting on behalf of the carrier.
""",
      example =
          "eyJhbGciOiJSUzI1NiIsImtpZCI6IlVhRVdLNmt2ZkRITzNZT2NwUGl2M1RCT2JQTzk2SFZhR2U0czFhUUxBZU0ifQ.eyJkb2N1bWVudENoZWNrc3VtIjogIjhkYzk5ZDhhYzkyMjI0MGM1NWMwMzg0NWY0OWRlZjY0MTg3MTQ2NjUxYmFlNGY5YTYzMTMxMjc3Y2YwMGQ5ZGYiLCJlQkxWaXN1YWxpc2F0aW9uQnlDYXJyaWVyQ2hlY2tzdW0iOiAiNzZhN2QxNGM4M2Q3MjY4ZDY0M2FlNzM0NWM0NDhkZTYwNzAxZjk1NWQyNjRhNzQzZTY5MjhhMGI4MjY4YjI0ZiIsImlzc3VlVG9DaGVja3N1bSI6ICI3NmE3ZDE0YzgzZDcyNjhkNjQzYWU3MzQ1YzQ0OGRlNjA3MDFmOTU1ZDI2NGE3NDNlNjkyOGEwYjgyNjhiMjRmIn0.c4SJ9-61fE6RmeIuZ3EI-TSM0M6qXuOudtr3YhpDjqVMaYk_RYpaWYvw75ssTbjgGFKTBKCy5lpmOfb8Fq--Qu2k0MWbH6qdX5jTYwl0DX946RQg-hnmVTg9np3bmqVeKqKURyV-UUdG-KK_XCGzPZ-lZkeUlpMcIthQFs0pCODR9GPytv7ZXLPZFOmHM9fn3FD2yRqVhQzcs7HdcxMjCx6hkBW8Z-jW4qteVy2_E9uqjkKwlu_cQLoY83Z0mcjn0PZNQvKF10x7q1_Jjf_Su19UigTUu3pFMrzo4iPS_jcrFoIb3TSZNSzbgAwtujSBFOufPDyEmxlx1sH0ZowMvA",
      pattern = "^[A-Za-z0-9_-]+\\.[A-Za-z0-9_-]+\\.[A-Za-z0-9_-]+$")
  private String issuanceManifestSignedContent;

  @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
  private IssueToParty issueTo;
}
