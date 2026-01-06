package org.dcsa.standards.specifications.standards.an.v101.model;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.Data;
import org.dcsa.standards.specifications.standards.core.v101.model.ClassifiedDate;
import org.dcsa.standards.specifications.standards.core.v101.model.ClassifiedDateTime;
import org.dcsa.standards.specifications.standards.core.v101.model.Location;

@Data
@Schema(description = "Transport details relevant to the arrival notice.")
public class Transport {

  @Schema(
      description =
"""
The location where the cargo is handed over by the shipper, or his agent, to the shipping line.
This indicates the point at which the shipping line takes on responsibility for carriage of the container.
""")
  private Location placeOfReceipt;

  @Schema(
      description =
"""
The location where the cargo is loaded onto a first sea-going vessel for water transportation.
""")
  private Location portOfLoading;

  @Schema(
      description =
"""
The location where the cargo is discharged from the last sea-going vessel.
""")
  private Location portOfDischarge;

  @Schema(
      description =
"""
The location where the cargo is handed over to the consignee, or his agent,
by the shipping line and where responsibility of the shipping line ceases.
""")
  private Location placeOfDelivery;

  @Schema(
      description =
"""
The date of departure from the location where the cargo is handed over by the shipper, or his agent,
to the shipping line. This can refer either to the Place of Receipt or the Port of Loading.
""")
  private ClassifiedDate departureDate;

  @Schema(
      description =
"""
The date on which the carrier takes possession of all containers under the bill of lading.
For carrier haulage, this is when the carrier collects the last container at the place of receipt.
For merchant haulage, this is when the last container is physically handed over to the terminal.
""")
  private ClassifiedDate receivedForShipmentDate;

  @Schema(
      description =
"""
The date when the container was loaded onto the vessel at the port of origin.
""")
  private ClassifiedDate onBoardDate;

  @Schema(description = "The date of arrival of the vessel at the Port of Discharge.")
  private ClassifiedDate portOfDischargeArrivalDate;

  @Schema(description = "The date of arrival of the shipment at Place of Delivery.")
  private ClassifiedDate placeOfDeliveryArrivalDate;

  @Schema(
      description =
"""
The date when the container reaches its inland destination (e.g., a warehouse or rail terminal).
""")
  private ClassifiedDate inlandArrivalDate;

  @Schema(
      description =
"""
The date and time when the shipment will be placed under General Order status
(shipment is transferred to a bonded warehouse, and additional fees or penalties may apply).
""")
  private ClassifiedDateTime generalOrderDateTime;

  @Schema(
      description =
"""
The inland location where the cargo is intended to cross an international border.
""")
  private Location borderCrossingLocation;

  @Schema(
      description =
"""
Full or partial information, considered relevant in this context, on some or all `Legs` of the `Transport`
""")
  private List<Leg> legs;
}
