package org.dcsa.standards.specifications.standards.booking.v2.model;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.Data;
import org.dcsa.standards.specifications.standards.booking.v2.types.PurchaseOrderReference;
import org.dcsa.standards.specifications.standards.dt.v100.model.IdentifyingCode;
import org.dcsa.standards.specifications.standards.dt.v100.model.PartyContactDetail;
import org.dcsa.standards.specifications.standards.dt.v100.model.TaxLegalReference;

@Schema(description = Shipper.CLASS_SCHEMA_DESCRIPTION)
@Data
public class Shipper {

  public static final String CLASS_SCHEMA_DESCRIPTION = "The party by whom or in whose name or on whose behalf a contract of carriage of goods by sea has been concluded with a carrier, or the party by whom or in whose name, or on whose behalf, the goods are actually delivered to the carrier in relation to the contract of carriage by sea.";

  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      description = "Name of the party.",
      example = "IKEA Denmark",
      maxLength = 70,
      pattern = "^\\S(?:.*\\S)?$")
  private String partyName;

  @Schema(description = "Party address.")
  private PartyAddress address;

  @Schema(description = "A list of contact details")
  @ArraySchema(minItems = 1)
  private List<PartyContactDetail> partyContactDetails;

  private List<IdentifyingCode> identifyingCodes;

  @Schema(description = "A list of `Tax References` for a `Party`")
  private List<TaxLegalReference> taxLegalReferences;

  @Schema(
      description = "A reference linked to the `Shipper`.",
      example = "HHL007",
      maxLength = 35,
      pattern = "^\\S(?:.*\\S)?$")
  private String reference;

  @Schema(description = "A list of `Purchase Order Reference`s linked to the `Shipper`.")
  private List<PurchaseOrderReference> purchaseOrderReferences;
}
