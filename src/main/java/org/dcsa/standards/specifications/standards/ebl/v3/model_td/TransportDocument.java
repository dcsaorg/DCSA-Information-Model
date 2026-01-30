package org.dcsa.standards.specifications.standards.ebl.v3.model_td;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.Data;
import org.dcsa.standards.specifications.standards.dt.v100.model.Charge;
import org.dcsa.standards.specifications.standards.dt.v100.model.CustomsReference;
import org.dcsa.standards.specifications.standards.dt.v100.model.ExportLicense;
import org.dcsa.standards.specifications.standards.dt.v100.model.ImportLicense;
import org.dcsa.standards.specifications.standards.dt.v100.model.PartyContactDetail;
import org.dcsa.standards.specifications.standards.dt.v100.model.Reference;
import org.dcsa.standards.specifications.standards.dt.v100.model.UtilizedTransportEquipment;
import org.dcsa.standards.specifications.standards.ebl.v3.model.PlaceOfIssue;
import org.dcsa.standards.specifications.standards.ebl.v3.types.CarrierClause;
import org.dcsa.standards.specifications.standards.ebl.v3.types.DisplayedName;

@Schema(description = TransportDocument.CLASS_SCHEMA_DESCRIPTION)
@Data
public class TransportDocument {

  public static final String CLASS_SCHEMA_DESCRIPTION = "The document that governs the terms of carriage between shipper and carrier for maritime transportation. Two distinct types of transport documents exist:\n- Bill of Lading\n- Sea Waybill.";

  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      description =
          "A unique number allocated by the shipping line to the transport document and the main number used for the tracking of the status of the shipment.",
      example = "HHL71800000",
      maxLength = 20,
      pattern = "^\\S(?:.*\\S)?$")
  private String transportDocumentReference;

  @Schema(description = "Additional reference that can be optionally used alongside the `transportDocumentReference` in order to distinguish between versions of the same `Transport Document`.", example = "Version_1", maxLength = 100, pattern = "^\\S(?:.*\\S)?$")
  private String transportDocumentSubReference;

  @Schema(description = "The identifier for a `Shipping Instructions` provided by the carrier for system purposes.", example = "e0559d83-00e2-438e-afd9-fdd610c1a008", maxLength = 100, pattern = "^\\S(?:.*\\S)?$")
  private String shippingInstructionsReference;

  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      description =
"""
The status of the `Transport Document`. Possible values are:
- DRAFT
- APPROVED
- ISSUED
- PENDING_SURRENDER_FOR_AMENDMENT
- SURRENDERED_FOR_AMENDMENT
- PENDING_SURRENDER_FOR_DELIVERY
- SURRENDERED_FOR_DELIVERY
- VOIDED
""",
      example = "DRAFT",
      maxLength = 50)
  private String transportDocumentStatus;

  @Schema(requiredMode = Schema.RequiredMode.REQUIRED, description = "Specifies the type of the transport document\n- `BOL` (Bill of Lading)\n- `SWB` (Sea Waybill)", example = "SWB", allowableValues = {"BOL", "SWB"})
  private String transportDocumentTypeCode;

  @Schema(requiredMode = Schema.RequiredMode.REQUIRED, description = "Specifies whether the Transport Document is a received for shipment, or shipped on board.", example = "true")
  private Boolean isShippedOnBoardType;

  @Schema(
      description =
"""
An indicator of whether freight and ancillary fees for the main transport are prepaid (`PRE`) or collect (`COL`). When prepaid the charges are the responsibility of the shipper or the Invoice payer on behalf of the shipper (if provided). When collect, the charges are the responsibility of the consignee or the Invoice payer on behalf of the consignee (if provided).

- `PRE` (Prepaid)
- `COL` (Collect)
""",
      example = "PRE",
      allowableValues = {"PRE", "COL"})
  private String freightPaymentTermCode;

  @Schema(requiredMode = Schema.RequiredMode.REQUIRED, description = "An indicator whether the transport document is electronically transferred.", example = "true")
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

**Condition:** Only applicable if `transportDocumentType` = `BOL` (Bill of Lading). If `isElectronic = 'True'`, accepted value is `1` (one) or `0` (zero)
""",
      example = "1",
      minimum = "0",
      format = "int32")
  private Integer numberOfOriginalsWithCharges;

  @Schema(
      description =
"""
Number of originals of the Bill of Lading that has been requested by the customer without charges.

**Condition:** Only applicable if `transportDocumentType` = `BOL` (Bill of Lading). If `isElectronic = 'True'`, accepted value is `1` (one) or `0` (zero)
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
"""
Date when the last container that is linked to the transport document is physically loaded onboard the vessel indicated on the transport document.

When provided on a transport document, the transportDocument is a `Shipped On Board` B/L.

Exactly one of `shippedOnBoard` and `receiveForShipmentDate` must be provided on an issued B/L.
""",
      example = "2020-12-12",
      format = "date")
  private String shippedOnBoardDate;

  @Schema(description = "The text to be displayed on the `Transport Document` as evidence that the goods have been received for shipment or shipped on board.", example = "Received for Shipment CMA CGM CONCORDE 28-Jul-2022...", maxLength = 250, pattern = "^\\S(?:.*\\S)?$")
  private String displayedShippedOnBoardReceivedForShipment;

  @Schema(requiredMode = Schema.RequiredMode.REQUIRED, description = "Carrier terms and conditions of transport.", example = "Any reference in...", maxLength = 50000)
  private String termsAndConditions;

  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      description =
"""
Indicates the type of service offered at `Origin`. The options are:
- `CY` (Container yard (incl. rail ramp))
- `SD` (Store Door)
- `CFS` (Container Freight Station)
""",
      example = "CY",
      maxLength = 3,
      allowableValues = {"CY", "SD", "CFS"})
  private String receiptTypeAtOrigin;

  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      description =
"""
Indicates the type of service offered at `Destination`. The options are:

- `CY` (Container yard (incl. rail ramp))
- `SD` (Store Door)
- `CFS` (Container Freight Station)
""",
      example = "CY",
      maxLength = 3,
      allowableValues = {"CY", "SD", "CFS"})
  private String deliveryTypeAtDestination;

  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      description =
"""
Refers to the shipment term at the **loading** of the cargo into the container. Possible values are:

- `FCL` (Full Container Load)
- `LCL` (Less than Container Load)
""",
      example = "FCL",
      maxLength = 3)
  private String cargoMovementTypeAtOrigin;

  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      description =
"""
Refers to the shipment term at the **unloading** of the cargo out of the container. Possible values are:

- `FCL` (Full Container Load)
- `LCL` (Less than Container Load)
""",
      example = "FCL",
      maxLength = 3)
  private String cargoMovementTypeAtDestination;

  @Schema(
      description =
"""
Local date when the transport document has been issued.

Can be omitted on draft transport documents, but must be provided when the document has been issued.
""",
      example = "2020-12-12",
      format = "date")
  private String issueDate;

  @Schema(
      description =
"""
Date when the last container linked to the transport document is physically in the terminal (customers cleared against the intended vessel).

When provided on a transport document, the transportDocument is a `Received For Shipment` B/L.

Exactly one of `shippedOnBoard` and `receiveForShipmentDate` must be provided on an issued B/L.
""",
      example = "2020-12-12",
      format = "date")
  private String receivedForShipmentDate;

  @Schema(
      description =
"""
Reference number for agreement between shipper and carrier, which optionally includes a certain minimum quantity commitment (usually referred as “MQC”) of cargo that the shipper commits to over a fixed period, and the carrier commits to a certain rate or rate schedule.
""",
      example = "HHL51800000",
      maxLength = 30)
  private String serviceContractReference;

  @Schema(description = "Information provided by the shipper to identify whether pricing for the shipment has been agreed via a contract or a quotation reference.", example = "HHL1401", maxLength = 35, pattern = "^\\S(?:.*\\S)?$")
  private String contractQuotationReference;

  @Schema(
      description =
"""
The value of the cargo that the shipper declares in order to avoid the carrier's limitation of liability and "Ad Valorem" freight, i.e., freight which is calculated based on the value of the goods declared by the shipper.

**Condition:** Included in the transport document upon customer request. If customers want the value to show, evidence is required, and customers need to approve additional insurance fee charge from the carrier (very exceptional).
""",
      example = "1231.1",
      minimum = "0",
      format = "float")
  private Double declaredValue;

  @Schema(
      description =
"""
The currency used for the declared value, using the 3-character code defined by [ISO 4217](https://www.iso.org/iso-4217-currency-codes.html).

**Condition:** Mandatory if `declaredValue` is provided. If `declaredValue` is not provided, this field must be empty.
""",
      example = "DKK",
      maxLength = 3,
      minLength = 3,
      pattern = "^[A-Z]{3}$")
  private String declaredValueCurrency;

  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      description =
          "The `SCAC` code (provided by [NMFTA](https://nmfta.org/scac/)) or `SMDG` code (provided by [SMDG](https://smdg.org/documents/smdg-code-lists/smdg-liner-code-list/)) of the issuing carrier of the `Transport Document`. `carrierCodeListProvider` defines which list the `carrierCode` is based upon.",
      example = "MMCU",
      maxLength = 4,
      pattern = "^\\S+$")
  private String carrierCode;

  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      description =
"""
The code list provider for the `carrierCode`. Possible values are:
- `SMDG` (Ship Message Design Group)
- `NMFTA` (National Motor Freight Traffic Association)
""",
      example = "NMFTA",
      allowableValues = {"SMDG", "NMFTA"})
  private String carrierCodeListProvider;

  @Schema(
      description =
          "Additional clauses for a specific shipment added by the carrier to the Bill of Lading, subject to local rules / guidelines or certain mandatory information required to be shared with the customer.")
  private List<CarrierClause> carrierClauses;

  @Schema(
      description =
"""
The number of additional pages required to contain the goods description on a transport document. Only applicable for physical transport documents.
""",
      example = "2",
      minimum = "0",
      format = "int32")
  private Integer numberOfRiderPages;

  @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
  private Transports transports;

  @Schema(description = "A list of `Charges`")
  private List<Charge> charges;

  @Schema
  private PlaceOfIssue placeOfIssue;

  @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
  private InvoicePayableAt invoicePayableAt;

  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      description =
          "The contact details of the person(s) to contact in relation to the **Transport Document** (changes, notifications etc.)")
  @ArraySchema(minItems = 1)
  private List<PartyContactDetail> partyContactDetails;

  @Schema(requiredMode = Schema.RequiredMode.REQUIRED, description = "All `Parties` with associated roles.")
  private DocumentParties documentParties;

  @Schema(requiredMode = Schema.RequiredMode.REQUIRED, description = "A list of `ConsignmentItems`")
  @ArraySchema(minItems = 1)
  private List<ConsignmentItem> consignmentItems;

  @Schema(requiredMode = Schema.RequiredMode.REQUIRED, description = "A list of `Utilized Transport Equipments` describing the equipment being used.")
  @ArraySchema(minItems = 1)
  private List<UtilizedTransportEquipment> utilizedTransportEquipments;

  @Schema
  private ExportLicense exportLicense;

  @Schema
  private ImportLicense importLicense;

  @Schema(description = "A list of `References`")
  private List<Reference> references;

  @Schema(description = "A list of `Customs references`")
  private List<CustomsReference> customsReferences;
}
