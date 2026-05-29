package org.dcsa.standards.specifications.standards.cs.v101;

import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.parameters.Parameter;
import java.math.BigDecimal;
import java.util.List;
import org.dcsa.standards.specifications.standards.cs.v100.GetPointToPointRoutesEndpoint;

public class GetPointToPointRoutesV101Endpoint extends GetPointToPointRoutesEndpoint {

  private final Parameter maxTranshipment =
      new Parameter()
          .in("query")
          .name("maxTranshipment")
          .example(1)
          .description(
"""
Specifies the maximum number of transhipments that the proposed routings can have in the response. By default, transhipments are allowed and the responses can have either direct routings or routings with transhipment. The default value of maximum transhipments is defined by the carrier for when the consumer does not provide a value. If the user sets the number of transhipments to 0, only direct routings can be returned in the response.

**Note:** According to the DCSA definition, a “transhipment” specifically refers to transferring containers or cargo from one vessel to another, focusing exclusively on ocean transport. This means that, under the current interpretation, only vessel-to-vessel transfers are counted as transhipments. Moves between road, rail, or barge and a vessel (i.e., inland legs) do not fall under this definition and, therefore, should not be counted as transhipments.
""")
          .schema(new Schema<Integer>().type("integer").format("int32").minimum(BigDecimal.ZERO));

  @Override
  public List<Parameter> getQueryParameters() {
    return super.getQueryParameters().stream()
        .map(p -> "maxTranshipment".equals(p.getName()) ? maxTranshipment : p)
        .toList();
  }
}
