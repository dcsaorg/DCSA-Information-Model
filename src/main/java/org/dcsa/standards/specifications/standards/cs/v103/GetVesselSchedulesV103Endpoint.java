package org.dcsa.standards.specifications.standards.cs.v103;

import io.swagger.v3.oas.models.media.StringSchema;
import io.swagger.v3.oas.models.parameters.Parameter;
import java.util.ArrayList;
import java.util.List;
import org.dcsa.standards.specifications.standards.cs.v102.GetVesselSchedulesV102Endpoint;

public class GetVesselSchedulesV103Endpoint extends GetVesselSchedulesV102Endpoint {

  private final Parameter responseScope =
      new Parameter()
          .in("query")
          .name("responseScope")
          .example("MATCHED_CALLS")
          .description(
"""
Indicates whether the `transportCalls` arrays in the returned vessel schedules represent full matched voyage(s) (the **default**) or only matched transport calls. Possible values are:

  - `FULL_VOYAGE` (the response includes entire voyages - which is also the default)
  - `MATCHED_CALLS` (the response only includes matched `transportCalls`)

If omitted, the default value is `FULL_VOYAGE`.
""")
          .schema(new StringSchema()._default("FULL_VOYAGE").maxLength(30));

  @Override
  public List<Parameter> getQueryParameters() {
    List<Parameter> params = new ArrayList<>(super.getQueryParameters());
    params.add(responseScope);
    return params;
  }
}
