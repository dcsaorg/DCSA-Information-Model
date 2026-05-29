package org.dcsa.standards.specifications.standards.cs.v101;

import io.swagger.v3.oas.models.parameters.Parameter;
import java.util.List;
import org.dcsa.standards.specifications.standards.cs.v100.GetVesselSchedulesEndpoint;

public class GetVesselSchedulesV101Endpoint extends GetVesselSchedulesEndpoint {

  private final Parameter facilitySMDGCode =
      createStringQueryParameter(
          "facilitySMDGCode",
          "APM",
          "The facilitySMDGCode specifying a specific facility (using SMDG Code). Be aware that the `facilitySMDGCode` does not contain a `UNLocationCode` - this must be specified in the `UNLocationCode` filter.  Specifying this filter will only return schedules including entire Voyages related to this particular `facilitySMDGCode`. It is recommended to specify a value for this query parameter only if a value is provided for `UNLocationCode`.");

  private final Parameter startDate =
      createDateQueryParameter(
          "startDate",
          "2020-04-06",
          "The start date of the period for which schedule information is requested. If a date of any Timestamp (ATA, ETA or PTA) inside a PortCall matches a date on or after (≥) the `startDate` the entire Voyage (import- and export-Voyage) matching the PortCall will be included in the result. All matching is done towards local Date at the place of the port call.\nIf this filter is not provided, the default value is **3 months** prior to the request time.");

  private final Parameter endDate =
      createDateQueryParameter(
          "endDate",
          "2020-04-10",
          "The end date of the period for which schedule information is requested. If a date of any Timestamp (ATA, ETA or PTA) inside a PortCall matches a date on or before (≤) the `endDate` the entire Voyage(import- and export-Voyage) matching the PortCall will be included in the result. All matching is done towards local Date at the place of the port call.\nIf this filter is not provided, the default value is **6 months** after the request time.");

  @Override
  public List<Parameter> getQueryParameters() {
    return super.getQueryParameters().stream()
        .map(
            p ->
                switch (p.getName()) {
                  case "facilitySMDGCode" -> facilitySMDGCode;
                  case "startDate" -> startDate;
                  case "endDate" -> endDate;
                  default -> p;
                })
        .toList();
  }
}
