package org.dcsa.standards.specifications.standards.an.v101.types;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.dcsa.standards.specifications.generator.EnumBase;

@Getter
@Schema(
    type = "string",
    example = "DEM",
    description = "Code used to denote the type of free time.",
    maxLength = 10)
@AllArgsConstructor
public enum FreeTimeTypeCode implements EnumBase {
  DEM("Demurrage"),
  DET("Detention (in certain regions, particularly in the US, this is commonly referred to as 'Per Diem')"),
  STO("Storage");

  private final String valueDescription;
}
