package org.dcsa.standards.specifications.standards.portcall.v200;

import io.swagger.v3.oas.models.parameters.Parameter;
import java.util.List;
import java.util.Map;

import org.dcsa.standards.specifications.generator.QueryParametersFilterEndpoint;

public class GetEventsEndpoint extends QueryParametersFilterEndpoint {

  private final Parameter unLocationCode =
      createStringQueryParameter("UNLocationCode", "NLAMS", "UN location code.");

  private final Parameter portVisitReference =
      createStringQueryParameter("portVisitReference", "NLAMS1234589", "Port visit reference.");

  private final Parameter carrierServiceName =
      createStringQueryParameter(
          "carrierServiceName", "Great Lion Service", "Carrier-specific service name.");

  private final Parameter carrierServiceCode =
      createStringQueryParameter("carrierServiceCode", "FE1", "Carrier-specific service code.");

  private final Parameter universalServiceReference =
      createStringQueryParameter(
          "universalServiceReference",
          "SR12345A",
          "Globally unique identifier of a liner service, assigned by carriers as specified by DCSA.");

  private final Parameter terminalCallReference =
      createStringQueryParameter(
          "terminalCallReference",
          "15063401",
          "The terminal call reference for which to retrieve available events.");

  private final Parameter carrierImportVoyageNumber =
      createStringQueryParameter(
          "carrierImportVoyageNumber",
          "1234N",
          "The identifier of an import voyage. The carrier-specific identifier of the import Voyage.");

  private final Parameter universalImportVoyageReference =
      createStringQueryParameter(
          "universalImportVoyageReference",
          "2301W",
          "Unique identifier of the import voyage within the service, assigned by carriers as specified by DCSA.");

  private final Parameter carrierExportVoyageNumber =
      createStringQueryParameter(
          "carrierExportVoyageNumber",
          "1234N",
          "The identifier of an export voyage. The carrier-specific identifier of the export Voyage.");

  private final Parameter universalExportVoyageReference =
      createStringQueryParameter(
          "universalExportVoyageReference",
          "2301W",
          "Unique identifier of the export voyage within the service, assigned by carriers as specified by DCSA.");

  private final Parameter portCallServiceTypeCode =
      createStringQueryParameter("portCallServiceTypeCode", "BERTH", "Port call service type.");

  private final Parameter vesselIMONumber =
      createStringQueryParameter("vesselIMONumber", "12345678", "Vessel IMO number.");

  private final Parameter vesselName =
      createStringQueryParameter("vesselName", "King of the Seas", "Vessel name.");

  private final Parameter vesselMMSINumber =
      createStringQueryParameter("vesselMMSINumber", "278111222", "Vessel MMSI number.");

  private final Parameter portCallID =
      createStringQueryParameter(
          "portCallID", "0342254a-5927-4856-b9c9-aa12e7c00563", "Unique identifier of a port call");

  private final Parameter terminalCallID =
      createStringQueryParameter(
          "terminalCallID",
          "0342254a-5927-4856-b9c9-aa12e7c00563",
          "Unique identifier of a terminal call");

  private final Parameter portCallServiceID =
      createStringQueryParameter(
          "portCallServiceID",
          "0342254a-5927-4856-b9c9-aa12e7c00563",
          "Unique identifier of a port call service");

  private final Parameter timestampID =
      createStringQueryParameter(
          "timestampID",
          "0342254a-5927-4856-b9c9-aa12e7c00563",
          "Unique identifier of a timestamp");

  private final Parameter classifierCode =
      createStringQueryParameter(
          "classifierCode", "ACT", "Classifier code (EST / REQ / PLN / ACT)");

  private final Parameter eventTimestampMin =
      createDateTimeQueryParameter(
          "eventTimestampMin", "Retrieve events with a timestamp at or after this date-time");

  private final Parameter eventTimestampMax =
      createDateTimeQueryParameter(
          "eventTimestampMax", "Retrieve events with a timestamp at or before this date-time");

  private final Parameter limit =
      createIntegerQueryParameter(
          "limit", 10, "Maximum number of events to include in each page of the response.");

  private final Parameter cursor =
      createStringQueryParameter(
          "cursor",
          "ExampleNextPageCursor",
          "Set to the value of the `Next-Page-Cursor` header of the previous response to retrieve the next page.");

  @Override
  public List<Parameter> getQueryParameters() {
    return List.of(
        unLocationCode,
        portVisitReference,
        carrierServiceName,
        carrierServiceCode,
        universalServiceReference,
        terminalCallReference,
        carrierImportVoyageNumber,
        universalImportVoyageReference,
        carrierExportVoyageNumber,
        universalExportVoyageReference,
        portCallServiceTypeCode,
        vesselIMONumber,
        vesselName,
        vesselMMSINumber,
        portCallID,
        terminalCallID,
        portCallServiceID,
        timestampID,
        classifierCode,
        eventTimestampMin,
        eventTimestampMax,
        limit,
        cursor);
  }

  @Override
  public Map<Boolean, List<List<Parameter>>> getRequiredAndOptionalFilters() {
    return Map.ofEntries(Map.entry(Boolean.TRUE, List.of()), Map.entry(Boolean.FALSE, List.of()));
  }
}
