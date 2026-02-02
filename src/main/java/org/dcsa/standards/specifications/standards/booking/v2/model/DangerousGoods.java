package org.dcsa.standards.specifications.standards.booking.v2.model;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.Data;
import org.dcsa.standards.specifications.standards.booking.v2.types.SegregationGroup;
import org.dcsa.standards.specifications.standards.dt.v100.model.EmergencyContactDetails;
import org.dcsa.standards.specifications.standards.dt.v100.model.InnerPackaging;
import org.dcsa.standards.specifications.standards.dt.v100.model.Limits;

@Schema(description = DangerousGoods.CLASS_SCHEMA_DESCRIPTION)
@Data
public class DangerousGoods {

  public static final String CLASS_SCHEMA_DESCRIPTION =
      "Specification for `Dangerous Goods`. It is mandatory to provide one of `UNNumber` or `NANumber`. Dangerous Goods is based on **IMDG Amendment Version 41-22**.";

  @Schema(
      name = "UNNumber",
      description =
"""
United Nations Dangerous Goods Identifier (UNDG) assigned by the UN Sub-Committee of Experts on the Transport of Dangerous Goods and shown in the IMO IMDG.
""",
      example = "1463",
      minLength = 4,
      maxLength = 4,
      pattern = "^\\d{4}$")
  private String unNumber;

  @Schema(
      name = "NANumber",
      description =
"""
Four-digit number that is assigned to dangerous, hazardous, and harmful substances by the United States Department of Transportation.
""",
      example = "9037",
      minLength = 4,
      maxLength = 4,
      pattern = "^\\d{4}$")
  private String naNumber;

  @Schema(
      description =
"""
Four-character code supplied by Exis Technologies that assists to remove ambiguities when identifying a variant within a single `UN number` or `NA number` that may occur when two companies exchange DG information.

Character | Valid Characters | Description
:--------:|------------------|------------
1| 0, 1, 2, 3|The packing group. Code 0 indicates there is no packing group
2|0 to 9 and A to Z|A sequence letter for the PSN, or 0 if there were no alternative PSNs
3 and 4|0 to 9 and A to Z|Two sequence letters for other information, for the cases where the variant is required because of different in subrisks, packing instruction etc.
""",
      example = "2200",
      minLength = 4,
      maxLength = 4,
      pattern = "^[0-3][0-9A-Z]{3}$")
  private String codedVariantList;

  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      description =
          "The proper shipping name for goods under IMDG Code, or the product name for goods under IBC Code and IGC Code, or the bulk cargo shipping name for goods under IMSBC Code, or the name of oil for goods under Annex I to the MARPOL Convention.",
      example = "Chromium Trioxide, anhydrous",
      maxLength = 250)
  private String properShippingName;

  @Schema(
      description =
          "The recognized chemical or biological name or other name currently used for the referenced dangerous goods as described in chapter 3.1.2.8 of the IMDG Code.",
      example = "xylene and benzene",
      maxLength = 250)
  private String technicalName;

  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      description =
"""
The hazard class code of the referenced dangerous goods according to the specified regulation. Examples of possible values are:
- `1.1A` (Substances and articles which have a mass explosion hazard)
- `1.6N` (Extremely insensitive articles which do not have a mass explosion hazard)
- `2.1` (Flammable gases)
- `8` (Corrosive substances)
""",
      example = "1.4S",
      maxLength = 4)
  private String imoClass;

  @Schema(
      description =
          "Any risk in addition to the class of the referenced dangerous goods according to the IMO IMDG Code.",
      example = "1.2",
      minLength = 1,
      maxLength = 3,
      pattern = "^[0-9](\\.[0-9])?$")
  private String subsidiaryRisk1;

  @Schema(
      description =
          "Any risk in addition to the class of the referenced dangerous goods according to the IMO IMDG Code.",
      example = "1.2",
      minLength = 1,
      maxLength = 3,
      pattern = "^[0-9](\\.[0-9])?$")
  private String subsidiaryRisk2;

  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      description = "Indicates if the goods belong to the classification of Marine Pollutant.",
      example = "false")
  private Boolean isMarinePollutant;

  @Schema(
      description =
          "The packing group according to the UN Recommendations on the Transport of Dangerous Goods and IMO IMDG Code.",
      example = "3",
      minimum = "1",
      maximum = "3",
      format = "int32")
  private Integer packingGroup;

  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      description =
          "Indicates if the dangerous goods can be transported as limited quantity in accordance with Chapter 3.4 of the IMO IMDG Code.",
      example = "false")
  private Boolean isLimitedQuantity;

  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      description =
          "Indicates if the dangerous goods can be transported as excepted quantity in accordance with Chapter 3.5 of the IMO IMDG Code.",
      example = "false")
  private Boolean isExceptedQuantity;

  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      description =
          "Indicates if the cargo has special packaging for the transport, recovery or disposal of damaged, defective, leaking or nonconforming hazardous materials packages, or hazardous materials that have spilled or leaked.",
      example = "false")
  private Boolean isSalvagePackings;

  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      description = "Indicates if the cargo is residue.",
      example = "false")
  private Boolean isEmptyUncleanedResidue;

  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      description = "Indicates if waste is being shipped",
      example = "false")
  private Boolean isWaste;

  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      description = "Indicates if high temperature cargo is shipped.",
      example = "false")
  private Boolean isHot;

  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      description = "Indicates if the cargo require approval from authorities",
      example = "false")
  private Boolean isCompetentAuthorityApprovalRequired;

  @Schema(
      description = "Name and reference number of the competent authority providing the approval.",
      example = "{Name and reference...}",
      maxLength = 70,
      pattern = "^\\S(?:.*\\S)?$")
  private String competentAuthorityApproval;

  @Schema(
      description =
"""
List of the segregation groups applicable to specific hazardous goods according to the IMO IMDG Code.

**Condition:** only applicable to specific hazardous goods.
""")
  private List<SegregationGroup> segregationGroups;

  @Schema(description = "A list of `Inner Packings` contained inside this `outer packaging/overpack`.")
  private List<InnerPackaging> innerPackagings;

  @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
  private EmergencyContactDetails emergencyContactDetails;

  @Schema(
      name = "EMSNumber",
      description =
"""
The emergency schedule identified in the IMO EmS Guide - Emergency Response Procedures for Ships Carrying Dangerous Goods. Comprises 2 values; 1 for spillage and 1 for fire. Possible values spillage: S-A to S-Z. Possible values fire: F-A to F-Z.
""",
      example = "F-A S-Q",
      maxLength = 7)
  private String emsNumber;

  @Schema(
      description = "Date by when the refrigerated liquid needs to be delivered.",
      example = "2021-09-03",
      format = "date")
  private String endOfHoldingTime;

  @Schema(
      description = "Date & time when the container was fumigated",
      example = "2024-09-04T09:41:00Z",
      format = "date-time")
  private String fumigationDateTime;

  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      description =
"""
Indicates if a container of hazardous material is at the reportable quantity level. If `TRUE`, a report to the relevant authority must be made in case of spill.
""",
      example = "false")
  private Boolean isReportableQuantity;

  @Schema(
      description =
"""
The zone classification of the toxicity of the inhalant. Possible values are:
- `A` (Hazard Zone A) can be assigned to specific gases and liquids
- `B` (Hazard Zone B) can be assigned to specific gases and liquids
- `C` (Hazard Zone C) can **only** be assigned to specific gases
- `D` (Hazard Zone D) can **only** be assigned to specific gases
""",
      example = "A",
      minLength = 1,
      maxLength = 1)
  private String inhalationZone;

  @Schema(requiredMode = Schema.RequiredMode.REQUIRED, description = "Total weight of the goods carried, including packaging.")
  private GrossWeight grossWeight;

  @Schema(description = "Total weight of the goods carried, excluding packaging.")
  private NetWeight netWeight;

  @Schema(description = "The total weight of the explosive substances, without the packaging's, casings, etc.")
  private NetExplosiveContent netExplosiveContent;

  @Schema(description = "The volume of the referenced dangerous goods.\n\n**Condition:** only applicable to liquids and gas.")
  private NetVolume netVolume;

  @Schema
  private Limits limits;

  @Schema(
      description = "Text field to indicate certificate number & segment for specific stowage requirements overruling IMDG code",
      example = "22663:3",
      maxLength = 255)
  private String specialCertificateNumber;

  @Schema(
      description = "Text field to provide cargo handling information already known at the booking stage.",
      example = "To be handled with extreme care",
      maxLength = 255)
  private String additionalContainerCargoHandling;
}
