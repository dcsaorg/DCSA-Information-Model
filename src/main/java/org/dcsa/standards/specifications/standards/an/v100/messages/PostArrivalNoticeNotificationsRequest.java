package org.dcsa.standards.specifications.standards.an.v100.messages;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.Data;
import org.dcsa.standards.specifications.standards.an.v100.model.ArrivalNoticeNotification;

@Data
@Schema(description = "API message containing a list of Arrival Notice lightweight notifications.")
public class PostArrivalNoticeNotificationsRequest {

  @Schema(description = "List of Arrival Notice lightweight notifications.")
  private List<ArrivalNoticeNotification> arrivalNoticeNotifications;
}
