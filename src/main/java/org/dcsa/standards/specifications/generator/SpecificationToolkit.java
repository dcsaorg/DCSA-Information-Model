package org.dcsa.standards.specifications.generator;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLGenerator;
import io.swagger.v3.oas.models.media.Schema;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

import org.dcsa.standards.specifications.constraints.SchemaConstraint;

import io.swagger.v3.oas.models.parameters.Parameter;

@SuppressWarnings({"unchecked", "rawtypes"})
public enum SpecificationToolkit {
  ; // no instances

  public static Map<String, Schema<?>> parameterizeStringRawSchemaMap(
      Map<String, Schema> rawSchemaMap) {
    TreeMap<String, Schema<?>> stringSchemaTreeMap = new TreeMap<>();
    if (rawSchemaMap != null) {
      rawSchemaMap.forEach(stringSchemaTreeMap::put);
    }
    return stringSchemaTreeMap;
  }

  public static List<Schema<?>> parameterizeRawSchemaList(List<Schema> rawSchemaList) {
    return (ArrayList) new ArrayList<>(rawSchemaList);
  }

  public static List<SchemaConstraint> getClassConstraints(String className) {
    try {
      return getClassConstraints(Class.forName(className));
    } catch (ClassNotFoundException e) {
      return Collections.EMPTY_LIST;
    }
  }

  public static List<SchemaConstraint> getClassConstraints(Class<?> classObject) {
    Method getConstraintsMethod;
    try {
      getConstraintsMethod = classObject.getMethod("getConstraints");
    } catch (NoSuchMethodException e) {
      return Collections.EMPTY_LIST;
    }
    Object invocationResult;
    try {
      invocationResult = getConstraintsMethod.invoke(null);
    } catch (IllegalAccessException | InvocationTargetException e) {
      throw new RuntimeException(e);
    }
    return (List<SchemaConstraint>) invocationResult;
  }

  public static String readLocalFile(String filePath) {
    try {
      return Files.readString(Paths.get(filePath));
    } catch (IOException e) {
      throw new RuntimeException("Failed to read local file: " + filePath, e);
    }
  }

  public static String readRemoteFile(@SuppressWarnings("SameParameterValue") String fileUrl) {
    try (HttpClient httpClient = HttpClient.newHttpClient()) {
      return httpClient
          .send(
              HttpRequest.newBuilder(URI.create(fileUrl)).build(),
              HttpResponse.BodyHandlers.ofString())
          .body();
    } catch (IOException | InterruptedException e) {
      throw new RuntimeException("Failed to read remote file: " + fileUrl, e);
    }
  }

  public static String readResourceFile(@SuppressWarnings("SameParameterValue") String filePath) {
    try {
      return Files.readString(
          Paths.get(
              Objects.requireNonNull(
                      SpecificationToolkit.class.getClassLoader().getResource(filePath))
                  .toURI()));
    } catch (IOException | URISyntaxException e) {
      throw new RuntimeException("Failed to read file from resources: " + filePath, e);
    }
  }

  public static String getComponentSchema$ref(Class<?> schemaClass) {
    return "#/components/schemas/%s".formatted(schemaClass.getSimpleName());
  }

  public static ObjectMapper createYamlObjectMapper() {
    return new ObjectMapper(
            YAMLFactory.builder()
                .enable(YAMLGenerator.Feature.LITERAL_BLOCK_STYLE)
                .configure(YAMLGenerator.Feature.SPLIT_LINES, false)
                .build())
        .findAndRegisterModules()
        .setSerializationInclusion(JsonInclude.Include.NON_NULL)
        .setDefaultPropertyInclusion(JsonInclude.Include.NON_DEFAULT)
        .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
        .addMixIn(Schema.class, SchemaMixin.class)
        .addMixIn(Parameter.class, ParameterMixin.class);
  }
}
