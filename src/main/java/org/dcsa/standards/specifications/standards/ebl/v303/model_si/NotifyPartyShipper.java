package org.dcsa.standards.specifications.standards.ebl.v303.model_si;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.dcsa.standards.specifications.standards.core.v104.types.AddressLine;
import org.dcsa.standards.specifications.standards.ebl.v301.model.NotifyParty;

@Schema(description = NotifyParty.CLASS_SCHEMA_DESCRIPTION)
@Getter
@Setter
@RequiredArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
public class NotifyPartyShipper extends NotifyParty {

  @Schema(
      description =
"""
Unstructured address lines, used as a fallback when structured address fields are unavailable.

**Condition:** When communicating with providers **or** consumers implementing API **v3.0.2 or earlier**, a sender implementing API **v3.0.3 or later MUST NOT** use `addressLines` as the only property to identify the party. Recipients implementing earlier versions **MAY ignore** this property.
""")
  @ArraySchema(maxItems = 5)
  protected List<AddressLine> addressLines;
}
