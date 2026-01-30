package org.dcsa.standards.specifications.standards.ebl.v301.model_si;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.dcsa.standards.specifications.standards.dt.v100.model.PartyContactDetail;

@Schema(
    description =
        org.dcsa.standards.specifications.standards.ebl.v3.model_si.ShippingInstructions
            .CLASS_SCHEMA_DESCRIPTION)
@Getter
@Setter
@RequiredArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
public class ShippingInstructions
    extends org.dcsa.standards.specifications.standards.ebl.v3.model_si.ShippingInstructions {

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

  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      description =
          "The contact details of the Shipping Instructions requestor(s) to contact in relation to the **Transport Document** (changes, notifications etc.)")
  @ArraySchema(minItems = 1)
  private List<PartyContactDetail> partyContactDetails;

  @Schema(
      description =
          "A unique number allocated by the shipping line to the `Transport Document` and the main number used for the tracking of the status of the shipment.",
      example = "HHL71800000",
      maxLength = 20,
      pattern = "^\\S(?:.*\\S)?$")
  private String transportDocumentReference;
}
