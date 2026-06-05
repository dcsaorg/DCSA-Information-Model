package org.dcsa.standards.specifications.standards.ebl.v3.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Schema(
    description =
"""
Used for storing cargo in/on during transport. The equipment size/type is defined by the ISO 6346 code. The most common equipment size/type is 20'/40'/45' DRY Freight Container, but several different versions exist.
""")
@Getter
@Setter
@RequiredArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
public class Equipment
    extends org.dcsa.standards.specifications.standards.dt.v100.model.Equipment {

  @Schema
  protected TareWeight tareWeight;
}
