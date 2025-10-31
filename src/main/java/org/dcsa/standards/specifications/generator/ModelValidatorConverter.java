package org.dcsa.standards.specifications.generator;

import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.SimpleType;
import io.swagger.v3.core.converter.AnnotatedType;
import io.swagger.v3.core.converter.ModelConverter;
import io.swagger.v3.core.converter.ModelConverterContext;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.models.media.ComposedSchema;
import io.swagger.v3.oas.models.media.DateSchema;
import io.swagger.v3.oas.models.media.Schema;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.dcsa.standards.specifications.constraints.SchemaConstraint;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ModelValidatorConverter implements ModelConverter {
  public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
  private final Map<String, Map<String, List<SchemaConstraint>>> constraintsByClassAndField;
  private final Map<String, Class<?>> modelClassesBySimpleName;
  private final Map<String, Map<String, Schema<?>>> originalSchemasByClassAndField =
      new TreeMap<>();

  public ModelValidatorConverter(
      Map<String, Map<String, List<SchemaConstraint>>> constraintsByClassAndField,
      Stream<Class<?>> modelClassesStream) {
    this.constraintsByClassAndField = constraintsByClassAndField;
    this.modelClassesBySimpleName =
        modelClassesStream.collect(Collectors.toMap(Class::getSimpleName, Function.identity()));
  }

  @SneakyThrows
  @Override
  public Schema<?> resolve(
      AnnotatedType annotatedType,
      ModelConverterContext modelConverterContext,
      Iterator<ModelConverter> modelConverterIterator) {
    Schema<?> schema =
        modelConverterIterator.hasNext()
            ? modelConverterIterator
                .next()
                .resolve(annotatedType, modelConverterContext, modelConverterIterator)
            : null;
    if (schema == null) return null;

    if (annotatedType.getType() instanceof SimpleType simpleType) {
      log.debug(
          "  attribute: {} {} {}",
          annotatedType.getParent().getName(),
          annotatedType.getPropertyName(),
          simpleType.getRawClass().getSimpleName());

      Field javaFieldWithThisPropertyName =
          getJavaFieldWithPropertyName(
              modelClassesBySimpleName.get(annotatedType.getParent().getName()),
              annotatedType.getPropertyName());
      if (javaFieldWithThisPropertyName != null) {
        if (!List.class.isAssignableFrom(javaFieldWithThisPropertyName.getType())) {
          Arrays.stream(annotatedType.getCtxAnnotations())
              .filter(
                  annotation -> annotation instanceof io.swagger.v3.oas.annotations.media.Schema)
              .map(annotation -> (io.swagger.v3.oas.annotations.media.Schema) annotation)
              .forEach(
                  schemaAnnotation -> {
                    String description = schemaAnnotation.description();
                    if (description != null && !description.isEmpty()) {
                      schema.setDescription(description);
                    }
                    String format = schemaAnnotation.format();
                    if (format != null && !format.isEmpty()) {
                      schema.setFormat(format);
                    }
                  });
        }
      }

      originalSchemasByClassAndField
          .computeIfAbsent(annotatedType.getParent().getName(), ignoredKey -> new TreeMap<>())
          .put(annotatedType.getPropertyName(), schema);

      if (simpleType.isEnumType()) {
        schema.setDescription(
            schema.getDescription()
                + "\n"
                + Arrays.stream(
                        Class.forName(simpleType.getRawClass().getName()).getEnumConstants())
                    .map(
                        enumConstant ->
                            "- `%s` - %s"
                                .formatted(
                                    ((EnumBase) enumConstant).name(),
                                    ((EnumBase) enumConstant).getValueDescription()))
                    .collect(Collectors.joining("\n")));
      }

      constraintsByClassAndField
          .getOrDefault(annotatedType.getParent().getName(), Map.of())
          .getOrDefault(annotatedType.getPropertyName(), List.of())
          .forEach(
              schemaConstraint ->
                  schema.setDescription(
                      schema.getDescription() + "\n\n" + schemaConstraint.getDescription()));
    } else if (annotatedType.getType() instanceof CollectionType) {
      log.debug(
          "  array: {} {}", annotatedType.getParent().getName(), annotatedType.getPropertyName());

      boolean clearSchemaConstraints =
          modelClassesBySimpleName
                  .get(annotatedType.getParent().getName())
                  .getAnnotationsByType(ClearSchemaConstraints.class)
                  .length
              > 0;
      if (clearSchemaConstraints) {
        schema.getItems().pattern(null);
        schema.getItems().minLength(null);
      }

      Arrays.stream(annotatedType.getCtxAnnotations())
          .filter(annotation -> annotation instanceof ArraySchema)
          .map(annotation -> (ArraySchema) annotation)
          .forEach(
              arraySchemaAnnotation -> {
                if (isSetValue(arraySchemaAnnotation.minItems())) {
                  schema.setMinItems(arraySchemaAnnotation.minItems());
                }
                if (isSetValue(arraySchemaAnnotation.maxItems())) {
                  schema.setMaxItems(arraySchemaAnnotation.maxItems());
                }
              });
    } else {
      log.info("Object: {}", annotatedType.getType());

      if (schema.getProperties() != null) {
        boolean clearSchemaConstraints =
            getAnnotatedTypeClass(annotatedType)
                    .getAnnotationsByType(ClearSchemaConstraints.class)
                    .length
                > 0;
        if (clearSchemaConstraints) {
          schema.required(null);
        }
        boolean clearParentProperties =
            getAnnotatedTypeClass(annotatedType)
                    .getAnnotationsByType(ClearParentProperties.class)
                    .length
                > 0;
        if (clearParentProperties) {
          originalSchemasByClassAndField
              .getOrDefault(
                  getAnnotatedTypeClass(annotatedType).getSuperclass().getSimpleName(), Map.of())
              .keySet()
              .forEach(parentPropertyName -> schema.getProperties().remove(parentPropertyName));
        }
        new TreeSet<>(schema.getProperties().keySet())
            .forEach(
                propertyName -> {
                  Schema<?> propertySchema = schema.getProperties().get(propertyName);
                  if (clearSchemaConstraints) {
                    propertySchema.pattern(null);
                    propertySchema.minItems(null);
                    propertySchema.minLength(null);
                    propertySchema.minimum(null);
                    propertySchema.maximum(null);
                    propertySchema.exclusiveMinimum(null);
                    propertySchema.exclusiveMaximum(null);
                    propertySchema.exclusiveMinimumValue(null);
                    propertySchema.exclusiveMaximumValue(null);
                  }
                  if (propertySchema.get$ref() != null) {
                    Schema<?> originalPropertySchema =
                        originalSchemasByClassAndField
                            .getOrDefault(
                                getAnnotatedTypeClass(annotatedType).getSimpleName(), Map.of())
                            .get(propertyName);
                    if (originalPropertySchema != null) {
                      schema
                          .getProperties()
                          .put(
                              propertyName,
                              new ComposedSchema()
                                  .allOf(List.of(new Schema<>().$ref(propertySchema.get$ref())))
                                  .description(originalPropertySchema.getDescription()));
                    }
                  } else if (propertySchema instanceof DateSchema) {
                    schema
                        .getProperties()
                        .put(
                            propertyName,
                            new Schema<String>()
                                .type("string")
                                .format("date")
                                .name(propertyName)
                                .description(propertySchema.getDescription())
                                .example(DATE_FORMAT.format((Date) propertySchema.getExample())));
                  }
                });
      }
    }
    return schema;
  }

  private static Field getJavaFieldWithPropertyName(Class<?> aClass, String propertyName) {
    if (aClass == null || java.lang.Object.class.equals(aClass)) {
      return null;
    }
    return Arrays.stream(aClass.getDeclaredFields())
        .filter(field -> javaFieldHasPropertyName(field, propertyName))
        .findFirst()
        .orElse(getJavaFieldWithPropertyName(aClass.getSuperclass(), propertyName));
  }

  private static boolean javaFieldHasPropertyName(Field field, String propertyName) {
    return field.getName().equals(propertyName)
        || Arrays.stream(
                field.getAnnotationsByType(io.swagger.v3.oas.annotations.media.Schema.class))
            .anyMatch(schemaAnnotation -> schemaAnnotation.name().equals(propertyName));
  }

  @SneakyThrows
  private static Class<?> getAnnotatedTypeClass(AnnotatedType annotatedType) {
    return Class.forName(annotatedType.getType().getTypeName());
  }

  private static boolean isSetValue(Integer value) {
    return value != null && value != Integer.MIN_VALUE && value != Integer.MAX_VALUE;
  }
}
