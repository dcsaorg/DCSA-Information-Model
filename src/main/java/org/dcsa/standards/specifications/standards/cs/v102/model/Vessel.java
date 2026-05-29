package org.dcsa.standards.specifications.standards.cs.v102.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Schema(
    description =
"""
Static vessel information like `IMONumber`, `MMSINumber`, `name`, `flag`, `callSign` and operator.
""")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Vessel extends org.dcsa.standards.specifications.standards.cs.v101.model.Vessel {

  @Schema(
      description =
          "The unique reference for a registered Vessel. The reference is the International Maritime Organisation (IMO) number, also sometimes known as the Lloyd's register code, which does not change during the lifetime of the vessel.",
      pattern = "^\\d{7,8}$",
      minLength = 7,
      maxLength = 8,
      example = "9321483")
  private String vesselIMONumber;

  @Schema(
      description =
          "The flag of the nation whose laws the vessel is registered under. This is the [ISO 3166](https://www.iso.org/obp/ui/#iso:pub:PUB500001:en) two-letter country code.",
      pattern = "^[A-Z]{2}$",
      minLength = 2,
      maxLength = 2,
      example = "DE")
  private String flag;
}
