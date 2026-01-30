package org.dcsa.standards.specifications.standards.ebl.v301.model_si;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.dcsa.standards.specifications.standards.ebl.v3.model_si.OtherDocumentPartyShippingInstructions;
import org.dcsa.standards.specifications.standards.ebl.v3.model_si.ShippingInstructionsRequestor;

@Schema(
    description =
        org.dcsa.standards.specifications.standards.ebl.v3.model_si
            .DocumentPartiesShippingInstructions.CLASS_SCHEMA_DESCRIPTION)
@Getter
@Setter
@RequiredArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
public class DocumentPartiesShippingInstructions
    extends org.dcsa.standards.specifications.standards.ebl.v3.model_si
        .DocumentPartiesShippingInstructions {

  @Schema private ShippingInstructionsRequestor shippingInstructionsRequestor;

  @Schema(
      name = "other",
      description =
          "A list of document parties that can be optionally provided in the `Shipping Instructions` and `Transport Document`.")
  private List<OtherDocumentPartyShippingInstructions> otherWithTypeChanged;
}
