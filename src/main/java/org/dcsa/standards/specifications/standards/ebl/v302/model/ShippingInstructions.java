package org.dcsa.standards.specifications.standards.ebl.v302.model;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.Data;
import org.dcsa.standards.specifications.standards.dt.v100.model.PartyContactDetail;
import org.dcsa.standards.specifications.standards.dt.v100.model.Reference;
import org.dcsa.standards.specifications.standards.ebl.v302.types.DisplayedName;
import org.dcsa.standards.specifications.standards.ebl.v302.types.RequestedCarrierCertificate;
import org.dcsa.standards.specifications.standards.ebl.v302.types.RequestedCarrierClause;

@Schema(
    description = "The `Shipping Instructions` as provided by the Shipper.",
    title = "Shipping Instructions")
@Data
public class ShippingInstructions {

  @Schema(
      description =
          "The identifier for a `Shipping Instructions` provided by the carrier for system purposes.",
      example = "e0559d83-00e2-438e-afd9-fdd610c1a008",
      maxLength = 100,
      pattern = "^\\S(?:.*\\S)?$")
  private String shippingInstructionsReference;

  @Schema(
      description =
          "A unique number allocated by the shipping line to the `Transport Document` and the main number used for the tracking of the status of the shipment.",
      example = "HHL71800000",
      maxLength = 20,
      pattern = "^\\S(?:.*\\S)?$")
  private String transportDocumentReference;

  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      description =
          "The status of the `Shipping Instructions`. Possible values are:\n- `RECEIVED` (Shipping Instructions has been received)\n- `PENDING_UPDATE` (An update is required to the Shipping Instructions)\n- `COMPLETED` (The Shipping Instructions can no longer be modified - the related Transport Document has been surrendered for delivery)",
      example = "RECEIVED",
      maxLength = 50)
  private String shippingInstructionsStatus;

  @Schema(
      description =
          "The status of the latest update to the `Shipping Instructions`. If no update has been requested - then this field is empty. Possible values are:\n- `UPDATE_RECEIVED` (An update to a Shipping Instructions is waiting to be processed)\n- `UPDATE_CONFIRMED` (An update to a Shipping Instructions has been confirmed)\n- `UPDATE_CANCELLED` (An update to a Shipping Instructions is discontinued by consumer)\n- `UPDATE_DECLINED` (An update to a Shipping Instructions is discontinued by provider)",
      example = "UPDATE_RECEIVED",
      maxLength = 50)
  private String updatedShippingInstructionsStatus;

  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      description =
          "Specifies the type of the transport document\n- `BOL` (Bill of Lading)\n- `SWB` (Sea Waybill)",
      example = "SWB",
      allowableValues = {"BOL", "SWB"})
  private String transportDocumentTypeCode;

  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      description =
          "Specifies whether the Transport Document is a received for shipment, or shipped on board.",
      example = "true")
  private Boolean isShippedOnBoardType;

  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      description =
"""
An indicator of whether freight and ancillary fees for the main transport are prepaid (`PRE`) or collect (`COL`). When prepaid the charges are the responsibility of the shipper or the Invoice payer on behalf of the shipper (if provided). When collect, the charges are the responsibility of the consignee or the Invoice payer on behalf of the consignee (if provided).

- `PRE` (Prepaid)
- `COL` (Collect)
""",
      example = "PRE",
      allowableValues = {"PRE", "COL"})
  private String freightPaymentTermCode;

  @Schema private OriginChargesPaymentTerm originChargesPaymentTerm;

  @Schema private DestinationChargesPaymentTerm destinationChargesPaymentTerm;

  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      description = "An indicator whether the transport document is electronically transferred.",
      example = "true")
  private Boolean isElectronic;

  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      description =
"""
Indicates whether the B/L is issued `to order` or not. If `true`, the B/L is considered negotiable and an Endorsee party can be defined in the Document parties. If no Endorsee is defined, the B/L is blank endorsed. If `false`, the B/L is considered non-negotiable (also referred to as `straight`).

`isToOrder` must be `false` if `transportDocumentTypeCode='SWB'` (Sea Waybill).
""",
      example = "false")
  private Boolean isToOrder;

  @Schema(
      description =
"""
The requested number of copies of the `Transport Document` to be issued by the carrier including charges.

**Conditions:** The following table defines the conditions for the `numberOfCopiesWithCharges` property:
| Transport Document Type Code | Is Electronic | Meaning |
|-------|:-------:|-------|
|`BOL`|`false`|How many paper copies of the Original BL to be issued by the carrier with charges|
|`BOL`|`true`|Not applicable, there are no copies|
|`SWB`|`false`|Indicates that charges should be included in the `SWB` (pdf or other formats)|
|`SWB`|`true`|Indicates that charges should be included in the electronic `SWB`|
""",
      example = "2",
      minimum = "0",
      format = "int32")
  private Integer numberOfCopiesWithCharges;

  @Schema(
      description =
"""
The requested number of copies of the `Transport Document` to be issued by the carrier **NOT** including charges.

**Conditions:** The following table defines the conditions for the `numberOfCopiesWithoutCharges` property:
| Transport Document Type Code | Is Electronic | Meaning |
|-------|:-------:|-------|
|`BOL`|`false`|How many paper copies of the Original BL to be issued by the carrier without charges|
|`BOL`|`true`|Not applicable, there are no copies|
|`SWB`|`false`|Indicates that charges should NOT be included in the `SWB` (pdf or other formats)|
|`SWB`|`true`|Indicates that charges NOT should be included in the electronic `SWB`|
""",
      example = "2",
      minimum = "0",
      format = "int32")
  private Integer numberOfCopiesWithoutCharges;

  @Schema(
      description =
"""
Number of originals of the Bill of Lading that has been requested by the customer with charges.

**Condition:** Only applicable if `transportDocumentType` = `BOL` (Bill of Lading). If `isElectronic = 'True'`, accepted value is `1` (one) or `0` (zero). `numberOfOriginalsWithoutCharges` + `numberOfOriginalsWithCharges` must be ≤ 1.
""",
      example = "1",
      minimum = "0",
      format = "int32")
  private Integer numberOfOriginalsWithCharges;

  @Schema(
      description =
"""
Number of originals of the Bill of Lading that has been requested by the customer without charges.

**Condition:** Only applicable if `transportDocumentType` = `BOL` (Bill of Lading). If `isElectronic = 'True'`, accepted value is `1` (one) or `0` (zero). `numberOfOriginalsWithoutCharges` + `numberOfOriginalsWithCharges` must be ≤ 1.
""",
      example = "1",
      minimum = "0",
      format = "int32")
  private Integer numberOfOriginalsWithoutCharges;

  @Schema(
      description =
"""
The name to be used in order to specify how the `Place of Receipt` should be displayed on the `Transport Document` to match the name and/or address provided on the `Letter of Credit`.

**Condition:** The order of the items in this array **MUST** be preserved as by the provider of the API.
""")
  @ArraySchema(maxItems = 5)
  private List<DisplayedName> displayedNameForPlaceOfReceipt;

  @Schema(
      description =
"""
The name to be used in order to specify how the `Port of Load` should be displayed on the `Transport Document` to match the name and/or address provided on the `Letter of Credit`.

**Condition:** The order of the items in this array **MUST** be preserved as by the provider of the API.
""")
  @ArraySchema(maxItems = 5)
  private List<DisplayedName> displayedNameForPortOfLoad;

  @Schema(
      description =
"""
The name to be used in order to specify how the `Port of Discharge` should be displayed on the `Transport Document` to match the name and/or address provided on the `Letter of Credit`.

**Condition:** The order of the items in this array **MUST** be preserved as by the provider of the API.
""")
  @ArraySchema(maxItems = 5)
  private List<DisplayedName> displayedNameForPortOfDischarge;

  @Schema(
      description =
"""
The name to be used in order to specify how the `Place of Delivery` should be displayed on the `Transport Document` to match the name and/or address provided on the `Letter of Credit`.

**Condition:** The order of the items in this array **MUST** be preserved as by the provider of the API.
""")
  @ArraySchema(maxItems = 5)
  private List<DisplayedName> displayedNameForPlaceOfDelivery;

  @Schema(
      description =
          "The `SCAC` code (provided by [NMFTA](https://nmfta.org/scac/)) or `SMDG` code (provided by [SMDG](https://smdg.org/documents/smdg-code-lists/smdg-liner-code-list/)) of the carrier the `Shipping Instructions` is intended for. `carrierCodeListProvider` defines which list the `carrierCode` is based upon.",
      example = "MMCU",
      maxLength = 4,
      pattern = "^\\S+$")
  private String carrierCode;

  @Schema(
      description =
"""
The code list provider for the `carrierCode`. Possible values are:
- `SMDG` (Ship Message Design Group)
- `NMFTA` (National Motor Freight Traffic Association)
""",
      example = "NMFTA",
      allowableValues = {"SMDG", "NMFTA"})
  private String carrierCodeListProvider;

  @Schema private PlaceOfIssue placeOfIssue;

  @Schema private InvoicePayableAtShippingInstructions invoicePayableAt;

  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      description =
          "The contact details of the Shipping Instructions requestor(s) to contact in relation to the **Transport Document** (changes, notifications etc.)")
  @ArraySchema(minItems = 1)
  private List<PartyContactDetail> partyContactDetails;

  @Schema(
      description =
          "Indicates whether the Carrier's agent at destination name, address and contact details should be included in the `Transport Document`.",
      example = "false")
  private Boolean isCarriersAgentAtDestinationRequired;

  @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
  private DocumentPartiesShippingInstructions documentParties;

  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      description =
          "Indicates whether cargo is delivered to EU, Norway, Switzerland or Northern Ireland.",
      example = "true")
  private Boolean isCargoDeliveredInICS2Zone;

  @Schema(requiredMode = Schema.RequiredMode.REQUIRED, description = "A list of `ConsignmentItems`")
  @ArraySchema(minItems = 1)
  private List<ConsignmentItemShipper> consignmentItems;

  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      description =
          "A list of `Utilized Transport Equipments` describing the equipment being used.")
  @ArraySchema(minItems = 1)
  private List<UtilizedTransportEquipmentShipper> utilizedTransportEquipments;

  @Schema private ExportLicenseShipper exportLicense;

  @Schema private ImportLicenseShipper importLicense;

  @Schema(description = "A list of `References`")
  private List<Reference> references;

  @Schema(description = "A list of `Customs references`")
  private List<CustomsReference> customsReferences;

  @Schema(
      description =
          "A list of `Advance Manifest Filings` specified by the Shipper to indicate whom is to do the Filing")
  private List<AdvanceManifestFiling> advanceManifestFilings;

  @Schema(
      description =
"""
Indicates whether one or more `House Bill of Lading(s)` have been issued.

**Condition:** Mandatory if `manifestTypeCode` is `ENS`
""")
  private Boolean isHouseBillOfLadingsIssued;

  @Schema(description = "A list of `House Bill of Ladings` specified by the Shipper.")
  private List<HouseBillOfLading> houseBillOfLadings;

  @Schema(
      description =
          "Certificate(s) requested by the Shipper for the Carrier to include as part of the shipment documentation pack")
  private List<RequestedCarrierCertificate> requestedCarrierCertificates;

  @Schema(
      description =
          "Clauses requested by the Shipper for the Carrier to include in the `Transport Document` `Carrier clauses`")
  private List<RequestedCarrierClause> requestedCarrierClauses;

  @Schema(
      description =
"""
Feedback that can be provided includes, but is not limited to:
- unsupported properties
- changed values
- removed properties
- general information
""")
  private List<Feedback> feedbacks;
}
