package org.dcsa.standards.specifications.standards.cs.v100.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class Vessel {

  @Schema(
      description =
          "The unique reference for a registered Vessel. The reference is the International Maritime Organisation (IMO) number, also sometimes known as the Lloyd&apos;s register code, which does not change during the lifetime of the vessel.",
      pattern = "^\\d{7,8}$",
      minLength = 7,
      maxLength = 8,
      example = "9321483")
  private String vesselIMONumber;

  @Schema(
      name = "MMSINumber",
      description =
          "Maritime Mobile Service Identities (MMSIs) are nine-digit numbers used by maritime digital selective calling (DSC), automatic identification systems (AIS) and certain other equipment to uniquely identify a ship or a coast radio station.",
      pattern = "^\\d{9}$",
      minLength = 9,
      maxLength = 9,
      example = "278111222")
  private String mmsiNumber;

  @Schema(
      description = "The name of the Vessel given by the Vessel Operator and registered with IMO.",
      pattern = "^\\S(?:.*\\S)?$",
      maxLength = 50,
      example = "King of the Seas")
  private String name;

  @Schema(
      description =
          "The flag of the nation whose laws the vessel is registered under. This is the [ISO 3166](https://www.iso.org/obp/ui/#iso:pub:PUB500001:en) two-letter country code.",
      pattern = "^[A-Z]{2}$",
      minLength = 2,
      maxLength = 2,
      example = "DE")
  private String flag;

  @Schema(
      description =
          "A unique alphanumeric identity that belongs to the vessel and is assigned by the International Telecommunication Union (ITU). It consists of a three letter alphanumeric prefix that indicates nationality, followed by one to four characters to identify the individual vessel. For instance, vessels registered under Denmark are assigned the prefix ranges 5PA-5QZ, OUAOZZ, and XPA-XPZ. The Call Sign changes whenever a vessel changes its flag.",
      pattern = "^\\S(?:.*\\S)?$",
      maxLength = 10,
      example = "NCVV")
  private String callSign;

  @Schema(
      description =
          "The carrier who is in charge of the vessel operation based on either the SMDG or SCAC code lists.",
      maxLength = 10,
      example = "MAEU")
  private String operatorCarrierCode;

  @Schema(
      description =
          "Identifies the code list provider used for the operator and partner carriercodes.",
      allowableValues = {"SMDG", "NMFTA"},
      example = "NMFTA")
  private String operatorCarrierCodeListProvider;
}
