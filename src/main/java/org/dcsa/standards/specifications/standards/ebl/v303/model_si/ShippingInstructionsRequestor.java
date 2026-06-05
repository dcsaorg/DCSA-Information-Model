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

@Schema(
    description =
"""
The requestor of whoever submits the `Shipping Instructions`.

**Condition:** Providers **or** consumers on API v3.0.2 (or earlier): Either the `address` or a party `identifyingCode` must be provided in the `Shipping Instructions`.

**Condition:** Providers **and** consumers on API v3.0.3 (or later): Either the `address`, `addressLines` or a party `identifyingCode` must be provided in the `Shipping Instructions`.
""")
@Getter
@Setter
@RequiredArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
public class ShippingInstructionsRequestor
    extends org.dcsa.standards.specifications.standards.ebl.v3.model_si
        .ShippingInstructionsRequestor {

  @Schema(
      description =
"""
Unstructured address lines, used as a fallback when structured address fields are unavailable.

**Condition:** When communicating with providers **or** consumers implementing API **v3.0.2 or earlier**, a sender implementing API **v3.0.3 or later MUST NOT** use `addressLines` as the only property to identify the party. Recipients implementing earlier versions **MAY ignore** this property.
""")
  @ArraySchema(maxItems = 5)
  protected List<AddressLine> addressLines;
}
