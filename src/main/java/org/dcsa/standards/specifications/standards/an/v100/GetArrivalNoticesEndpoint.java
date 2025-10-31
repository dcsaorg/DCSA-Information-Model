package org.dcsa.standards.specifications.standards.an.v100;

import io.swagger.v3.oas.models.parameters.Parameter;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.dcsa.standards.specifications.generator.QueryParametersFilterEndpoint;

public class GetArrivalNoticesEndpoint extends QueryParametersFilterEndpoint {

  private final Parameter transportDocumentReferences =
      createStringListQueryParameter(
          "transportDocumentReferences",
          List.of("HHL71800000", "HHL71800001"),
          "Reference(s) of the transport document(s) for which to return the associated arrival notices");

  private final Parameter equipmentReferences =
      createStringListQueryParameter(
          "equipmentReferences",
          List.of("APZU4812090", "APZU4812091"),
          "Reference(s) of the equipment for which to return the associated arrival notices");

  private final Parameter portOfDischarge =
      createStringQueryParameter(
          "portOfDischarge",
          "NLRTM",
          "UN location of the port of discharge for which to retrieve available arrival notices");

  private final Parameter vesselIMONumber =
      createStringQueryParameter(
          "vesselIMONumber",
          "9321483",
          "IMO number of the vessel for which to retrieve available arrival notices");

  private final Parameter vesselName =
      createStringQueryParameter(
          "vesselName",
          "King of the Seas",
          "Name of the vessel for which to retrieve available arrival notices");

  private final Parameter carrierImportVoyageNumber =
      createStringQueryParameter(
          "carrierImportVoyageNumber",
          "2208N",
          "The identifier of an import voyage. The carrier-specific identifier of the import Voyage.");

  private final Parameter universalImportVoyageReference =
      createStringQueryParameter(
          "universalImportVoyageReference",
          "2301W",
          "Unique identifier of the import voyage within the service, assigned by carriers as specified by DCSA.");

  private final Parameter carrierServiceCode =
      createStringQueryParameter(
          "carrierServiceCode",
          "FE1",
          "The carrier specific code of the service for which the schedule details are published.");

  private final Parameter universalServiceReference =
      createStringQueryParameter(
          "universalServiceReference",
          "SR12345A",
          "Globally unique identifier of a liner service, assigned by carriers as specified by DCSA.");

  private final Parameter portOfDischargeArrivalDateMin =
      createDateQueryParameter(
          "portOfDischargeArrivalDateMin",
          "2025-01-22",
          "Retrieve arrival notices with a date of arrival of the vessel at the Port of Discharge on or after this date");

  private final Parameter portOfDischargeArrivalDateMax =
      createDateQueryParameter(
          "portOfDischargeArrivalDateMax",
          "2025-01-23",
          "Retrieve arrival notices with a date of arrival of the vessel at the Port of Discharge on or before this date");

  private final Parameter removeCharges =
      createBooleanQueryParameter(
          "removeCharges",
          true,
"""
Optional flag indicating whether the publisher should remove the charges from the PDF visualization
of every returned arrival notice, and for consistency, also from the structured response data.

This flag allows arrival notice receivers to retrieve, for the purpose of forwarding to third parties,
versions of the arrival notice PDF visualizations in which the charges are removed,
if they would be normally received with charges included based on the role of the arrival notice recipient.

This flag is **not** expected to perform any filtering on the list of arrival notices included in the response.
However, if the removal of charges (from the arrival notices that have them) results in a list of arrival notices
in which some become exact duplicates, publishers may choose to remove duplicates from the response.

The default value is `false`, which leaves unchanged the presence or absence of charges in each returned arrival notice.
""");

  private final Parameter includeVisualization =
      createBooleanQueryParameter(
          "includeVisualization",
          true,
"""
Optional flag indicating whether the PDF `arrivalNoticeVisualization` should be included in each returned arrival notice.

The publisher makes the final decision on whether to include PDF visualizations in the response (for some or for all
the arrival notices), based on a variety of factors including:
- whether it has implemented support for including PDF visualizations
- the API consumer (role, registration profile, business relationship)
- the type and availability status of the returned arrival notices.

However, to support a future transition to fully automated processing of arrival notices by receivers,
the publisher should **not** include the PDF visualization if this parameter is set to `false`.
""");

  private final Parameter limit =
      createIntegerQueryParameter(
          "limit",
          10,
          "Maximum number of arrival notices to include in each page of the response.",
          schema -> schema.minimum(new BigDecimal(1)));

  private final Parameter cursor =
      createStringQueryParameter(
          "cursor",
          "ExampleNextPageCursor",
          "Set to the value of the `Next-Page-Cursor` header of the previous response to retrieve the next page.");

  @Override
  public List<Parameter> getQueryParameters() {
    return List.of(
        transportDocumentReferences,
        equipmentReferences,
        portOfDischarge,
        vesselIMONumber,
        vesselName,
        carrierImportVoyageNumber,
        universalImportVoyageReference,
        carrierServiceCode,
        universalServiceReference,
        portOfDischargeArrivalDateMin,
        portOfDischargeArrivalDateMax,
        includeVisualization,
        removeCharges,
        limit,
        cursor);
  }

  @Override
  public Map<Boolean, List<List<Parameter>>> getRequiredAndOptionalFilters() {
    return Map.ofEntries(
        Map.entry(
            Boolean.TRUE,
            List.of(
                // TDR only
                List.of(transportDocumentReferences))),
        Map.entry(
            Boolean.FALSE,
            List.of(
                // EQR
                List.of(transportDocumentReferences, equipmentReferences),
                List.of(equipmentReferences),
                List.of(
                    portOfDischargeArrivalDateMin,
                    portOfDischargeArrivalDateMax,
                    equipmentReferences),
                // POD
                List.of(
                    portOfDischargeArrivalDateMin, portOfDischargeArrivalDateMax, portOfDischarge),
                // vessel IMO + voyage number (+ service)
                List.of(
                    portOfDischargeArrivalDateMin, portOfDischargeArrivalDateMax, vesselIMONumber),
                List.of(
                    portOfDischargeArrivalDateMin,
                    portOfDischargeArrivalDateMax,
                    vesselIMONumber,
                    carrierImportVoyageNumber),
                List.of(
                    portOfDischargeArrivalDateMin,
                    portOfDischargeArrivalDateMax,
                    vesselIMONumber,
                    carrierImportVoyageNumber,
                    carrierServiceCode),
                List.of(
                    portOfDischargeArrivalDateMin,
                    portOfDischargeArrivalDateMax,
                    vesselIMONumber,
                    carrierImportVoyageNumber,
                    universalServiceReference),
                // vessel IMO + voyage reference (+ service)
                List.of(
                    portOfDischargeArrivalDateMin,
                    portOfDischargeArrivalDateMax,
                    vesselIMONumber,
                    universalImportVoyageReference),
                List.of(
                    portOfDischargeArrivalDateMin,
                    portOfDischargeArrivalDateMax,
                    vesselIMONumber,
                    universalImportVoyageReference,
                    carrierServiceCode),
                List.of(
                    portOfDischargeArrivalDateMin,
                    portOfDischargeArrivalDateMax,
                    vesselIMONumber,
                    universalImportVoyageReference,
                    universalServiceReference),
                // vessel name + voyage number (+ service)
                List.of(portOfDischargeArrivalDateMin, portOfDischargeArrivalDateMax, vesselName),
                List.of(
                    portOfDischargeArrivalDateMin,
                    portOfDischargeArrivalDateMax,
                    vesselName,
                    carrierImportVoyageNumber),
                List.of(
                    portOfDischargeArrivalDateMin,
                    portOfDischargeArrivalDateMax,
                    vesselName,
                    carrierImportVoyageNumber,
                    carrierServiceCode),
                List.of(
                    portOfDischargeArrivalDateMin,
                    portOfDischargeArrivalDateMax,
                    vesselName,
                    carrierImportVoyageNumber,
                    universalServiceReference),
                // vessel name + voyage reference (+ service)
                List.of(
                    portOfDischargeArrivalDateMin,
                    portOfDischargeArrivalDateMax,
                    vesselName,
                    universalImportVoyageReference),
                List.of(
                    portOfDischargeArrivalDateMin,
                    portOfDischargeArrivalDateMax,
                    vesselName,
                    universalImportVoyageReference,
                    carrierServiceCode),
                List.of(
                    portOfDischargeArrivalDateMin,
                    portOfDischargeArrivalDateMax,
                    vesselName,
                    universalImportVoyageReference,
                    universalServiceReference))));
  }
}
