package org.dcsa.standards.specifications.standards.ovs.v301;

import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.parameters.Parameter;
import java.util.List;
import org.dcsa.standards.specifications.standards.ovs.v300.GetServiceSchedulesEndpoint;

public class GetServiceSchedulesV301Endpoint extends GetServiceSchedulesEndpoint {

  private final Parameter mmsiNumber =
      new Parameter()
          .in("query")
          .name("MMSINumber")
          .example("278111222")
          .description(
              "The identifier of a vessel via the `MMSINumber`. The result will only return schedules including the vessel with the specified `MMSINumber`. It is not a requirement for dummy vessels to have an `MMSINumber`. In this case filtering by `vesselName` should be used.")
          .schema(
              new Schema<String>().type("string").pattern("^\\d{9}$").minLength(9).maxLength(9));

  private final Parameter vesselOperatorSMDGLinerCode =
      createStringQueryParameter(
          "vesselOperatorSMDGLinerCode",
          "MSK",
          "`SMDG` code identifying the carrier operating the vessel.");

  private final Parameter cursor =
      new Parameter()
          .in("query")
          .name("cursor")
          .example("fE9mZnNldHw9MTAmbGltaXQ9MTA")
          .description(
              "A server-generated value that identifies the next page position in a collection. When `cursor` is provided, it **MUST NOT** be combined with any other query parameters (including `limit`); such parameters will be ignored and MAY result in a `400` error.")
          .schema(new Schema<String>().type("string").maxLength(1024));

  @Override
  public List<Parameter> getQueryParameters() {
    List<Parameter> base = super.getQueryParameters();
    // Insert MMSINumber after vesselIMONumber (index 3), vesselOperatorSMDGLinerCode after
    // facilitySMDGCode, and cursor after limit
    java.util.List<Parameter> params = new java.util.ArrayList<>(base);
    // Find vesselIMONumber index and insert MMSINumber after it
    for (int i = 0; i < params.size(); i++) {
      if ("vesselIMONumber".equals(params.get(i).getName())) {
        params.add(i + 1, mmsiNumber);
        break;
      }
    }
    // Find facilitySMDGCode and insert vesselOperatorSMDGLinerCode after it
    for (int i = 0; i < params.size(); i++) {
      if ("facilitySMDGCode".equals(params.get(i).getName())) {
        params.add(i + 1, vesselOperatorSMDGLinerCode);
        break;
      }
    }
    // Add cursor at the end
    params.add(cursor);
    return List.copyOf(params);
  }
}
