package org.dcsa.standards.specifications.standards.ebl.v303.model_td;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.dcsa.standards.specifications.standards.ebl.v302.model_td.OtherDocumentParty;

@Schema(
    description =
        org.dcsa.standards.specifications.standards.ebl.v302.model_td.DocumentParties
            .CLASS_SCHEMA_DESCRIPTION)
@Getter
@Setter
@RequiredArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
public class DocumentParties
    extends org.dcsa.standards.specifications.standards.ebl.v302.model_td.DocumentParties {

  @Schema(
      name = "other",
      description =
          "A list of document parties that can be optionally provided in the `Transport Document`.")
  private List<OtherDocumentParty> renamed_other;
}
