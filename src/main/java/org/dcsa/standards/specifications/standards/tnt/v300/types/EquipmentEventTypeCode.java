package org.dcsa.standards.specifications.standards.tnt.v300.types;

import org.dcsa.standards.specifications.generator.EnumBase;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@Schema(
    type = "string",
    example = "LOAD",
    description = "Code used to denote the type of an equipment event")
@AllArgsConstructor
public enum EquipmentEventTypeCode implements EnumBase {
  AVDO("Available for Drop-off"),
  AVPU("Available for Pick-up"),
  CROS("Crossed"),
  CUSI("Customs Selected for Inspection"),
  CUSR("Customs Released"),
  CUSS("Customs Selected for Scan"),
  DISC("Discharged"),
  DROP("Drop-off"),
  GTIN("Gated in"),
  GTOT("Gated out"),
  INSP("Inspected"),
  LOAD("Loaded"),
  PICK("Pick-up"),
  RMVD("Removed"),
  RSEA("Resealed"),
  STRP("Stripped"),
  STUF("Stuffed");

  private final String valueDescription;
}
