package org.dcsa.standards.specifications.standards.dgd.v100.model;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.Data;
import org.dcsa.standards.specifications.standards.core.v200.model.Volume;
import org.dcsa.standards.specifications.standards.core.v200.model.Weight;
import org.dcsa.standards.specifications.standards.core.v200.types.FormattedDate;
import org.dcsa.standards.specifications.standards.core.v200.types.FormattedDateTime;

@Schema(description = "Dangerous goods information")
@Data
public class DangerousGoods {

  @Schema(
      name = "EMSNumber",
      description =
          "The emergency schedule identified in the IMO EmS Guide — Emergency Response Procedures for Ships Carrying Dangerous Goods. Comprises 2 values; 1 for spillage and 1 for fire. Possible values spillage: S-A to S-Z. Possible values fire: F-A to F-Z.",
      example = "F-A S-Q",
      maxLength = 7)
  private String emsNumber;

  @Schema(
      name = "IMOClass",
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
      name = "NANumber",
      description =
          "Four-digit number that is assigned to dangerous, hazardous, and harmful substances by the United States Department of Transportation.",
      example = "9037",
      maxLength = 4)
  private String naNumber;

  @Schema(
      name = "UNNumber",
      description =
          "United Nations Dangerous Goods Identifier (UNDG) assigned by the UN Sub-Committee of Experts on the Transport of Dangerous Goods and shown in the IMO IMDG.",
      example = "1463",
      maxLength = 4)
  private String unNumber;

  @Schema(
      description =
"""
Four-character code supplied by Exis Technologies that assists to remove ambiguities when identifying a variant within a single UN number or NA number that may occur when two companies exchange DG information.

Character | Valid Characters | Description
:--------:|------------------|------------
1| 0, 1, 2, 3|The packing group. Code 0 indicates there is no packing group
2|0 to 9 and A to Z|A sequence letter for the PSN, or 0 if there were no alternative PSNs
3 and 4|0 to 9 and A to Z|Two sequence letters for other information, for the cases where the variant is required because of different in subrisks, packing instruction etc.
""",
      example = "2200",
      maxLength = 4)
  private String codedVariantList;

  @Schema(
      description = "Name and reference number of the competent authority providing the approval.",
      example = "{Name and reference...}",
      maxLength = 500)
  private String competentAuthorityApproval;

  @Schema(description = "24 hr emergency contact details")
  private EmergencyContactDetails emergencyContactDetails;

  @Schema(description = "Date by when the refrigerated liquid needs to be delivered.")
  private FormattedDate endOfHoldingTime;

  @Schema(description = "Date & time when the container was fumigated")
  private FormattedDateTime fumigationDateTime;

  @Schema(description = "Total weight of the goods carried, including packaging.")
  private Weight grossWeight;

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
      maxLength = 1)
  private String inhalationZone;

  @Schema(
      description = "A list of `Inner Packings` contained inside this `outer packaging/overpack`.")
  private List<InnerPackaging> innerPackagings;

  @Schema(description = "Indicates if the cargo require approval from authorities")
  private Boolean isCompetentAuthorityApprovalRequired;

  @Schema(description = "Indicates if the cargo is residue.")
  private Boolean isEmptyUncleanedResidue;

  @Schema(
      description =
          "Indicates if the dangerous goods can be transported as excepted quantity in accordance with Chapter 3.5 of the IMO IMDG Code.")
  private Boolean isExceptedQuantity;

  @Schema(description = "Indicates if high temperature cargo is shipped.")
  private Boolean isHot;

  @Schema(
      description =
          "Indicates if the dangerous goods can be transported as limited quantity in accordance with Chapter 3.4 of the IMO IMDG Code.")
  private Boolean isLimitedQuantity;

  @Schema(description = "Indicates if the goods belong to the classification of Marine Pollutant.")
  private Boolean isMarinePollutant;

  @Schema(
      description =
          "Indicates if a container of hazardous material is at the reportable quantity level. If `true`, a report to the relevant authority must be made in case of spill.")
  private Boolean isReportableQuantity;

  @Schema(
      description =
          "Indicates if the cargo has special packaging for the transport, recovery or disposal of damaged, defective, leaking or nonconforming hazardous materials packages, or hazardous materials that have spilled or leaked.")
  private Boolean isSalvagePackings;

  @Schema(description = "Indicates if waste is being shipped")
  private Boolean isWaste;

  @Schema(
      description =
          "Limits for the `Dangerous Goods`. The same `Temperature Unit` must apply to all attributes in this structure.")
  private Limits limits;

  @Schema(
      description =
          "The total weight of the explosive substances, without the packaging, casings, etc.")
  private Weight netExplosiveContent;

  @Schema(
      description =
          "The volume of the referenced dangerous goods. Only applicable to liquids and gas.")
  private Volume netVolume;

  @Schema(description = "Total weight of the goods carried, excluding packaging.")
  private Weight netWeight;

  @Schema(
      description =
          "The packing group according to the UN Recommendations on the Transport of Dangerous Goods and IMO IMDG Code.",
      example = "3")
  private Integer packingGroup;

  @Schema(
      description =
          "The proper shipping name for goods under IMDG Code, or the product name for goods under IBC Code and IGC Code, or the bulk cargo shipping name for goods under IMSBC Code, or the name of oil for goods under Annex I to the MARPOL Convention.",
      example = "Chromium Trioxide, anhydrous",
      maxLength = 500)
  private String properShippingName;

  @ArraySchema(
      arraySchema =
          @Schema(
              description =
"""
List of the segregation groups applicable to specific hazardous goods according to the IMO IMDG Code.
"""),
      schema =
          @Schema(
              description =
"""
Grouping of Dangerous Goods having certain similar chemical properties. Possible values are:

- `1` (Acids)
- `2` (Ammonium Compounds)
- `3` (Bromates)
- `4` (Chlorates)
- `5` (Chlorites)
- `6` (Cyanides)
- `7` (Heavy metals and their salts)
- `8` (Hypochlorites)
- `9` (Lead and its compounds)
- `10` (Liquid halogenated hydrocarbons)
- `11` (Mercury and mercury compounds)
- `12` (Nitrites and their mixtures)
- `13` (Perchlorates)
- `14` (Permanganates)
- `15` (Powdered metals)
- `16` (Peroxides),
- `17` (Azides)
- `18` (Alkalis)
"""))
  private List<String> segregationGroups;

  @Schema(
      description =
          "Any risk in addition to the class of the referenced dangerous goods according to the IMO IMDG Code.",
      example = "1.2",
      maxLength = 4)
  private String subsidiaryRisk1;

  @Schema(
      description =
          "Any risk in addition to the class of the referenced dangerous goods according to the IMO IMDG Code.",
      example = "1.2",
      maxLength = 4)
  private String subsidiaryRisk2;

  @Schema(
      description =
          "The recognized chemical or biological name or other name currently used for the referenced dangerous goods as described in chapter 3.1.2.8 of the IMDG Code.",
      example = "xylene and benzene",
      maxLength = 200)
  private String technicalName;

  @Schema(
      description =
          "Text field to indicate certificate number & segment for specific stowage requirements overruling IMDG code",
      example = "22663:3",
      maxLength = 100)
  private String specialCertificateNumber;

  @Schema(
      description =
          "Text field to provide cargo handling information already known at the booking stage.",
      example = "To be handled with extreme care",
      maxLength = 500)
  private String additionalContainerCargoHandling;

  @Schema(
      description =
          "Indicates that the dangerous goods are offered for transport in molten state. Required when applicable unless already included in the properShippingName.")
  private Boolean isMolten;

  @Schema(
      description =
"""
Indicates the transport condition of lithium cells or batteries transported under Special Provision 376 or 377. Allowed values:
- `DAM` (Damaged or defective)
- `DIS` (For disposal)
- `REC` (For recycling)
""",
      example = "DAM",
      maxLength = 3)
  private String lithiumBatteryTransportCondition;

  @Schema(
      description =
          "Indicates that the substance is stabilized for transport purposes. Required when applicable unless already included in the properShippingName.")
  private Boolean isStabilizedSubstance;

  @Schema(
      description =
          "Indicates that stabilization is achieved through temperature control during transport. Required when applicable unless already included in the properShippingName.")
  private Boolean isTemperatureControlled;

  @Schema(
      description =
          "Contains regulatory information applicable to Class 7 radioactive material consignments in accordance with IMDG Code 5.4.1.5.7.")
  private RadioactiveMaterial radioactiveMaterial;

  @ArraySchema(
      arraySchema =
          @Schema(
              description =
"""
Classification reference(s) issued by the competent authority for fireworks transported under UN Nos. 0333, 0334, 0335, 0336, and 0337.
"""),
      schema =
          @Schema(
              description =
"""
The classification reference(s) shall consist of the competent authority's state, indicated by the distinguishing sign used on vehicles in international road traffic, the competent authority identification and a unique serial reference.
"""))
  private List<String> fireworkClassificationReferences;

  @Schema(description = "Production date of the material. Required for UN 1361.")
  private FormattedDate dateOfProduction;

  @Schema(
      description =
          "Date on which the material was packed into the packaging. Required for UN 1361.")
  private FormattedDate dateOfPackingIntoPackagings;

  @Schema(
      description =
          "Temperature of the material on the day of packing into the packaging, expressed in degrees Celsius (°C). Required for UN 1361.",
      example = "55",
      format = "float")
  private Double materialTemperatureAtPacking;

  @ArraySchema(
      arraySchema =
          @Schema(
              description =
                  "Additional mandatory regulatory wording required by IMDG special provisions, class rules, or competent authority approvals."),
      schema =
          @Schema(
              description = "The regulatory statement applicable to the dangerous goods cargo."))
  private List<String> additionalRegulatoryStatements;

  @Schema(
      description = "Medical First Aid Guide reference applicable to the dangerous goods carried.",
      example = "MFAG Table 620",
      maxLength = 100)
  private String mfagReference;
}
