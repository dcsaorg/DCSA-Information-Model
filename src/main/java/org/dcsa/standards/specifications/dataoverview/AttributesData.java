package org.dcsa.standards.specifications.dataoverview;

import io.swagger.v3.oas.models.media.Schema;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import io.swagger.v3.oas.models.media.StringSchema;

import org.dcsa.standards.specifications.generator.SpecificationToolkit;
import org.dcsa.standards.specifications.constraints.SchemaConstraint;

public class AttributesData {
  private final ArrayList<AttributeInfo> attributeInfoList = new ArrayList<>();
  private final List<String> rootTypeNames;

  public AttributesData(
      Map<String, Map<String, List<SchemaConstraint>>> constraintsByClassAndField,
      Map<String, Schema<?>> schemas,
      List<String> rootTypeNames) {
    this.rootTypeNames = rootTypeNames;
    sortedCaseInsensitive(schemas.keySet())
        .forEach(
            typeName -> {
              Schema<?> typeSchema = schemas.get(typeName);
              Set<String> requiredAttributes =
                  new HashSet<>(
                      Objects.requireNonNullElse(typeSchema.getRequired(), Collections.emptySet()));
              Map<String, Schema<?>> typeAttributeProperties =
                  SpecificationToolkit.parameterizeStringRawSchemaMap(typeSchema.getProperties());
              sortedCaseInsensitive(typeAttributeProperties.keySet())
                  .forEach(
                      attributeName -> {
                        AttributeInfo attributeInfo = new AttributeInfo();
                        attributeInfo.setObjectType(typeName);
                        attributeInfo.setAttributeName(attributeName);

                        Schema<?> attributeSchema = typeAttributeProperties.get(attributeName);
                        String attributeSchemaType = attributeSchema.getType();
                        attributeInfo.setAttributeType("UNKNOWN");
                        attributeInfo.setAttributeBaseType(attributeInfo.getAttributeType());
                        attributeInfo.setRequired(requiredAttributes.remove(attributeName));
                        attributeInfo.setSize(getAttributeSizeInfo(attributeSchema));
                        switch (attributeSchemaType) {
                          case "array":
                            {
                              Schema<?> itemSchema = attributeSchema.getItems();
                              String itemType = itemSchema.getType();
                              if (itemType == null) {
                                String $ref = itemSchema.get$ref();
                                if ($ref != null) {
                                  itemType = $ref.substring("#/components/schemas/".length());
                                } else {
                                  itemType = "UNKNOWN";
                                }
                              }
                              attributeInfo.setAttributeType("%s list".formatted(itemType));
                              attributeInfo.setAttributeBaseType(itemType);
                              break;
                            }
                          case "object":
                            {
                              List<Schema<?>> allOf =
                                  SpecificationToolkit.parameterizeRawSchemaList(
                                      attributeSchema.getAllOf());
                              if (allOf.size() == 1) {
                                String $ref = allOf.getFirst().get$ref();
                                if ($ref != null) {
                                  attributeInfo.setAttributeType(
                                      $ref.substring("#/components/schemas/".length()));
                                  attributeInfo.setAttributeBaseType(
                                      attributeInfo.getAttributeType());
                                }
                              }
                              break;
                            }
                          case null:
                            {
                              String $ref = attributeSchema.get$ref();
                              if ($ref != null) {
                                attributeInfo.setAttributeType(
                                    $ref.substring("#/components/schemas/".length()));
                                attributeInfo.setAttributeBaseType(
                                    attributeInfo.getAttributeType());
                              } else {
                                if (attributeSchema.getAllOf() != null) {
                                  $ref = attributeSchema.getAllOf().getFirst().get$ref();
                                  attributeInfo.setAttributeType(
                                      $ref.substring("#/components/schemas/".length()));
                                  attributeInfo.setAttributeBaseType(
                                      attributeInfo.getAttributeType());
                                } else {
                                  attributeInfo.setAttributeType("string");
                                  attributeInfo.setAttributeBaseType(
                                      attributeInfo.getAttributeType());
                                }
                              }
                              break;
                            }
                          default:
                            {
                              attributeInfo.setAttributeType(attributeSchemaType);
                              attributeInfo.setAttributeBaseType(attributeInfo.getAttributeType());
                              break;
                            }
                        }
                        attributeInfo.setExample(
                            Objects.requireNonNullElse(attributeSchema.getExample(), "")
                                .toString());
                        attributeInfo.setDescription(
                            Objects.requireNonNullElse(attributeSchema.getDescription(), "")
                                .trim());
                        attributeInfo.setConstraints(
                            constraintsByClassAndField
                                .getOrDefault(attributeInfo.getObjectType(), Map.of())
                                .getOrDefault(attributeInfo.getAttributeName(), List.of())
                                .stream()
                                .map(SchemaConstraint::getDescription)
                                .collect(Collectors.joining("\n\n")));
                        if (!attributeInfo.getConstraints().isEmpty()
                            && attributeInfo
                                .getDescription()
                                .endsWith(attributeInfo.getConstraints())) {
                          attributeInfo.setDescription(
                              attributeInfo
                                  .getDescription()
                                  .substring(
                                      0,
                                      attributeInfo.getDescription().length()
                                          - attributeInfo.getConstraints().length())
                                  .trim());
                        }
                        Schema<?> itemSchema = attributeSchema.getItems();
                        if (itemSchema != null) {
                          if (itemSchema instanceof StringSchema) {
                            if (itemSchema.getDescription() != null) {
                              attributeInfo.setDescription(
                                  "%s\nItem description:\n%s"
                                      .formatted(
                                          attributeInfo.getDescription(),
                                          itemSchema.getDescription()));
                            }
                          }
                        }
                        attributeInfo.setPattern("");
                        Schema<?> attributeStringSchema = getAttributeStringSchema(attributeSchema);
                        if (attributeStringSchema != null) {
                          String schemaPattern = attributeStringSchema.getPattern();
                          if (schemaPattern != null) {
                            attributeInfo.setPattern(schemaPattern);
                          }
                          if (attributeStringSchema.getFormat() != null
                              && !attributeStringSchema.getFormat().isEmpty()) {
                            attributeInfo.setAttributeType(
                                "%s(%s)"
                                    .formatted(
                                        attributeInfo.getAttributeType(),
                                        attributeStringSchema.getFormat()));
                          }
                        }
                        attributeInfoList.add(attributeInfo);
                      });
            });
  }

  private static Stream<String> sortedCaseInsensitive(Collection<String> values) {
    TreeSet<String> sortedSet = new TreeSet<>(String::compareToIgnoreCase);
    sortedSet.addAll(values);
    return sortedSet.stream();
  }

  public List<List<String>> getNormalizedRows() {
    return attributeInfoList.stream()
        .map(
            attributeInfo ->
                List.of(
                    Objects.requireNonNullElse(attributeInfo.getObjectType(), ""),
                    Objects.requireNonNullElse(attributeInfo.getAttributeName(), ""),
                    Objects.requireNonNullElse(attributeInfo.getAttributeType(), ""),
                    attributeInfo.isRequired() ? "yes" : "",
                    Objects.requireNonNullElse(attributeInfo.getSize(), ""),
                    Objects.requireNonNullElse(attributeInfo.getPattern(), ""),
                    Objects.requireNonNullElse(attributeInfo.getExample(), ""),
                    Objects.requireNonNullElse(attributeInfo.getDescription(), ""),
                    Objects.requireNonNullElse(attributeInfo.getConstraints(), "")))
        .toList();
  }

  public List<List<String>> getHierarchicalRows() {
    TreeMap<String, AttributeInfo> attributesByPath = new TreeMap<>(String::compareToIgnoreCase);
    rootTypeNames.forEach(
        rootTypeName ->
            attributesByPath.put(
                "%s:%s".formatted(rootTypeName, rootTypeName), new AttributeInfo()));

    Map<String, List<AttributeInfo>> attributeInfoByObjectType = new TreeMap<>();
    attributeInfoList.forEach(
        attributeInfo ->
            attributeInfoByObjectType
                .computeIfAbsent(
                    attributeInfo.getObjectType(), (ignoredObjectType) -> new ArrayList<>())
                .add(attributeInfo));

    ArrayList<String> newPaths = new ArrayList<>(attributesByPath.keySet());
    while (!newPaths.isEmpty()) {
      ArrayList<String> pendingPaths = new ArrayList<>(newPaths);
      newPaths.clear();
      pendingPaths.forEach(
          existingPath -> {
            String lastObjectType = existingPath.substring(1 + existingPath.lastIndexOf(":"));
            attributeInfoByObjectType
                .getOrDefault(lastObjectType, List.of())
                .forEach(
                    attributeInfo -> {
                      String newPath =
                          "%s / %s:%s"
                              .formatted(
                                  existingPath,
                                  attributeInfo.getAttributeName(),
                                  attributeInfo.getAttributeBaseType());
                      attributesByPath.put(newPath, attributeInfo);
                      newPaths.add(newPath);
                    });
          });
    }
    return attributesByPath.entrySet().stream()
        .map(
            pathAndAttributeInfo -> {
              String path =
                  Arrays.stream(pathAndAttributeInfo.getKey().split(" / "))
                      .map(nameAndType -> nameAndType.substring(0, nameAndType.indexOf(":")))
                      .collect(Collectors.joining(" / "));
              AttributeInfo attributeInfo = pathAndAttributeInfo.getValue();
              return List.of(
                  path,
                  Objects.requireNonNullElse(attributeInfo.getAttributeName(), ""),
                  Objects.requireNonNullElse(attributeInfo.getAttributeType(), ""),
                  attributeInfo.isRequired() ? "yes" : "",
                  Objects.requireNonNullElse(attributeInfo.getSize(), ""),
                  Objects.requireNonNullElse(attributeInfo.getPattern(), ""),
                  Objects.requireNonNullElse(attributeInfo.getExample(), ""),
                  Objects.requireNonNullElse(attributeInfo.getDescription(), ""),
                  Objects.requireNonNullElse(attributeInfo.getConstraints(), ""));
            })
        .toList();
  }

  private static String getAttributeSizeInfo(Schema<?> attributeSchema) {
    Schema<?> arraySchema = attributeSchema.getItems() == null ? null : attributeSchema;
    Schema<?> itemSchema =
        attributeSchema.getItems() == null ? attributeSchema : attributeSchema.getItems();
    return Stream.of(
            arraySchema == null ? null : getConstraintValue("minItems", arraySchema.getMinItems()),
            arraySchema == null ? null : getConstraintValue("maxItems", arraySchema.getMaxItems()),
            getConstraintValue("minLength", itemSchema.getMinLength()),
            getConstraintValue("maxLength", itemSchema.getMaxLength()),
            getConstraintValue("minimum", itemSchema.getMinimum()),
            getConstraintValue("maximum", itemSchema.getMaximum()),
            getConstraintValue("exclMin", itemSchema.getExclusiveMinimumValue()),
            getConstraintValue("exclMax", itemSchema.getExclusiveMaximumValue()))
        .filter(Objects::nonNull)
        .collect(Collectors.joining("\n"));
  }

  private static String getConstraintValue(String title, BigDecimal constraintValue) {
    return constraintValue == null ? null : getConstraintValue(title, constraintValue.intValue());
  }

  private static String getConstraintValue(String title, Integer constraintValue) {
    return constraintValue == null
            || constraintValue == Integer.MIN_VALUE
            || constraintValue == Integer.MAX_VALUE
        ? null
        : "%s=%d".formatted(title, constraintValue);
  }

  private static Schema<?> getAttributeStringSchema(Schema<?> attributeSchema) {
    if ("string".equals(attributeSchema.getType())) {
      return attributeSchema;
    }
    if (attributeSchema.getItems() != null) {
      if ("string".equals(attributeSchema.getItems().getType())) {
        return attributeSchema.getItems();
      }
    }
    return null;
  }
}
