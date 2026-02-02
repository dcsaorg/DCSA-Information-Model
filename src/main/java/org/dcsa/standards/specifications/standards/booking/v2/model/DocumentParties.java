package org.dcsa.standards.specifications.standards.booking.v2.model;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.Data;

@Schema(description = DocumentParties.CLASS_SCHEMA_DESCRIPTION)
@Data
public class DocumentParties {

  public static final String CLASS_SCHEMA_DESCRIPTION = "All `Parties` with associated roles.";

  @Schema(description = "The Booking Agent party.")
  private BookingAgent bookingAgent;

  @Schema(description = "The Shipper party.")
  private Shipper shipper;

  @Schema(description = "The Consignee party.")
  private Consignee consignee;

  @Schema(description = "The Service Contract Owner party.")
  private ServiceContractOwner serviceContractOwner;

  @Schema(description = "The Carrier Booking Office.")
  private CarrierBookingOffice carrierBookingOffice;

  @Schema(description = "A list of document parties that can be optionally provided at booking stage.")
  private List<OtherDocumentParty> other;
}
