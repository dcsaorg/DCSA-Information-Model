package org.dcsa.standards.specifications.standards.cs.v103.model;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Schema(
    description =
        "Leg of the Point-to-Point routing. The property `Leg` can be conformed by as many leg as needed and this leg must be identified with a sequence number.")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Leg extends org.dcsa.standards.specifications.standards.cs.v102.model.Leg {

  @Schema(description = "Estimated footprint emission values for this particular leg.")
  private Footprint footprint;

  @ArraySchema(
      arraySchema =
          @Schema(
              description =
"""
The option to list all intermediate calls between `departure` and `arrival`.

Intermediate calls are calls where no transhipment and no change of mode of transport takes place.

This structure allows intermediate non-transhipment calls to be grouped under a single leg, enabling consumers to present them as collapsed or expanded elements in the user interface, instead of treating each call as a separate leg.

The list only includes calls taking place **between** the `departure` and `arrival` of the current leg.

The order of the list is the order of visits from `departure` to `arrival`.
"""))
  private List<IntermediateCall> intermediateCalls;
}
