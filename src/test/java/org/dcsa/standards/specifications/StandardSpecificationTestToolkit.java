package org.dcsa.standards.specifications;

import io.swagger.v3.oas.models.media.ArraySchema;
import io.swagger.v3.oas.models.media.ComposedSchema;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.parser.OpenAPIV3Parser;
import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.extern.slf4j.Slf4j;
import org.dcsa.standards.specifications.generator.SpecificationToolkit;
import org.dcsa.standards.specifications.generator.StandardSpecification;
import org.junit.jupiter.api.Assertions;

@Slf4j
public enum StandardSpecificationTestToolkit {
  ; // no instances

  static void verifyTypeExport(
      String typeName, String yamlFilePath, StandardSpecification standardSpecification) {
    Map<String, Schema<?>> originalSchemas =
        SpecificationToolkit.parameterizeStringRawSchemaMap(
            new OpenAPIV3Parser().read(yamlFilePath).getComponents().getSchemas());
    Map<String, Schema<?>> generatedSchemas =
        SpecificationToolkit.parameterizeStringRawSchemaMap(
            standardSpecification.getOpenAPI().getComponents().getSchemas());
    compareType("", originalSchemas, generatedSchemas, typeName);
  }

  private static void compareType(
      String indentation,
      Map<String, Schema<?>> originalSchemasByType,
      Map<String, Schema<?>> generatedSchemasByType,
      String typeName) {
    log.info("{}Comparing object: {}", indentation, typeName);

    Schema<?> originalTypeSchema = originalSchemasByType.get(typeName);
    Schema<?> generatedTypeSchema = generatedSchemasByType.get(typeName);

    Map<String, Schema<?>> originalProperties = getSchemaProperties(originalTypeSchema);
    Map<String, Schema<?>> generatedProperties = getSchemaProperties(generatedTypeSchema);
    softAssertEquals(
        "property list",
        new TreeSet<>(originalProperties.keySet()),
        new TreeSet<>(generatedProperties.keySet()));
    softAssertEquals(
        "required properties", originalTypeSchema.getRequired(), generatedTypeSchema.getRequired());

    originalProperties.keySet().stream()
        .sorted()
        .forEach(
            attributeName -> {
              Schema<?> originalAttributeSchema = originalProperties.get(attributeName);
              Schema<?> generatedAttributeSchema = generatedProperties.get(attributeName);
              compareAttribute(
                  "  " + indentation,
                  typeName,
                  attributeName,
                  originalAttributeSchema,
                  generatedAttributeSchema);
            });

    originalProperties.keySet().stream()
        .sorted()
        .forEach(
            attributeName -> {
              Schema<?> originalAttributeSchema = originalProperties.get(attributeName);
              String attributeTypeName = getAttributeTypeName(originalAttributeSchema);
              if (attributeTypeName != null) {
                compareType(
                    "    " + indentation,
                    originalSchemasByType,
                    generatedSchemasByType,
                    attributeTypeName);
              }
            });
  }

  private static void compareAttribute(
      String indentation,
      String typeName,
      String attributeName,
      Schema<?> originalAttributeSchema,
      Schema<?> generatedAttributeSchema) {
    log.info("{}Comparing {} {}", indentation, typeName, attributeName);
    if (generatedAttributeSchema instanceof ComposedSchema) {
      if (!(originalAttributeSchema instanceof ComposedSchema)) {
        compareAttribute(
            indentation,
            typeName,
            attributeName,
            originalAttributeSchema,
            generatedAttributeSchema.getAllOf().getFirst());
        return;
      }
    }
    softAssertEquals(indentation, originalAttributeSchema, generatedAttributeSchema);
  }

  private static void softAssertEquals(
      String indentation, Schema<?> originalAttributeSchema, Schema<?> generatedAttributeSchema) {
    softAssertEquals(
        "name",
        comparableDescription(originalAttributeSchema.getDescription()),
        comparableDescription(generatedAttributeSchema.getDescription()));

    softAssertEquals(
        "type",
        getAttributeTypeName(originalAttributeSchema),
        getAttributeTypeName(generatedAttributeSchema));
    softAssertEquals(
        "format", originalAttributeSchema.getFormat(), generatedAttributeSchema.getFormat());
    softAssertEquals(
        "pattern", originalAttributeSchema.getPattern(), generatedAttributeSchema.getPattern());

    softAssertEquals(
        "minLength",
        originalAttributeSchema.getMinLength(),
        generatedAttributeSchema.getMinLength());
    softAssertEquals(
        "maxLength",
        originalAttributeSchema.getMaxLength(),
        generatedAttributeSchema.getMaxLength());

    softAssertEquals(
        "minItems", originalAttributeSchema.getMinItems(), generatedAttributeSchema.getMinItems());
    softAssertEquals(
        "maxItems", originalAttributeSchema.getMaxItems(), generatedAttributeSchema.getMaxItems());

    softAssertEquals(
        "minimum", originalAttributeSchema.getMinimum(), generatedAttributeSchema.getMinimum());
    softAssertEquals(
        "maximum", originalAttributeSchema.getMaximum(), generatedAttributeSchema.getMaximum());

    softAssertEquals(
        "exclusiveMinimum",
        originalAttributeSchema.getExclusiveMinimum(),
        generatedAttributeSchema.getExclusiveMinimum());
    softAssertEquals(
        "exclusiveMaximum",
        originalAttributeSchema.getExclusiveMaximum(),
        generatedAttributeSchema.getExclusiveMaximum());

    softAssertEquals(
        "examples", originalAttributeSchema.getExamples(), generatedAttributeSchema.getExamples());

    if (originalAttributeSchema instanceof ArraySchema) {
      log.info("{}  Comparing array item schema", indentation);
      Assertions.assertInstanceOf(ArraySchema.class, generatedAttributeSchema);
      softAssertEquals(
          indentation, originalAttributeSchema.getItems(), generatedAttributeSchema.getItems());
    }
  }

  private static void softAssertEquals(String property, Object expected, Object actual) {
    if (!Objects.equals(expected, actual)) {
      log.warn(
"""
WRONG VALUE:
================
{}
<<<<<<<< expected <<<<<<<<
{}
>>>>>>>>  actual  >>>>>>>>
{}
================
""",
          property,
          expected,
          actual);
    }
    if (System.currentTimeMillis() > 0) {
      Assertions.assertEquals(expected, actual, "Wrong value for: " + property);
    }
  }

  private static String comparableDescription(String description) {
    return description == null ? "" : description.trim();
  }

  private static String getAttributeTypeName(Schema<?> attributeSchema) {
    if (attributeSchema.getItems() != null) {
      return getAttributeTypeName(attributeSchema.getItems());
    }
    if (attributeSchema.get$ref() != null) {
      return Arrays.stream(attributeSchema.get$ref().split("/")).toList().getLast();
    }
    return null;
  }

  private static Map<String, Schema<?>> getSchemaProperties(Schema<?> schema) {
    Map<String, Schema<?>> allProperties =
        SpecificationToolkit.parameterizeStringRawSchemaMap(schema.getProperties());
    Stream.of(schema.getAllOf(), schema.getAnyOf(), schema.getOneOf())
        .filter(Objects::nonNull)
        .forEach(
            schemaList ->
                allProperties.putAll(
                    schemaList.stream()
                        .flatMap(subSchema -> getSchemaProperties(subSchema).entrySet().stream())
                        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue))));
    return allProperties;
  }
}
