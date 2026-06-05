package org.dcsa.standards.specifications.standards.ebl.v301.model_iss;

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
"""
The document that governs the terms of carriage between shipper and carrier for maritime transportation. Two distinct types of transport documents exist:
- Bill of Lading
- Sea Waybill.
""")
@Getter
@Setter
@RequiredArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
public class TransportDocument
    extends org.dcsa.standards.specifications.standards.ebl.v301.model_td.TransportDocument {

  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      description =
          "The contact details of the Shipping Instructions requestor(s) to contact in relation to the **Transport Document** (changes, notifications etc.)")
  @ArraySchema(minItems = 1)
  private List<PartyContactDetail> partyContactDetails;
}
