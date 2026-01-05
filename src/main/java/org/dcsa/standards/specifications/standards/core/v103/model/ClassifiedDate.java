package org.dcsa.standards.specifications.standards.core.v103.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.dcsa.standards.specifications.standards.core.v102.types.EventClassifierCode;
import org.dcsa.standards.specifications.standards.core.v102.types.FormattedDate;

@Schema(description = "Date classified as planned, estimated or actual")
@Data
public class ClassifiedDate {

  @Schema
  private FormattedDate value;

  @Schema
  private EventClassifierCode classifier;
}
