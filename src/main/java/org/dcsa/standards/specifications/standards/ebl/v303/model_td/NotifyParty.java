package org.dcsa.standards.specifications.standards.ebl.v303.model_td;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.dcsa.standards.specifications.standards.core.v104.types.AddressLine;

@Schema(
    description =
"""
The person to be notified when a shipment arrives at its destination.

**Condition:** If a `displayedAddress` is provided, it must be included in the `Transport Document` instead of the `address` or `addressLines`.
""")
@Getter
@Setter
@RequiredArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
public class NotifyParty
    extends org.dcsa.standards.specifications.standards.ebl.v301.model.NotifyParty {

  @Schema(
      description =
          "Unstructured address lines, used as a fallback when structured address fields are unavailable.")
  @ArraySchema(maxItems = 5)
  protected List<AddressLine> addressLines;
}
