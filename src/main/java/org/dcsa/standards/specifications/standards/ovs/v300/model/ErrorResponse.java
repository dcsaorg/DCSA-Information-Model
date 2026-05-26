package org.dcsa.standards.specifications.standards.ovs.v300.model;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.Data;

@Schema(description = "Unexpected error")
@Data
public class ErrorResponse {

  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      description = "The HTTP method used to make the request e.g. `GET`, `POST`, etc",
      example = "POST",
      allowableValues = {"GET", "HEAD", "POST", "PUT", "DELETE", "OPTION", "PATCH"})
  private String httpMethod;

  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      description = "The URI that was requested.",
      example = "/v1/events")
  private String requestUri;

  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      description = "The HTTP status code returned.",
      format = "int32",
      example = "400")
  private Integer statusCode;

  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      description = "A standard short description corresponding to the HTTP status code.",
      maxLength = 50,
      example = "Bad Request")
  private String statusCodeText;

  @Schema(
      description =
          "A long description corresponding to the HTTP status code with additional information.",
      maxLength = 200,
      example = "The supplied data could not be accepted")
  private String statusCodeMessage;

  @Schema(
      description = "A unique identifier to the HTTP request within the scope of the API provider.",
      maxLength = 100,
      example = "4426d965-0dd8-4005-8c63-dc68b01c4962")
  private String providerCorrelationReference;

  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      description =
          "The DateTime corresponding to the error occuring. Must be formatted using [ISO 8601](https://en.wikipedia.org/wiki/ISO_8601) format.",
      format = "date-time",
      example = "2019-11-12T07:41:00+08:30")
  private String errorDateTime;

  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      description = "An array of errors provding more detail about the root cause.")
  private List<DetailedError> errors;
}
