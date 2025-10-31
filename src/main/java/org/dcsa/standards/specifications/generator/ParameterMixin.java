package org.dcsa.standards.specifications.generator;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.models.parameters.Parameter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@SuppressWarnings("unused")
public abstract class ParameterMixin {

  @JsonProperty("name")
  private String name;

  @JsonProperty("in")
  private String in;

  @JsonProperty("description")
  private String description;

  @JsonProperty("required")
  private Boolean required;

  @JsonProperty("style")
  private Parameter.StyleEnum style;

  @JsonProperty("explode") // inadvertently skipped by default
  private Boolean explode;

  @JsonProperty("schema")
  private Object schema;

  @JsonProperty("example")
  private Object example;
}
