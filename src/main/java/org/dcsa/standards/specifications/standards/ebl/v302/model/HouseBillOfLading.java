package org.dcsa.standards.specifications.standards.ebl.v302.model;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.Data;

@Schema(
    description =
        "A legal contract between the Ocean Transport Intermediary (OTI), such as a Non-Vessel Operating Common Carrier (NVOCC) or a freight forwarder, and the shipper that acknowledges the receipt of goods and outlines the terms of shipment.",
    title = "House Bill of Lading")
@Data
public class HouseBillOfLading {

  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      description =
          "A unique number allocated by the Ocean Transport Intermediary (OTI) to the `House Bill of Lading`.",
      example = "HBOL001",
      pattern = "^\\S(?:.*\\S)?$",
      maxLength = 20)
  private String houseBillOfLadingReference;

  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      description =
          "Indicates whether the `House Bill of Lading` (HBL) is issued `to order` or not. If `true`, `Notify party` at `HBL` level is mandatory",
      example = "false")
  private Boolean isToOrder;

  @Schema
  private PlaceOfAcceptance placeOfAcceptance;

  @Schema
  private PlaceOfFinalDelivery placeOfFinalDelivery;

  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      description =
"""
Method used for the payment of freight charges. It can be one of the following values:
- `A` (Payment in cash)
- `B` (Payment by credit card)
- `C` (Payment by cheque)
- `D` (Other (e.g. direct debit to cash account))
- `H` (Electronic funds transfer)
- `Y` (Account holder with carrier)
- `Z` (Not pre-paid)
""",
      example = "A",
      maxLength = 1)
  private String methodOfPayment;

  @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
  private DocumentPartiesHouseBL documentParties;

  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      description = "Indicates whether cargo is delivered to EU, Norway, Switzerland or Northern Ireland.",
      example = "true")
  private Boolean isCargoDeliveredInICS2Zone;

  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      description =
"""
Identification in a chronological order of the countries through which the goods are routed between the country of original departure and final destination as stipulated in the lowest House Bill of Lading.

**Condition:** The order of the items in this array **MUST** be preserved as by the provider of the API.

**Condition:** If provided - the first country in the list must be the country of `Place of Acceptance`; the last country in the list must be the country of `Place of Final Delivery`.
""")
  @ArraySchema(
      minItems = 1,
      schema =
          @Schema(
              description =
                  "The 2 characters for the country code using [ISO 3166-1 alpha-2](https://www.iso.org/obp/ui/#iso:pub:PUB500001:en)",
              example = "NL",
              pattern = "^[A-Z]{2}$",
              minLength = 2,
              maxLength = 2))
  private List<String> routingOfConsignmentCountries;

  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      description = "A list of `ConsignmentItems` for this `House Bill of Lading`")
  @ArraySchema(minItems = 1)
  private List<ConsignmentItemHBL> consignmentItems;

  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      description = "A list of `Utilized Transport Equipment` for this `House Bill of Lading`")
  @ArraySchema(minItems = 1)
  private List<UtilizedTransportEquipmentHBL> utilizedTransportEquipments;
}
