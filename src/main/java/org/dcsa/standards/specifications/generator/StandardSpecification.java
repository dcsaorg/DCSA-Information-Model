package org.dcsa.standards.specifications.generator;

import io.swagger.v3.core.converter.ModelConverters;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.headers.Header;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.media.Schema;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import io.swagger.v3.oas.models.parameters.Parameter;
import lombok.Getter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import org.dcsa.standards.specifications.constraints.SchemaConstraint;
import org.dcsa.standards.specifications.dataoverview.DataOverview;
import org.dcsa.standards.specifications.dataoverview.DataOverviewSheet;
import org.dcsa.standards.specifications.dataoverview.LegendMetadata;

@Slf4j
public abstract class StandardSpecification {

  public static final String COMPONENTS_HEADERS_REF_PATH = "#/components/headers/";
  public static final String API_VERSION_HEADER = "API-Version";
  public static final String API_VERSION_HEADER_REF =
      COMPONENTS_HEADERS_REF_PATH + API_VERSION_HEADER;
  public static final String NEXT_PAGE_CURSOR_HEADER = "Next-Page-Cursor";
  public static final String NEXT_PAGE_CURSOR_HEADER_REF =
      COMPONENTS_HEADERS_REF_PATH + NEXT_PAGE_CURSOR_HEADER;

  private static final String API_VERSION_DESCRIPTION =
      "Every API request and response must contain the `API-Version` header,"
          + " set to the full version of the implemented DCSA standard.";

  private final String standardVersion;
  private final String resourcesDirName;
  private final String generatedFilesPrefix;

  @Getter() protected final OpenAPI openAPI;

  private final Map<String, Map<String, List<SchemaConstraint>>> constraintsByClassAndField;

  protected StandardSpecification(
      String standardName,
      String standardVersion,
      String resourcesDirName,
      String generatedFilesPrefix) {
    this.standardVersion = standardVersion;
    this.resourcesDirName = resourcesDirName;
    this.generatedFilesPrefix = generatedFilesPrefix;
    openAPI =
        new OpenAPI()
            .openapi("3.0.3")
            .info(
                new Info()
                    .version(standardVersion)
                    .title("DCSA %s API".formatted(standardName))
                    .description(readResourceFile("openapi-root.md"))
                    .license(
                        new License()
                            .name("Apache 2.0")
                            .url("https://www.apache.org/licenses/LICENSE-2.0.html"))
                    .contact(
                        new io.swagger.v3.oas.models.info.Contact()
                            .name("Digital Container Shipping Association (DCSA)")
                            .url("https://dcsa.org")
                            .email("info@dcsa.org")))
            .components(new Components());

    openAPI
        .getComponents()
        .addHeaders(
            API_VERSION_HEADER,
            new Header()
                .description(API_VERSION_DESCRIPTION)
                .schema(new Schema<>().type("string").example(standardVersion)))
        .addHeaders(
            NEXT_PAGE_CURSOR_HEADER,
            new Header()
                .description(
                    "A cursor value pointing to the next page of results in a paginated GET response.")
                .schema(new Schema<>().type("string").example("ExampleNextPageCursor")));
    getCustomHeaders()
        .forEach(
            nameAndHeader ->
                openAPI
                    .getComponents()
                    .addHeaders(nameAndHeader.getKey(), nameAndHeader.getValue()));

    constraintsByClassAndField = new HashMap<>();
    modelClassesStream()
        .forEach(
            modelClass ->
                SpecificationToolkit.getClassConstraints(modelClass.getName())
                    .forEach(
                        schemaConstraint ->
                            schemaConstraint
                                .getTargetFields()
                                .forEach(
                                    targetField ->
                                        constraintsByClassAndField
                                            .computeIfAbsent(
                                                modelClass.getSimpleName(),
                                                ignoredClassName -> new HashMap<>())
                                            .computeIfAbsent(
                                                targetField.getName(),
                                                ignoredFieldName -> new ArrayList<>())
                                            .add(schemaConstraint))));

    ModelConverters.getInstance()
        .getConverters()
        .forEach(
            existingConverter -> {
              if (existingConverter instanceof ModelValidatorConverter) {
                log.info("Removing {}", existingConverter);
                ModelConverters.getInstance().removeConverter(existingConverter);
              }
            });
    ModelConverters.getInstance()
        .addConverter(
            new ModelValidatorConverter(constraintsByClassAndField, modelClassesStream()));
    modelClassesStream()
        .sorted(Comparator.comparing(Class::getSimpleName))
        .forEach(
            modelClass ->
                ModelConverters.getInstance()
                    .read(modelClass)
                    .forEach(openAPI.getComponents()::addSchemas));
  }

  protected abstract LegendMetadata getLegendMetadata();

  protected abstract Stream<Class<?>> modelClassesStream();

  protected abstract List<String> getRootTypeNames();

  protected abstract Map<Class<? extends DataOverviewSheet>, List<List<String>>>
      getOldDataValuesBySheetClass();

  protected abstract Map<Class<? extends DataOverviewSheet>, Map<String, String>>
      getChangedPrimaryKeyByOldPrimaryKeyBySheetClass();

  protected abstract QueryParametersFilterEndpoint getQueryParametersFilterEndpoint();

  protected abstract boolean swapOldAndNewInDataOverview();

  protected Stream<Map.Entry<String, Header>> getCustomHeaders() {
    return Stream.of();
  }

  protected String readResourceFile(String fileName) {
    return SpecificationToolkit.readResourceFile(
        "standards/%s/v%s/%s"
            .formatted(
                resourcesDirName,
                standardVersion.replaceAll("\\.", ""),
                fileName));
  }

  @SneakyThrows
  public void generateArtifacts() {
    String yamlContent = SpecificationToolkit.createYamlObjectMapper().writeValueAsString(openAPI);
    String fileNamePrefix = "%s-v%s".formatted(generatedFilesPrefix, standardVersion);
    String exportFileDir =
        "./generated-resources/standards/%s/v%s/"
            .formatted(resourcesDirName, standardVersion.replaceAll("\\.", ""));
    String yamlFilePath = exportFileDir + "%s-openapi.yaml".formatted(fileNamePrefix);
    Files.writeString(Paths.get(yamlFilePath), yamlContent);
    log.info("OpenAPI spec exported to {}", yamlFilePath);

    DataOverview dataOverview =
        new DataOverview(
            getLegendMetadata(),
            constraintsByClassAndField,
            SpecificationToolkit.parameterizeStringRawSchemaMap(
                openAPI.getComponents().getSchemas()),
            getRootTypeNames(),
            getQueryParametersFilterEndpoint().getQueryParameters(),
            getQueryParametersFilterEndpoint().getRequiredAndOptionalFilters(),
            getOldDataValuesBySheetClass(),
            getChangedPrimaryKeyByOldPrimaryKeyBySheetClass(),
            swapOldAndNewInDataOverview());
    dataOverview.exportToExcelFile(
        exportFileDir + "%s-data-overview.xlsx".formatted(fileNamePrefix));
    dataOverview.exportToCsvFiles(
        exportFileDir + "%s-data-overview-%s.csv".formatted(fileNamePrefix, "%s")); // pass the %s
  }

  protected Parameter getApiVersionHeaderParameter() {
    return new Parameter()
        .in("header")
        .name(API_VERSION_HEADER)
        .description(API_VERSION_DESCRIPTION)
        .required(false)
        .schema(new Schema<String>().type("string"))
        .example(standardVersion);
  }

  protected LinkedHashMap<String, Header> createRefHeadersMap(String... headerNames) {
    return Arrays.stream(headerNames)
        .map(
            headerName ->
                Map.entry(headerName, new Header().$ref(COMPONENTS_HEADERS_REF_PATH + headerName)))
        .collect(
            Collectors.toMap(
                Map.Entry::getKey, Map.Entry::getValue, (a, b) -> b, LinkedHashMap::new));
  }
}
