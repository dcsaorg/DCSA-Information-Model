package org.dcsa.standards.specifications.standards.booking.v2.model;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.Data;
import org.dcsa.standards.specifications.standards.booking.v2.types.ConsigneePurchaseOrderReference;
import org.dcsa.standards.specifications.standards.dt.v100.model.IdentifyingCode;
import org.dcsa.standards.specifications.standards.dt.v100.model.PartyContactDetail;
import org.dcsa.standards.specifications.standards.dt.v100.model.TaxLegalReference;

@Schema(description = Consignee.CLASS_SCHEMA_DESCRIPTION)
@Data
public class Consignee {

  public static final String CLASS_SCHEMA_DESCRIPTION = "The party to which goods are consigned in the Master Bill of Lading.";

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
      description = "A reference linked to the `Consignee`.",
      example = "HHL007",
      maxLength = 35,
      pattern = "^\\S(?:.*\\S)?$")
  private String reference;

  @Schema(description = "A list of `Purchase Order Reference`s linked to the `Consignee`.")
  private List<ConsigneePurchaseOrderReference> purchaseOrderReferences;
}
