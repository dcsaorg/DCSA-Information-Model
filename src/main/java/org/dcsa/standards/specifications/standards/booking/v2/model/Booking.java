package org.dcsa.standards.specifications.standards.booking.v2.model;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.Data;
import org.dcsa.standards.specifications.standards.booking.v2.types.CarrierClause;
import org.dcsa.standards.specifications.standards.dt.v100.model.Charge;
import org.dcsa.standards.specifications.standards.dt.v100.model.CustomsReference;
import org.dcsa.standards.specifications.standards.dt.v100.model.PartyContactDetail;
import org.dcsa.standards.specifications.standards.dt.v100.model.Reference;

@Schema(description = Booking.CLASS_SCHEMA_DESCRIPTION)
@Data
public class Booking {

  public static final String CLASS_SCHEMA_DESCRIPTION = "Booking";

  @Schema(
      description =
"""
A reference to the booking during the booking request phase.

**Condition:** `carrierBookingRequestReference` and/or `carrierBookingReference` must be provided
""",
      example = "24595eb0-5cfc-4381-9c3a-cedc1975e9aa",
      maxLength = 100,
      pattern = "^\\S(?:.*\\S)?$")
  private String carrierBookingRequestReference;

  @Schema(
      description =
"""
The `carrierBookingReference` if know. Often this will not be known until the booking has been confirmed. Is available during a booking amendment.

**Condition:** `carrierBookingRequestReference` and/or `carrierBookingReference` must be provided
""",
      example = "ABC709951",
      maxLength = 35,
      pattern = "^\\S(?:.*\\S)?$")
  private String carrierBookingReference;

  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      description =
"""
The status of the `Booking`. Possible values are:
- `RECEIVED` (Booking request has been received)
- `PENDING_UPDATE` (An update is required to the Booking)
- `UPDATE_RECEIVED` (An update has been received and is awaiting to be processed)
- `CONFIRMED` (Booking has been Confirmed)
- `PENDING_AMENDMENT` (An amendment is required to the Booking)
- `REJECTED` (Booking discontinued by carrier before it has been Confirmed)
- `DECLINED` (Booking discontinued by carrier after it has been Confirmed)
- `CANCELLED` (Booking discontinued by consumer)
- `COMPLETED` (The Transport Document this Booking is connected to has been Surrendered for Delivery)
""",
      example = "RECEIVED",
      maxLength = 50)
  private String bookingStatus;

  @Schema(
      description =
"""
The status of latest amendment added to the `Booking`. If no amendment has been requested - then this field is empty. Possible values are:
- `AMENDMENT_RECEIVED` (An amendment has been received and is awaiting to be processed)
- `AMENDMENT_CONFIRMED` (Amendment is confirmed)
- `AMENDMENT_DECLINED` (Amendment discontinued by provider)
- `AMENDMENT_CANCELLED` (Amendment discontinued by consumer)
""",
      example = "AMENDMENT_RECEIVED",
      maxLength = 50)
  private String amendedBookingStatus;

  @Schema(
      description =
"""
The status of the latest booking cancellation. If no cancellation has been requested - then this property is empty. Possible values are:
- `CANCELLATION_RECEIVED` (A request to cancel a Confirmed Booking has been received and is awaiting to be processed)
- `CANCELLATION_DECLINED` (Cancellation of the Confirmed Booking has been declined by provider)
- `CANCELLATION_CONFIRMED` (Cancellation of the Confirmed Booking has been confirmed by provider)
""",
      example = "CANCELLATION_RECEIVED",
      maxLength = 50)
  private String bookingCancellationStatus;

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
Reference number for agreement between shipper and carrier, which optionally includes a certain minimum quantity commitment (usually referred as “MQC”) of cargo that the shipper commits to over a fixed period, and the carrier commits to a certain rate or rate schedule.

**Condition:** One of `serviceContractReference` or `contractQuotationReference` must be provided, but not both.
""",
      example = "HHL51800000",
      maxLength = 30)
  private String serviceContractReference;

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

  @Schema(description = "An indicator of whether origin charges are prepaid (`PRE`) or collect (`COL`). When prepaid, the charges are the responsibility of the shipper or the Invoice payer on behalf of the shipper (if provided). When collect, the charges are the responsibility of the consignee or the Invoice payer on behalf of the consignee (if provided).")
  private OriginChargesPaymentTerm originChargesPaymentTerm;

  @Schema(description = "An indicator of whether destination charges are prepaid (`PRE`) or collect (`COL`). When prepaid, the charges are the responsibility of the shipper or the Invoice payer on behalf of the shipper (if provided). When collect, the charges are the responsibility of the consignee or the Invoice payer on behalf of the consignee (if provided).")
  private DestinationChargesPaymentTerm destinationChargesPaymentTerm;

  @Schema(
      description =
"""
Information provided by the shipper to identify whether pricing for the shipment has been agreed via a contract or a quotation reference.

**Condition:** One of `contractQuotationReference` or `serviceContractReference` must be provided, but not both.
""",
      example = "HHL1401",
      maxLength = 35,
      pattern = "^\\S(?:.*\\S)?$")
  private String contractQuotationReference;

  @Schema(description = "Vessels related to this booking request.\n\n**Condition:** Mandatory if `carrierExportVoyageNumber` is provided and Vessel details or `carrierServiceCode` are blank")
  private Vessel vessel;

  @Schema(
      description =
"""
The name of a service as specified by the carrier.

**Condition:** Mandatory if `carrierExportVoyageNumber` is provided and Vessel details or `carrierServiceCode` are blank
""",
      example = "Great Lion Service",
      maxLength = 50,
      pattern = "^\\S(?:.*\\S)?$")
  private String carrierServiceName;

  @Schema(
      description =
"""
The carrier specific code of the service for which the schedule details are published.

**Condition:** Mandatory if `carrierExportVoyageNumber` is provided and Vessel details or `carrierServiceName` are blank
""",
      example = "FE1",
      maxLength = 11,
      pattern = "^\\S(?:.*\\S)?$")
  private String carrierServiceCode;

  @Schema(
      description = "A global unique service reference, as per DCSA standard, agreed by VSA partners for the service. The service reference must match the regular expression pattern: `SR\\d{5}[A-Z]`. The letters `SR` followed by `5 digits`, followed by a checksum-character as a capital letter from `A to Z`.",
      example = "SR12345A",
      maxLength = 8,
      minLength = 8,
      pattern = "^SR\\d{5}[A-Z]$")
  private String universalServiceReference;

  @Schema(
      description =
"""
The carrier specific identifier of the export Voyage.

**Condition:** Mandatory if `expectedDepartureDate` or `expectedArrivalAtPlaceOfDeliveryStartDate` and `expectedArrivalAtPlaceOfDeliveryEndDate` is not provided.
""",
      example = "2103S",
      maxLength = 50,
      pattern = "^\\S(?:.*\\S)?$")
  private String carrierExportVoyageNumber;

  @Schema(
      description =
"""
A global unique voyage reference for the export Voyage, as per DCSA standard, agreed by VSA partners for the voyage. The voyage reference must match the regular expression pattern: `\\d{2}[0-9A-Z]{2}[NEWSR]`
- `2 digits` for the year
- `2 alphanumeric characters` for the sequence number of the voyage
- `1 character` for the direction/haul (`N`orth, `E`ast, `W`est, `S`outh or `R`oundtrip).
""",
      example = "2103N",
      maxLength = 5,
      minLength = 5,
      pattern = "^\\d{2}[0-9A-Z]{2}[NEWSR]$")
  private String universalExportVoyageReference;

  @Schema(
      description = "The value of the cargo that the shipper declares in order to avoid the carrier's limitation of liability and \"Ad Valorem\" freight, i.e., freight which is calculated based on the value of the goods declared by the shipper.",
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
      description = "The `SCAC` code (provided by [NMFTA](https://nmfta.org/scac/)) or `SMDG` code (provided by [SMDG](https://smdg.org/documents/smdg-code-lists/smdg-liner-code-list/)) of the carrier the booking request is intended for. `carrierCodeListProvider` defines which list the `carrierCode` is based upon.",
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

  @Schema(
      description =
"""
Indicates whether the shipper agrees to load part of the shipment in case not all of the cargo is delivered within cut-off.

**Note:** The carrier is not liable in case unable to follow the customer's instructions due to operational constraints. If this value is omitted, it may be interpreted differently by different API providers and by the same API provider in different contexts.
""",
      example = "true")
  private Boolean isPartialLoadAllowed;

  @Schema(
      description =
"""
Information provided by the shipper whether an export declaration is required for this particular shipment/commodity/destination.

**Note:** If this property is omitted, it may be interpreted differently by different API providers and by the same API provider in different contexts.
""",
      example = "true")
  private Boolean isExportDeclarationRequired;

  @Schema(
      description = "Reference number assigned to an Export declaration typically submitted by the exporter (or the freight forwarder on behalf of the exporter) that provides detailed information about the goods being exported. It serves as a record for the exporting country's government and is used for statistical, regulatory, and compliance purposes. The export declaration must typically be submitted to the relevant customs authorities before the goods leave the exporting country.",
      example = "ABC123123",
      maxLength = 35,
      pattern = "^\\S(?:.*\\S)?$")
  private String exportDeclarationReference;

  @Schema(
      description =
"""
The date when the shipment is expected to be loaded on board a vessel as provided by the shipper or its agent.

**Condition:** Mandatory if vessel/voyage/service details or `expectedArrivalAtPlaceOfDeliveryDate` is not provided.
""",
      example = "2021-05-17",
      format = "date")
  private String expectedDepartureDate;

  @Schema(
      description =
"""
The start date (provided as a range together with `expectedArrivalAtPlaceOfDeliveryEndDate`) for when the shipment is expected to arrive at `Place Of Delivery`.

**Condition:** Mandatory if vessel/voyage/service details or `expectedDepartureDate` is not provided.
""",
      example = "2021-05-17",
      format = "date")
  private String expectedArrivalAtPlaceOfDeliveryStartDate;

  @Schema(
      description =
"""
The end date (provided as a range together with `expectedArrivalAtPlaceOfDeliveryStartDate`) for when the shipment is expected to arrive at `Place Of Delivery`.

**Condition:** Mandatory if vessel/voyage/service details or `expectedDepartureDate` is not provided.
""",
      example = "2021-05-19",
      format = "date")
  private String expectedArrivalAtPlaceOfDeliveryEndDate;

  @Schema(
      description =
"""
Specifies the type of the `Transport Document`. Possible values are:
- `BOL` (Bill of Lading)
- `SWB` (Sea Waybill)
""",
      example = "SWB",
      allowableValues = {"BOL", "SWB"})
  private String transportDocumentTypeCode;

  @Schema(
      description = "A unique reference allocated by the shipping line to the `Transport Document` that the booking concerns.",
      example = "reserved-HHL123",
      maxLength = 20,
      pattern = "^\\S(?:.*\\S)?$")
  private String transportDocumentReference;

  @Schema(
      description =
"""
Identification number provided by the platform/channel used for booking request/confirmation, ex: Inttra booking reference, or GTNexus, other.

**Condition:** a booking channel is being used
""",
      example = "Inttra reference",
      maxLength = 20,
      pattern = "^\\S(?:.*\\S)?$")
  private String bookingChannelReference;

  @Schema(
      description = "Transport obligations, costs and risks as agreed between buyer and seller as defined by [Incoterms Rules](https://iccwbo.org/business-solutions/incoterms-rules/).",
      example = "FCA",
      maxLength = 3)
  private String incoTerms;

  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      description = "Indicates if an alternate equipment type can be provided by the carrier.",
      example = "true")
  private Boolean isEquipmentSubstitutionAllowed;

  @Schema(
      description = "Carrier terms and conditions of transport.",
      example = "Any reference in...",
      maxLength = 50000)
  private String termsAndConditions;

  @Schema(description = "Location where payment of ocean freight and charges for the main transport will take place by the customer.\n\nThe location must be provided as a `UN Location Code`")
  private InvoicePayableAt invoicePayableAt;

  @Schema(description = "An object to capture where the original Transport Document (`Bill of Lading`) will be issued.\n\n**Condition:** The location can be specified as one of `UN Location Code` or `CountryCode`, but not both.")
  private PlaceOfBLIssue placeOfBLIssue;

  @Schema(description = "A list of `References`")
  private List<Reference> references;

  @Schema(description = "A list of `Customs references`")
  private List<CustomsReference> customsReferences;

  @Schema(requiredMode = Schema.RequiredMode.REQUIRED, description = "All `Parties` with associated roles.")
  private DocumentParties documentParties;

  @Schema(description = "The contact details of the person(s) to contact in relation to the **Booking** (changes, notifications etc.)")
  private List<PartyContactDetail> partyContactDetails;

  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      description = "A list of `Shipment Locations`")
  @ArraySchema(minItems = 1)
  private List<ShipmentLocation> shipmentLocations;

  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      description =
"""
List of `Requested Equipments`. Multiple containers can be requested within the same booking. For each Requested Equipment object with 2 or more units, it is a condition that the commodity (or list of commodities) defined within the same Requested Equipment object is the same for each requested unit.

**Example:** 2 x 20' containing 50% shoes and 50% t-shirts can be requested within the same Requested Equipment object only if each 20' will contain 50% shoes and 50% t-shirts. If 1 x 20' will contain 100% shoes and the other 20' will be 100% t-shirts, 2 separate Requested Equipment objects must be defined.
""")
  @ArraySchema(minItems = 1)
  private List<RequestedEquipment> requestedEquipments;

  @Schema(
      description =
"""
A list of `Confirmed Equipments`

**Condition:** Mandatory and non-empty for a `CONFIRMED` Booking
""")
  private List<ConfirmedEquipment> confirmedEquipments;

  @Schema(
      description =
"""
A list of `Transport` objects (legs) describing the entire transport plan including transshipments.

**Condition:** Mandatory and non-empty for a `CONFIRMED` Booking
""")
  private List<Transport> transportPlan;

  @Schema(
      description =
"""
A list of cut-off times provided by the carrier in the booking confirmation. A cut-off time indicates the latest deadline within which a task must be completed. The confirmed schedule cannot be guaranteed if a cut-off time is missed. Customs brokers may set additional cut-off times to receive the export customs documentation, which is not included in the shipment cut-off times of a carrier booking.

**Condition:** Mandatory and non-empty for a `CONFIRMED` Booking
""")
  private List<ShipmentCutOffTime> shipmentCutOffTimes;

  @Schema(description = "A list of `Advance Manifest Filings` provided by the carrier")
  private List<AdvanceManifestFiling> advanceManifestFilings;

  @Schema(description = "A list of `Charges`")
  private List<Charge> charges;

  @Schema(description = "Additional clauses for a specific shipment added by the carrier to the Bill of Lading, subject to local rules / guidelines or certain mandatory information required to be shared with the customer.")
  private List<CarrierClause> carrierClauses;

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
