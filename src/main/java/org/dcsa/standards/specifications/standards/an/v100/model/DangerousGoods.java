package org.dcsa.standards.specifications.standards.an.v100.model;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import org.dcsa.standards.specifications.standards.dt.v100.types.SegregationGroup;
import org.dcsa.standards.specifications.generator.ClearSchemaConstraints;

@Schema(
    description =
"""
Specification for Dangerous Goods. Provide one of UNNumber or NANumber. Based on IMDG Code Amendment 41-22.
""")
@Getter
@Setter
@RequiredArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
@ClearSchemaConstraints
public class DangerousGoods
    extends org.dcsa.standards.specifications.standards.dt.v100.model.DangerousGoods {

  @Schema(
    name = "IMOClass",
    description = IMO_CLASS_DESCRIPTION,
    example = "1.4S",
    maxLength = 4)
  protected String imoClass;

  @Schema(
      description =
"""
List of the segregation groups applicable to specific hazardous goods according to the IMO IMDG Code.
""")
  protected List<SegregationGroup> segregationGroups;
}
