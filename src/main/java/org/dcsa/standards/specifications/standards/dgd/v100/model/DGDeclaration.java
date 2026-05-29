package org.dcsa.standards.specifications.standards.dgd.v100.model;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.Data;
import org.dcsa.standards.specifications.standards.core.v200.types.FormattedDateTime;

@Data
@Schema(
    description =
"""
Dangerous Good Declaration for one or more pieces of equipment, including the actual dangerous goods details and the relevant associated details
""")
public class DGDeclaration {

  @Schema(
      maxLength = 500,
      example = "DGD-HHL71800000-APZU4812090-2025-01-23T01:23:45Z",
      description = "Reference of the DG declaration")
  private String declarationReference;

  @Schema(description = "The date and time when the DG declaration was issued")
  private FormattedDateTime declarationDateTime;

  @Schema(
      description = "Free-format identifier used to specify a certain type of DG Declaration, for example \"English\" or \"French\".",
      example = "French",
      maxLength = 100)
  private String typeLabel;

  @Schema(description = "The equipments being used.")
  private List<UtilizedTransportEquipment> utilizedTransportEquipments;

  @Schema(description = "DG declaration details specific to the shipment")
  private ShipmentDetails shipmentDetails;

  @Schema(description = "DG declaration details specific to the vessel voyage")
  private VesselVoyageDetails vesselVoyageDetails;

  @Schema(description = "A list of `ConsignmentItems`")
  private List<ConsignmentItem> consignmentItems;

  @Schema(
      description =
"""
Document parties.

Note that while parties can generally appear in any order, including `N1` (First Notify Party)
and `N2` (Second Notify Party), the order of parties of type `NI` (Other Notify Party) is relevant,
as it determines which of these parties is considered the third, fourth, fifth (and so on) notify party.
""")
  private List<DocumentParty> documentParties;

  @Schema(
      description =
"""
"I hereby declare that the goods described above have been packed/loaded into the container/vehicle identified above in accordance with the applicable provisions". MUST BE COMPLETED AND SIGNED FOR ALL CONTAINER/VEHICLE LOADS BY PERSON RESPONSIBLE FOR PACKING/LOADING
""")
  private ContainerPackingCertificate containerPackingCertificate;

  @Schema(
      description =
"""
"I hereby declare that the contents of this consignment are fully and accurately described below by the proper shipping name, and are classified, packaged, marked and labelled/placarded and are in all respects in proper condition for transport according to the applicable international and national governmental regulations."
""")
  private ShipperDeclaration shipperDeclaration;

  @Schema(description = "Identifies the road transport company and driver responsible for moving the container to or from the port, and records their acceptance of the dangerous goods consignment")
  private HaulageAcceptance haulageAcceptance;

  @Schema(
      description =
"""
"Received the above number of packages/containers/trailers in apparent good order and condition, unless stated on the remarks"
""")
  private ReceivingOrganisationReceipt receivingOrganisationReceipt;
}
