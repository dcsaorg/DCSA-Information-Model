package org.dcsa.standards.specifications.standards.ebl.v302.model_iss;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Schema(
    description =
        org.dcsa.standards.specifications.standards.ebl.v302.model.DangerousGoods
            .CLASS_SCHEMA_DESCRIPTION)
@Getter
@Setter
@RequiredArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
public class DangerousGoods
    extends org.dcsa.standards.specifications.standards.ebl.v302.model.DangerousGoods {

  @Schema(
      description =
"""
Indicates if a container of hazardous material is at the reportable quantity level. If `TRUE`, a report to the relevant authority must be made in case of spill.
""",
      example = "false")
  protected Boolean isReportableQuantity;
}
