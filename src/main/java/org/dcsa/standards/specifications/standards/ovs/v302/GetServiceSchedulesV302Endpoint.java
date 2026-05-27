package org.dcsa.standards.specifications.standards.ovs.v302;

import io.swagger.v3.oas.models.parameters.Parameter;
import java.util.List;
import org.dcsa.standards.specifications.standards.ovs.v301.GetServiceSchedulesV301Endpoint;

public class GetServiceSchedulesV302Endpoint extends GetServiceSchedulesV301Endpoint {

  private final Parameter responseScope =
      new Parameter()
          .in("query")
          .name("responseScope")
          .example("MATCHED_CALLS")
          .description(
"""
Indicates whether the `transportCalls` arrays in the returned service schedules represent full matched voyage(s) (the **default**) or only matched transport calls. Possible values are:

  - `FULL_VOYAGE` (the response includes entire voyages - which is also the default)
  - `MATCHED_CALLS` (the response only includes matched `transportCalls`)

If omitted, the default value is `FULL_VOYAGE`.
""");

  @Override
  public List<Parameter> getQueryParameters() {
    List<Parameter> base = super.getQueryParameters();
    java.util.List<Parameter> params = new java.util.ArrayList<>(base);
    // Insert responseScope before limit
    for (int i = 0; i < params.size(); i++) {
      if ("limit".equals(params.get(i).getName())) {
        params.add(i, responseScope);
        break;
      }
    }
    return List.copyOf(params);
  }
}
