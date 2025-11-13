package org.dcsa.standards.specifications.standards.an.v100.model;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.Data;
import org.dcsa.standards.specifications.standards.an.v100.types.FreeTimeTimeUnitCode;
import org.dcsa.standards.specifications.standards.an.v100.types.FreeTimeTypeCode;
import org.dcsa.standards.specifications.standards.core.v101.model.ClassifiedDate;
import org.dcsa.standards.specifications.standards.core.v101.model.ClassifiedDateTime;
import org.dcsa.standards.specifications.standards.core.v101.types.EquipmentReference;
import org.dcsa.standards.specifications.standards.core.v101.types.IsoEquipmentCode;

@Data
@Schema(description = "Free time condition applicable to this shipment at destination.")
public class FreeTime {

  @Schema(description = "One or more types applicable to this free time condition.")
  private List<FreeTimeTypeCode> typeCodes;

  @Schema(
      name = "ISOEquipmentCodes",
      description = "Equipment type codes for which this free time applies")
  private List<IsoEquipmentCode> isoEquipmentCodes;

  @Schema(description = "References of the equipments for which this free time applies")
  private List<EquipmentReference> equipmentReferences;

  @Schema(
      description = "The duration expressed in `timeUnit`s for which this free time item applies.",
      example = "5",
      format = "int32")
  private Integer duration;

  @Schema() private FreeTimeTimeUnitCode timeUnit;

  @Schema(
      maxLength = 1000,
      description =
"""
Description of the conditions under which this free time item applies.
It can include references to the carrier website or individual charges as per service contract/agreement.
""",
      example = "Calculated as...")
  private String calculationBasis;

  @Schema(
      description =
          "The final date when the container(s) can be collected without incurring charges.")
  private ClassifiedDate lastFreeDate;

  @Schema(
      description =
          "The final date and time when the container(s) can be collected without incurring charges.")
  private ClassifiedDateTime lastFreeDateTime;
}
