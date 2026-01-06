package org.dcsa.standards.specifications.standards.vgm.v101.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.dcsa.standards.specifications.standards.core.v103.model.Location;
import org.dcsa.standards.specifications.standards.core.v103.model.Weight;
import org.dcsa.standards.specifications.standards.core.v103.types.FormattedDateTime;
import org.dcsa.standards.specifications.standards.vgm.v101.types.VGMMethodCode;

@Data
@Schema(
    description =
"""
Verified Gross Mass of one piece of equipment, according to SOLAS Chapter VI, Regulation 2, paragraphs 4-6
""")
public class VGM {

  @Schema(description = "The gross mass (weight) of the transport equipment")
  private Weight weight;

  @Schema() private VGMMethodCode method;

  @Schema(description = "Date and time when a gross mass (weight) of a packed container was obtained")
  private FormattedDateTime dateTime;

  @Schema(
      description =
          "The location where the packed container was weighed to determine the Verified Gross Mass (VGM).")
  private Location location;

  @Schema(
      maxLength = 70,
      example = "W42-23110812",
      description =
          "Reference number or identifier for the certificate issued by the weighing party (or a certified weighing facility).")
  private String certificationReference;

  @Schema(description = "Date and time when the VGM certificate has been issued.")
  private FormattedDateTime certificationDateTime;
}
