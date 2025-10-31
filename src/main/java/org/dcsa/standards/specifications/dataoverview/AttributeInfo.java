package org.dcsa.standards.specifications.dataoverview;

import lombok.Data;

@Data
public class AttributeInfo {
  private String objectType;
  private String attributeName;
  private String attributeType;
  private String attributeBaseType;
  private boolean required;
  private String size;
  private String pattern;
  private String example;
  private String description;
  private String constraints;
}
