package org.dcsa.standards.specifications.constraints;

import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AtLeastOneAttributeIsRequired implements SchemaConstraint {
  private final List<Field> attributeFields;

  @Override
  public String getDescription() {
    return "At least one of these attributes is required: %s."
        .formatted(
            attributeFields.stream()
                .map(field -> "'%s'".formatted(field.getName()))
                .collect(Collectors.joining(", ")));
  }

  @Override
  public List<Field> getTargetFields() {
    return attributeFields;
  }
}
