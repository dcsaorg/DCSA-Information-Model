package org.dcsa.standards.specifications.standards.core.v100.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.dcsa.standards.specifications.standards.core.v100.types.EventClassifierCode;
import org.dcsa.standards.specifications.standards.core.v100.types.FormattedDateTime;

@Schema(description = "Date and time classified as planned, estimated or actual")
@Data
public class ClassifiedDateTime {

  @Schema private FormattedDateTime value;

  @Schema private EventClassifierCode classifier;
}
