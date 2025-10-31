package org.dcsa.standards.specifications.constraints;

import lombok.RequiredArgsConstructor;

import java.lang.reflect.Field;
import java.util.List;

@RequiredArgsConstructor
public class AttributeOneRequiresAttributeTwo implements SchemaConstraint {
  private final Field attributeFieldOne;
  private final Field attributeFieldTwo;

  @Override
  public String getDescription() {
    return "Specifying attribute '%s' requires specifying attribute '%s'."
        .formatted(attributeFieldOne.getName(), attributeFieldTwo.getName());
  }

  @Override
  public List<Field> getTargetFields() {
    return List.of(attributeFieldOne, attributeFieldTwo);
  }
}
