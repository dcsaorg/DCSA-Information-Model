package org.dcsa.standards.specifications.standards;

import io.swagger.v3.oas.models.media.ArraySchema;
import io.swagger.v3.oas.models.media.ComposedSchema;
import io.swagger.v3.oas.models.media.Discriminator;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.parser.OpenAPIV3Parser;

import java.nio.file.Files;
import java.nio.file.Path;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.HashSet;
import java.util.HexFormat;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.dcsa.standards.specifications.generator.SpecificationToolkit;
import org.dcsa.standards.specifications.generator.StandardSpecification;
import org.junit.jupiter.api.Assertions;

@Slf4j
public enum StandardSpecificationTestToolkit {
  ; // no instances

  public static final boolean FAIL_ON_FIRST_WRONG_VALUE = System.currentTimeMillis() < 0;

  private static final java.util.concurrent.atomic.AtomicInteger WRONG_VALUE_COUNT =
      new java.util.concurrent.atomic.AtomicInteger();

  @SneakyThrows
  public static String getFileHash(String filePath) {
    Path path = Path.of(filePath);
    String hash =
        HexFormat.of()
            .formatHex(MessageDigest.getInstance("SHA-256").digest(Files.readAllBytes(path)))
            .toUpperCase();
    log.info("The hash of '{}' is '{}'", path.getFileName(), hash);
    return hash;
  }

  public static void verifyTypeExport(
      String typeName, String yamlFilePath, StandardSpecification standardSpecification) {
    Map<String, Schema<?>> originalSchemas =
        SpecificationToolkit.parameterizeStringRawSchemaMap(
            new OpenAPIV3Parser().read(yamlFilePath).getComponents().getSchemas());
    Map<String, Schema<?>> generatedSchemas =
        SpecificationToolkit.parameterizeStringRawSchemaMap(
            standardSpecification.getOpenAPI().getComponents().getSchemas());
    compareType("", originalSchemas, generatedSchemas, typeName, new HashSet<>());
    int wrongValues = WRONG_VALUE_COUNT.getAndSet(0);
    if (wrongValues > 0) {
      Assertions.fail(
          "Found " + wrongValues + " wrong value(s) while comparing type '" + typeName
              + "' against '" + yamlFilePath + "' (see WRONG VALUE log warnings above)");
    }
  }

  private static void compareType(
      String indentation,
      Map<String, Schema<?>> originalSchemasByType,
      Map<String, Schema<?>> generatedSchemasByType,
      String typeName,
      Set<String> visitedTypes) {
    if (!visitedTypes.add(typeName)) {
      return;
    }
    log.info("{}Comparing object: {}", indentation, typeName);

    Schema<?> originalTypeSchema = originalSchemasByType.get(typeName);
    Schema<?> generatedTypeSchema = generatedSchemasByType.get(typeName);

    if (originalTypeSchema == null || generatedTypeSchema == null) {
      if (originalTypeSchema == null && generatedTypeSchema == null) {
        return;
      }
      log.warn("Schema '{}' exists in {} but not in {}",
          typeName,
          originalTypeSchema != null ? "original" : "generated",
          originalTypeSchema != null ? "generated" : "original");
      return;
    }
    Map<String, Schema<?>> originalProperties = getSchemaProperties(originalTypeSchema, originalSchemasByType);
    Map<String, Schema<?>> generatedProperties = getSchemaProperties(generatedTypeSchema, generatedSchemasByType);
    softAssertEquals(
        "property list",
        new TreeSet<>(originalProperties.keySet()),
        new TreeSet<>(generatedProperties.keySet()));
    softAssertEquals(
        "required properties", originalTypeSchema.getRequired(), generatedTypeSchema.getRequired());
    softAssertEquals(
        "type description (" + typeName + ")",
        comparableDescription(originalTypeSchema.getDescription()),
        comparableDescription(generatedTypeSchema.getDescription()));

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
                  generatedAttributeSchema,
                  originalSchemasByType,
                  generatedSchemasByType,
                  visitedTypes);
            });

    originalProperties.keySet().stream()
        .sorted()
        .forEach(
            attributeName -> {
              Schema<?> originalAttributeSchema = originalProperties.get(attributeName);
              Schema<?> generatedAttributeSchema = generatedProperties.get(attributeName);
              String attributeTypeName = getAttributeTypeName(originalAttributeSchema);
              if (attributeTypeName == null && generatedAttributeSchema != null) {
                attributeTypeName = getAttributeTypeName(generatedAttributeSchema);
              }
              if (attributeTypeName != null) {
                compareType(
                    "    " + indentation,
                    originalSchemasByType,
                    generatedSchemasByType,
                    attributeTypeName,
                    visitedTypes);
              }
            });
  }

  private static void compareAttribute(
      String indentation,
      String typeName,
      String attributeName,
      Schema<?> originalAttributeSchema,
      Schema<?> generatedAttributeSchema,
      Map<String, Schema<?>> originalSchemasByType,
      Map<String, Schema<?>> generatedSchemasByType,
      Set<String> visitedTypes) {
    log.info("{}Comparing {} {}", indentation, typeName, attributeName);
    if (originalAttributeSchema == null || generatedAttributeSchema == null) {
      log.warn(
          "Missing attribute schema for {}.{} (original={}, generated={})",
          typeName,
          attributeName,
          originalAttributeSchema != null,
          generatedAttributeSchema != null);
      return;
    }
    if (generatedAttributeSchema instanceof ComposedSchema && generatedAttributeSchema.getAllOf() != null) {
      if (!(originalAttributeSchema instanceof ComposedSchema && originalAttributeSchema.getAllOf() != null)) {
        // Generated is allOf-wrapped $ref with description; original is inline/direct
        // Compare description from the wrapper only if original has one too
        String originalDesc = comparableDescription(originalAttributeSchema.getDescription());
        String generatedDesc = comparableDescription(generatedAttributeSchema.getDescription());
        if (!originalDesc.isEmpty()) {
          softAssertEquals("description", originalDesc, generatedDesc);
        }
        // Recurse into the referenced type, using the original inline schema as the type definition
        // if it's not available in the original schemas map (parser may have inlined it)
        String refTypeName = getAttributeTypeName(generatedAttributeSchema.getAllOf().getFirst());
        if (refTypeName != null && !visitedTypes.contains(refTypeName)) {
          Schema<?> originalTypeForRef = originalSchemasByType.get(refTypeName);
          if (originalTypeForRef == null && originalAttributeSchema.getProperties() != null) {
            // Parser inlined the $ref – compare directly using the inline schema as the type
            visitedTypes.add(refTypeName);
            Schema<?> generatedTypeForRef = generatedSchemasByType.get(refTypeName);
            if (generatedTypeForRef != null) {
              log.info("{}  Comparing inlined type: {}", indentation, refTypeName);
              Map<String, Schema<?>> origProps = SpecificationToolkit.parameterizeStringRawSchemaMap(originalAttributeSchema.getProperties());
              Map<String, Schema<?>> genProps = SpecificationToolkit.parameterizeStringRawSchemaMap(generatedTypeForRef.getProperties());
              softAssertEquals("property list of " + refTypeName,
                  new TreeSet<>(origProps.keySet()), new TreeSet<>(genProps.keySet()));
              origProps.keySet().stream().sorted().forEach(propName -> {
                Schema<?> origProp = origProps.get(propName);
                Schema<?> genProp = genProps.get(propName);
                if (genProp != null) {
                  softAssertEquals(indentation, origProp, genProp, visitedTypes);
                }
              });
            }
          }
        }
        return;
      }
    }
    // Handle oneOf comparison
    if (originalAttributeSchema.getOneOf() != null && generatedAttributeSchema.getOneOf() != null) {
      softAssertEquals(
          "description",
          comparableDescription(originalAttributeSchema.getDescription()),
          comparableDescription(generatedAttributeSchema.getDescription()));
      List<String> originalOneOfRefs = originalAttributeSchema.getOneOf().stream()
          .map(s -> ((Schema<?>) s).get$ref())
          .filter(Objects::nonNull)
          .toList();
      List<String> generatedOneOfRefs = generatedAttributeSchema.getOneOf().stream()
          .map(s -> ((Schema<?>) s).get$ref())
          .filter(Objects::nonNull)
          .toList();
      softAssertEquals("oneOf", originalOneOfRefs, generatedOneOfRefs);
      // Compare discriminator
      Discriminator originalDisc = originalAttributeSchema.getDiscriminator();
      Discriminator generatedDisc = generatedAttributeSchema.getDiscriminator();
      if (originalDisc != null || generatedDisc != null) {
        softAssertEquals("discriminator.propertyName",
            originalDisc != null ? originalDisc.getPropertyName() : null,
            generatedDisc != null ? generatedDisc.getPropertyName() : null);
        softAssertEquals("discriminator.mapping",
            originalDisc != null ? originalDisc.getMapping() : null,
            generatedDisc != null ? generatedDisc.getMapping() : null);
      }
      // Recurse into each oneOf type
      for (String ref : originalOneOfRefs) {
        String refTypeName = ref.substring(ref.lastIndexOf('/') + 1);
        compareType("    " + indentation, originalSchemasByType, generatedSchemasByType,
            refTypeName, visitedTypes);
      }
      return;
    }
    softAssertEquals(indentation, originalAttributeSchema, generatedAttributeSchema, visitedTypes);
  }

  private static void softAssertEquals(
      String indentation, Schema<?> originalAttributeSchema, Schema<?> generatedAttributeSchema,
      Set<String> visitedTypes) {
    softAssertEquals(
        "description",
        comparableDescription(originalAttributeSchema.getDescription()),
        comparableDescription(generatedAttributeSchema.getDescription()));

    softAssertEquals(
        "type",
        getAttributeTypeName(originalAttributeSchema),
        getAttributeTypeName(generatedAttributeSchema));
    softAssertEquals(
        "schema type",
        originalAttributeSchema.getType(),
        generatedAttributeSchema.getType());
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
      String itemTypeName = getAttributeTypeName(originalAttributeSchema.getItems());
      if (itemTypeName == null || !visitedTypes.contains(itemTypeName)) {
        softAssertEquals(
            indentation, originalAttributeSchema.getItems(), generatedAttributeSchema.getItems(),
            visitedTypes);
      }
    }
  }

  private static void softAssertEquals(String property, Object expected, Object actual) {
    if (!Objects.equals(expected, actual)) {
      WRONG_VALUE_COUNT.incrementAndGet();
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
(escaped view)
expected: {}
actual:   {}
""",
          property,
          expected,
          actual,
          escapeNonPrintable(expected),
          escapeNonPrintable(actual));
    }
    if (FAIL_ON_FIRST_WRONG_VALUE) {
      Assertions.assertEquals(expected, actual, "Wrong value for: " + property);
    }
  }

  private static String escapeNonPrintable(Object value) {
    if (value == null) return "null";
    String str = value.toString();
    StringBuilder sb = new StringBuilder();
    for (char c : str.toCharArray()) {
      if (c < 32 || c == 127) {
        sb.append(String.format("\\x%02X", (int) c));
      } else {
        sb.append(c);
      }
    }
    return sb.toString();
  }

  private static String comparableDescription(String description) {
    return description == null ? "" : description.replace("\r\n", "\n").replace("\r", "\n").trim();
  }

  private static String getAttributeTypeName(Schema<?> attributeSchema) {
    if (attributeSchema.getItems() != null) {
      return getAttributeTypeName(attributeSchema.getItems());
    }
    if (attributeSchema.get$ref() != null) {
      return Arrays.stream(attributeSchema.get$ref().split("/")).toList().getLast();
    }
    if (attributeSchema.getAllOf() != null && !attributeSchema.getAllOf().isEmpty()) {
      return getAttributeTypeName(attributeSchema.getAllOf().getFirst());
    }
    return null;
  }

  private static Map<String, Schema<?>> getSchemaProperties(
      Schema<?> schema, Map<String, Schema<?>> allSchemas) {
    Map<String, Schema<?>> allProperties =
        SpecificationToolkit.parameterizeStringRawSchemaMap(schema.getProperties());
    Stream.of(schema.getAllOf(), schema.getAnyOf(), schema.getOneOf())
        .filter(Objects::nonNull)
        .forEach(
            schemaList ->
                allProperties.putAll(
                    schemaList.stream()
                        .map(subSchema -> resolveSchema(subSchema, allSchemas))
                        .flatMap(
                            subSchema ->
                                getSchemaProperties(subSchema, allSchemas).entrySet().stream())
                        .collect(
                            Collectors.toMap(
                                Map.Entry::getKey, Map.Entry::getValue, (a, _) -> a))));
    return allProperties;
  }

  private static Schema<?> resolveSchema(Schema<?> schema, Map<String, Schema<?>> allSchemas) {
    if (schema.get$ref() != null) {
      String refName = schema.get$ref().substring(schema.get$ref().lastIndexOf('/') + 1);
      Schema<?> resolved = allSchemas.get(refName);
      if (resolved != null) {
        return resolved;
      }
    }
    return schema;
  }
}
