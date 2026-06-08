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

public class AttributesData {
  private final ArrayList<AttributeInfo> attributeInfoList = new ArrayList<>();
  private final List<String> rootTypeNames;

  public AttributesData(
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

                        // Handle oneOf: expand into one attribute per variant
                        if (attributeSchema.getOneOf() != null && !attributeSchema.getOneOf().isEmpty()) {
                          List<Schema<?>> oneOfSchemas = SpecificationToolkit.parameterizeRawSchemaList(attributeSchema.getOneOf());
                          for (Schema<?> oneOfSchema : oneOfSchemas) {
                            String $ref = oneOfSchema.get$ref();
                            if ($ref != null) {
                              String variantType = $ref.substring("#/components/schemas/".length());
                              AttributeInfo variantInfo = new AttributeInfo();
                              variantInfo.setObjectType(typeName);
                              variantInfo.setAttributeName("%s as %s".formatted(attributeName, variantType));
                              variantInfo.setAttributeType(variantType);
                              variantInfo.setAttributeBaseType(variantType);
                              variantInfo.setRequired(requiredAttributes.contains(attributeName));
                              variantInfo.setSize("");
                              variantInfo.setPattern("");
                              variantInfo.setExample("");
                              variantInfo.setDescription(
                                  Objects.requireNonNullElse(attributeSchema.getDescription(), "").trim());
                              attributeInfoList.add(variantInfo);
                            }
                          }
                          requiredAttributes.remove(attributeName);
                          return;
                        }

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
                        Schema<?> itemSchema = attributeSchema.getItems();
                        if (itemSchema != null) {
                          if (itemSchema instanceof StringSchema) {
                            if (itemSchema.getDescription() != null) {
                              attributeInfo.setDescription(
                                  "%s\nItem description:\n%s"
                                      .formatted(
                                          attributeInfo.getDescription(),
                                          itemSchema.getDescription().trim()));
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
    Set<String> reachableTypes = getReachableTypes();
    return attributeInfoList.stream()
        .filter(attributeInfo -> reachableTypes.contains(attributeInfo.getObjectType()))
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
                    Objects.requireNonNullElse(attributeInfo.getDescription(), "")))
        .toList();
  }

  private Set<String> getReachableTypes() {
    Map<String, List<AttributeInfo>> attributeInfoByObjectType = new TreeMap<>();
    attributeInfoList.forEach(
        attributeInfo ->
            attributeInfoByObjectType
                .computeIfAbsent(attributeInfo.getObjectType(), _ -> new ArrayList<>())
                .add(attributeInfo));

    Set<String> reachableTypes = new HashSet<>();
    List<String> pending = new ArrayList<>(rootTypeNames);
    while (!pending.isEmpty()) {
      List<String> next = new ArrayList<>();
      for (String typeName : pending) {
        if (reachableTypes.add(typeName)) {
          attributeInfoByObjectType
              .getOrDefault(typeName, List.of())
              .forEach(attr -> {
                String baseType = attr.getAttributeBaseType();
                if (baseType != null && !reachableTypes.contains(baseType)) {
                  next.add(baseType);
                }
              });
        }
      }
      pending = next;
    }
    return reachableTypes;
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
                      String baseType = attributeInfo.getAttributeBaseType();
                      String newPath =
                          "%s / %s:%s"
                              .formatted(
                                  existingPath,
                                  attributeInfo.getAttributeName(),
                                  baseType);
                      attributesByPath.put(newPath, attributeInfo);
                      // Only expand further if this type is not already in the path (prevent circular references)
                      if (baseType == null || !hasTypeInPath(existingPath, baseType)) {
                        newPaths.add(newPath);
                      }
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
                  Objects.requireNonNullElse(attributeInfo.getDescription(), ""));
            })
        .toList();
  }

  private static boolean hasTypeInPath(String path, String type) {
    // Check for exact type match in path segments (format is "name:Type / name:Type / ...")
    // We need word-boundary matching to avoid "DocumentReference" matching "DocumentReferenceReplacement"
    int searchFrom = 0;
    String needle = ":" + type;
    while (true) {
      int idx = path.indexOf(needle, searchFrom);
      if (idx < 0) return false;
      int end = idx + needle.length();
      // Check that the match is followed by end-of-string, " / ", or " list"
      if (end == path.length()
          || path.startsWith(" / ", end)
          || path.startsWith(" list", end)) {
        return true;
      }
      searchFrom = end;
    }
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
    if (hasTypeWithFormat(attributeSchema)) {
      return attributeSchema;
    }
    if (attributeSchema.getItems() != null) {
      if (hasTypeWithFormat(attributeSchema.getItems())) {
        return attributeSchema.getItems();
      }
    }
    return null;
  }

  private static boolean hasTypeWithFormat(Schema<?> schema) {
    String type = schema.getType();
    return "string".equals(type) || "number".equals(type) || "integer".equals(type);
  }
}
