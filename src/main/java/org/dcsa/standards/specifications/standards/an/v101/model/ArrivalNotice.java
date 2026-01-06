package org.dcsa.standards.specifications.standards.an.v101.model;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.Data;
import org.dcsa.standards.specifications.standards.an.v101.types.CarrierClause;
import org.dcsa.standards.specifications.standards.an.v101.types.FreightPaymentStatus;
import org.dcsa.standards.specifications.standards.core.v101.types.FormattedDateTime;
import org.dcsa.standards.specifications.standards.core.v101.types.HouseBillOfLadingReference;
import org.dcsa.standards.specifications.standards.core.v101.model.Location;

@Schema(description = "Full content of an Arrival Notice document.")
@Data
public class ArrivalNotice {

  public static final String TYPE_LABEL_DESCRIPTION =
"""
Free-format identifier used to specify a certain type of arrival notice,
for example "English, consignee, USD" or "No charges, French".

Each publisher can choose their own criteria by which they categorize arrival notices,
as well as the format of this property. However, for each arrival notice publisher
the format must be consistent and predictable, in order to allow receivers
to programmatically determine whether each received arrival notice overrides
an earlier version of the arrival notice "of the same type" for the same transport document.

An arrival notice (including all its versions) is uniquely identified by the combination
of the `transportDocumentReference` and this `typeLabel`. Each version of an arrival notice
is uniquely identified by the combination of the `transportDocumentReference`, this `typeLabel`
and the `issueDateTime`.

Among the versions of arrival notices of the same type issued for the same transport document,
a version with a newer `issueDateTime` overrides a version with an older `issueDateTime`,
regardless of the `versionLabel` (which is a high-level label with business semantics
and may or may not change between timestamped versions).
""";

  public static final String VERSION_LABEL_DESCRIPTION =
"""
Free text used to indicate a certain version of an arrival notice of a certain type,
for example "Warning", "Updated", "Second", "Third" etc.

This is a high-level label with business semantics that does not influence the technical versioning
of arrival notices of the same `typeLabel` issued for the same `transportDocumentReference`,
which relies on the `issueDateTime` instead.
""";

  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      description = "The date and time when the Arrival Notice was issued.")
  private FormattedDateTime issueDateTime;

  @Schema(
      type = "string",
      maxLength = 1000,
      example = "English, consignee, USD",
      description = TYPE_LABEL_DESCRIPTION)
  private String typeLabel;

  @Schema(
      type = "string",
      maxLength = 1000,
      example = "Warning",
      description = VERSION_LABEL_DESCRIPTION)
  private String versionLabel;

  @Schema(
      description =
"""
The `SCAC` code (provided by [NMFTA](https://nmfta.org/scac/)) or `SMDG` code (provided by
[SMDG](https://smdg.org/documents/smdg-code-lists/smdg-liner-code-list/)) of the issuing carrier of the Arrival Notice.

`carrierCodeListProvider` defines which list the `carrierCode` is based upon.
""",
      example = "MMCU",
      maxLength = 4)
  private String carrierCode;

  @Schema(
      description =
          """
      The code list provider for the `carrierCode`. Possible values are:
      - `SMDG` (Ship Message Design Group)
      - `NMFTA` (National Motor Freight Traffic Association)
      """,
      example = "NMFTA",
      maxLength = 10)
  private String carrierCodeListProvider;

  @Schema(description = "The party to contact for any inquiries related to this Arrival Notice.")
  private List<PartyContactDetail> carrierContactInformation;

  @Schema() private PickupInformation pickupInformation;

  @Schema() private ReturnInformation returnInformation;

  @Schema() private ReleaseInformation releaseInformation;

  @Schema() private CustomsClearance customsClearance;

  @Schema(
      maxLength = 5000,
      example =
          "Valid port entry pass required for drivers. Ensure vehicle registration is pre-submitted to port security.",
      description =
"""
Free text field to provide additional required information for the consignee to prepare for the shipment arrival,
e.g. additional required documents to prepare and present for shipment release - country specific.
""")
  private String additionalInformation;

  @Schema(
      description = "References used by the customer or carrier to identify or track the shipment.")
  private List<Reference> references;

  @Schema(description = "List of House Bill of Lading references")
  private List<HouseBillOfLadingReference> houseBillOfLadingReferences;

  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      description =
          "A unique number allocated by the shipping line to the transport document and the main number used for the tracking of the status of the shipment.",
      example = "HHL71800000",
      maxLength = 20)
  private String transportDocumentReference;

  @Schema(
      description =
          "Specifies the type of the transport document\n- `BOL` (Bill of Lading)\n- `SWB` (Sea Waybill)",
      example = "SWB",
      maxLength = 10)
  private String transportDocumentTypeCode;

  @Schema(
      description = "The location where the original transport document (Bill of Lading) is issued")
  private Location placeOfBLIssue;

  @Schema(
      description = "An indicator whether the transport document is electronically transferred.",
      example = "true")
  private Boolean isElectronic;

  @Schema(
      description =
          """
      Indicates the type of service offered at `Destination`. The options are:

      - `CY` (Container yard (incl. rail ramp))
      - `SD` (Store Door)
      - `CFS` (Container Freight Station)
      """,
      example = "CY",
      maxLength = 3)
  private String deliveryTypeAtDestination;

  @Schema(
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
""",
      example = "HHL51800000",
      maxLength = 30)
  private String serviceContractReference;

  @Schema(maxLength = 50000, description = "Carrier terms and conditions for the Arrival Notice.")
  private String termsAndConditions;

  @Schema(
      description =
          "Additional clauses for a specific shipment added by the carrier to the Bill of Lading, subject to local rules / guidelines or certain mandatory information required to be shared with the customer.")
  private List<CarrierClause> carrierClauses;

  @Schema(
      description =
"""
Document parties.

Note that while parties can generally appear in any order, including `N1` (First Notify Party)
and `N2` (Second Notify Party), the order of parties of type `NI` (Other Notify Party) is relevant,
as it determines which of these parties is considered the third, fourth, fifth (and so on) notify party.
""")
  private List<DocumentParty> documentParties;

  @Schema() private Transport transport;

  @Schema(description = "List of free time conditions applicable to this shipment at destination")
  private List<FreeTime> freeTimes;

  @Schema(description = "A list of `Charges`")
  private List<Charge> charges;

  @Schema() private PaymentRemittance paymentRemittance;

  @Schema() private FreightPaymentStatus freightPaymentStatus;

  @Schema(description = "The equipments being used.")
  private List<UtilizedTransportEquipment> utilizedTransportEquipments;

  @Schema(description = "A list of `ConsignmentItems`")
  private List<ConsignmentItem> consignmentItems;

  @Schema(description = "Visualization of an arrival notice, as an embedded document")
  private EmbeddedDocument arrivalNoticeVisualization;
}
