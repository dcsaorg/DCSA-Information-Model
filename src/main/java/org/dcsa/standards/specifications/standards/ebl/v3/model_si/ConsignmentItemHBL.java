package org.dcsa.standards.specifications.standards.ebl.v3.model_si;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.Data;
import org.dcsa.standards.specifications.standards.dt.v100.model.CustomsReference;
import org.dcsa.standards.specifications.standards.ebl.v3.model.NationalCommodityCode;

@Schema(
    description =
        "Defines a list of `CargoItems` belonging together in the same consignment. A `ConsignmentItem` can be split across multiple containers (`UtilizedTransportEquipment`) by referencing multiple `CargoItems`",
    title = "Consignment Item (House B/L)")
@Data
public class ConsignmentItemHBL {

  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      description =
          "A plain language description that is precise enough for Customs services to be able to identify the goods. General terms (i.e. 'consolidated', 'general cargo' 'parts' or 'freight of all kinds') or not sufficiently precise description cannot be accepted. Where the declarant provides the CUS code for chemical substances and preparations, Member States may waive the requirement of providing a precise description of the goods.",
      example = "blue shoes size 47",
      pattern = "^\\S(?:.*\\S)?$",
      maxLength = 512)
  private String descriptionOfGoods;

  @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
  private NationalCommodityCode nationalCommodityCode;

  @Schema(requiredMode = Schema.RequiredMode.REQUIRED, description = "A list of all `cargoItems`")
  @ArraySchema(minItems = 1)
  private List<CargoItemHBL> cargoItems;

  @Schema(description = "A list of `Customs references`")
  private List<CustomsReference> customsReferences;
}
