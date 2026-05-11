package org.dcsa.standards.specifications.standards.ebl.v303.model_si;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.dcsa.standards.specifications.standards.ebl.v3.model_si.OtherDocumentPartyHBL;

@Schema(
    description =
        org.dcsa.standards.specifications.standards.ebl.v3.model_si.DocumentPartiesHouseBL
            .CLASS_SCHEMA_DESCRIPTION)
@Getter
@Setter
@RequiredArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
public class DocumentPartiesHouseBL
    extends org.dcsa.standards.specifications.standards.ebl.v3.model_si.DocumentPartiesHouseBL {

  @Schema(
      description =
          "A list of document parties that can be optionally provided in the `Shipping Instructions` as part of the House B/L.")
  private List<OtherDocumentPartyHBL> other;
}
