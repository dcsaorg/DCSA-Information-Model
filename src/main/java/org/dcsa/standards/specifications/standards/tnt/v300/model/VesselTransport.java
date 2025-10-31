package org.dcsa.standards.specifications.standards.tnt.v300.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.dcsa.standards.specifications.standards.an.v100.types.CountryCode;
import org.dcsa.standards.specifications.standards.core.v100.types.VesselIMONumber;

@Schema(description = "Vessel or barge transport information")
@Data
public class VesselTransport {

  @Schema() private VesselIMONumber vesselIMONumber;

  @Schema(maxLength = 50, example = "King of the Seas", description = "Vessel name")
  private String vesselName;

  @Schema(
      description =
"""
The national flag of the country under which a vessel is registered,
indicated by the 2-character code defined in
[ISO 3166-1 alpha-2](https://www.iso.org/obp/ui/#iso:pub:PUB500001:en).
""")
  private CountryCode vesselFlag;

  @Schema(
      maxLength = 10,
      example = "NCVV",
      description =
"""
A unique alphanumeric identity that belongs to the vessel
and is assigned by the International Telecommunication Union (ITU).
It consists of a three-letter alphanumeric prefix that indicates nationality,
followed by one to four characters to identify the individual vessel.
""")
  private String vesselCallSign;

  @Schema(
    maxLength = 10,
    example = "MAEU",
    description =
"""
The carrier who is in charge of the vessel operation based on `operatorCarrierCodeListProvider`
""")
  private String operatorCarrierCode;

  @Schema(
    maxLength = 10,
    example = "SMDG",
    description =
"""
The code list provider used for the `operatorCarrierCode` (SMDG or NMFTA)
""")
  private String operatorCarrierCodeListProvider;
}
