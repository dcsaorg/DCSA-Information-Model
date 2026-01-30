package org.dcsa.standards.specifications.standards.ebl.v302.model_td;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.dcsa.standards.specifications.standards.dt.v100.model.PartyContactDetail;
import org.dcsa.standards.specifications.standards.ebl.v3.model.Feedback;

@Schema(
    description =
        org.dcsa.standards.specifications.standards.ebl.v302.model_iss.TransportDocument
            .CLASS_SCHEMA_DESCRIPTION)
@Getter
@Setter
@RequiredArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
public class TransportDocument
    extends org.dcsa.standards.specifications.standards.ebl.v302.model_iss.TransportDocument {

  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      description =
          "The contact details of the person(s) to contact in relation to the **Transport Document** (changes, notifications etc.)")
  @ArraySchema(minItems = 1)
  private List<PartyContactDetail> partyContactDetails;

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
