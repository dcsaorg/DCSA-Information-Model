package org.dcsa.standards.specifications.standards.cs.v100;

import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.parameters.Parameter;
import java.util.List;
import java.util.Map;
import org.dcsa.standards.specifications.generator.QueryParametersFilterEndpoint;

public class GetVesselSchedulesEndpoint extends QueryParametersFilterEndpoint {

  private final Parameter vesselIMONumber =
      new Parameter()
          .in("query")
          .name("vesselIMONumber")
          .example("9321483")
          .description(
              "The unique reference for a registered Vessel. The reference is the International Maritime Organisation (IMO) number, also sometimes known as the Lloyd's register code, which does not change during the lifetime of the vessel.")
          .schema(
              new Schema<String>().type("string").pattern("^\\d{7,8}$").minLength(7).maxLength(8));

  private final Parameter vesselName =
      createStringQueryParameter(
          "vesselName",
          "King of the Seas",
          "The name of a vessel. The result will only return schedules including the vessel with the specified name.  Be aware that the vesselName is not unique and might match multiple vessels.");

  private final Parameter carrierServiceCode =
      createStringQueryParameter(
          "carrierServiceCode",
          "FE1",
          "The carrier specific service code to filter by. The result will only return schedules including the service code.");

  private final Parameter universalServiceReference =
      new Parameter()
          .in("query")
          .name("universalServiceReference")
          .example("SR12345A")
          .description("The Universal Service Reference (USR) as defined by DCSA to filter by.")
          .schema(
              new Schema<String>()
                  .type("string")
                  .pattern("^SR\\d{5}[A-Z]$")
                  .minLength(8)
                  .maxLength(8));

  private final Parameter carrierVoyageNumber =
      createStringQueryParameter(
          "carrierVoyageNumber",
          "2103S",
          "The carrier specific identifier of a Voyage - can be both `importVoyageNumber` and `exportVoyageNumber`. The result will only return schedules including the Ports where `carrierVoyageNumber` is either `carrierImportVoyageNumber` or `carrierExportVoyageNumber`.");

  private final Parameter universalVoyageReference =
      new Parameter()
          .in("query")
          .name("universalVoyageReference")
          .example("2201N")
          .description(
              "The Universal Reference of a Voyage - can be both `importUniversalVoyageReference` and `exportUniversalVoyageReference`. The result will only return schedules including the Ports where `universalVoyageReference` is either `importUniversalVoyageReference` or `exportUniversalVoyageReference`.")
          .schema(
              new Schema<String>()
                  .type("string")
                  .pattern("^\\d{2}[0-9A-Z]{2}[NEWSR]$")
                  .maxLength(5));

  private final Parameter unLocationCode =
      new Parameter()
          .in("query")
          .name("UNLocationCode")
          .example("NLAMS")
          .description(
              "The UN Location Code specifying where a port is located.  Specifying this filter will only return schedules including entire Voyages related to this particular UN Location Code.")
          .schema(
              new Schema<String>()
                  .type("string")
                  .pattern("^[A-Z]{2}[A-Z2-9]{3}$")
                  .minLength(5)
                  .maxLength(5));

  private final Parameter facilitySMDGCode =
      createStringQueryParameter(
          "facilitySMDGCode",
          "APM",
          "The facilitySMDGCode specifying a specific facility (using SMDG Code). Be aware that the `facilitySMDGCode` does not contain a `UNLocationCode` - this must be specified in the `UNLocationCode` filter.  Specifying this filter will only return schedules including entire Voyages related to this particular `facilitySMDGCode`.");

  private final Parameter vesselOperatorCarrierCode =
      createStringQueryParameter(
          "vesselOperatorCarrierCode",
          "MAEU",
          "The carrier who is in charge of the vessel operation based on either the SMDG or SCAC code lists.");

  private final Parameter startDate =
      createDateQueryParameter(
          "startDate",
          "2020-04-06",
          "The start date of the period for which schedule information is requested. If a date of any Timestamp (ATA, ETA or PTA) inside a PortCall matches a date on or after (≥) the `startDate` the entire Voyage (import- and export-Voyage) matching the PortCall will be included in the result. All matching is done towards local Date at the place of the port call. If this filter is not provided the default value is `3 months` prior to request time. The value is populated in ISO 8601 date format.");

  private final Parameter endDate =
      createDateQueryParameter(
          "endDate",
          "2020-04-10",
          "The end date of the period for which schedule information is requested. If a date of any Timestamp (ATA, ETA or PTA) inside a PortCall matches a date on or before (≤) the `endDate` the entire Voyage(import- and export-Voyage) matching the PortCall will be included in the result. All matching is done towards local Date at the place of the port call. If this filter is not provided the default value is `6 months` after request time. The value is populated in ISO 8601 date format.");

  private final Parameter limit =
      new Parameter()
          .in("query")
          .name("limit")
          .example(100)
          .description("Specifies the maximum number of `Service Schedule` objects to return.");

  private final Parameter cursor =
      createStringQueryParameter(
          "cursor",
          "fE9mZnNldHw9MTAmbGltaXQ9MTA",
          "A server generated value to specify a specific point in a collection result, used for pagination.");

  @Override
  public List<Parameter> getQueryParameters() {
    return List.of(
        vesselIMONumber,
        vesselName,
        carrierServiceCode,
        universalServiceReference,
        carrierVoyageNumber,
        universalVoyageReference,
        unLocationCode,
        facilitySMDGCode,
        vesselOperatorCarrierCode,
        startDate,
        endDate,
        limit,
        cursor);
  }

  @Override
  public Map<Boolean, List<List<Parameter>>> getRequiredAndOptionalFilters() {
    // At least one filter parameter must be provided
    return Map.ofEntries(
        Map.entry(Boolean.TRUE, List.of(List.of())), Map.entry(Boolean.FALSE, List.of()));
  }
}
