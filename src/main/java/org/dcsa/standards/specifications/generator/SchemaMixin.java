package org.dcsa.standards.specifications.generator;

import com.fasterxml.jackson.annotation.JsonIgnore;

public abstract class SchemaMixin {
  @SuppressWarnings("unused")
  @JsonIgnore
  public boolean exampleSetFlag; // Ignore this field during serialization

  @SuppressWarnings("unused")
  @JsonIgnore
  public boolean valueSetFlag; // Ignore this field during serialization

  @JsonIgnore public Object types; // Ignore the "types" field in all schemas
}
