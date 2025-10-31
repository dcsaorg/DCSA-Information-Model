package org.dcsa.standards.specifications.standards.an.v100.model;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.Data;
import org.dcsa.standards.specifications.standards.core.v100.types.EquipmentReference;
import org.dcsa.standards.specifications.standards.core.v100.model.ClassifiedDate;
import org.dcsa.standards.specifications.standards.core.v100.model.Location;
import org.dcsa.standards.specifications.standards.core.v100.types.FormattedDateTime;

@Data
@Schema(
    description =
"""
Lightweight notification that can be sent when a new or updated Arrival Notice is available
""")
public class ArrivalNoticeNotification {

  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      description = "The date and time when the Arrival Notice was issued.")
  private FormattedDateTime issueDateTime;

  @Schema(
      type = "string",
      maxLength = 1000,
      example = "English, consignee, USD",
      description = ArrivalNotice.TYPE_LABEL_DESCRIPTION)
  private String typeLabel;

  @Schema(
      type = "string",
      maxLength = 1000,
      example = "Warning",
      description = ArrivalNotice.VERSION_LABEL_DESCRIPTION)
  private String versionLabel;

  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      example = "HHL71800000",
      description =
"""
The reference of the transport document for which this arrival notice was created
""",
      maxLength = 20)
  private String transportDocumentReference;

  @Schema(
      description =
"""
References of the equipments for which this arrival notice was created
""")
  private List<EquipmentReference> equipmentReferences;

  @Schema(
      description =
"""
The location where the cargo is discharged from the last sea-going vessel
""")
  private Location portOfDischarge;

  @Schema(
      description =
"""
The location where the cargo is handed over by the shipping line to the consignee or its agent and where the
responsibility of the shipping line ceases
""")
  private Location placeOfDelivery;

  @Schema(description = "The date of arrival of the vessel at the Port of Discharge.")
  private ClassifiedDate portOfDischargeArrivalDate;

  @Schema(description = "The date of arrival of the shipment at Place of Delivery.")
  private ClassifiedDate placeOfDeliveryArrivalDate;
}
