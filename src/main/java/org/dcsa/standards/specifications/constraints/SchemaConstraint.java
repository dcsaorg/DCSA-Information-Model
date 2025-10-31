package org.dcsa.standards.specifications.constraints;

import java.lang.reflect.Field;
import java.util.List;

public interface SchemaConstraint {
  String getDescription();
  List<Field> getTargetFields();
}
