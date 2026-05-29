# Copilot Instructions for DCSA Information Model

## Project Overview

This repository is maintained by the Digital Container Shipping Association (DCSA). Its primary purpose is defining API standards by building a Java object model and exporting OpenAPI YAML specifications and CSV/Excel data overviews from it.

### Primary Goal
Standards such as AN (Arrival Notice), PortCall, TNT (Track & Trace), and VGM (Verified Gross Mass) are built by defining the Java object model in `src/main/java/org/dcsa/standards/specifications/standards/` and generating the OpenAPI YAML and data overview exports from it.

### Secondary Goal
For older standards originally written manually as OpenAPI specifications (e.g., Booking, EBL, OVS, CS), the project reconstructs the Java object model to match the existing OpenAPI YAML as closely as possible, then uses that model to generate the CSV/Excel data overview.

### Additional Content
- **BPMN files** for the DCSA Industry Blueprint are maintained in `src/main/resources/ibp/`.
- **Public lists** officially maintained by DCSA are in `src/main/resources/lists/`.
- **API Design & Implementation Guidelines** are in `src/main/resources/api-dni-guidelines.md`.

## Technology Stack

- **Language**: Java 25
- **Build**: Maven
- **Key Libraries**: Swagger/OpenAPI annotations, Jackson (YAML + CSV), Apache POI (Excel), Lombok, Log4j
- **Testing**: JUnit 5 (via `maven-surefire-plugin`)

## Project Structure

```
src/main/java/org/dcsa/standards/specifications/
  generator/            # Core generation engine (StandardSpecification, ModelValidatorConverter, SpecificationToolkit)
  constraints/          # SchemaConstraint definitions
  dataoverview/         # Data overview (CSV/Excel) generation logic
  standards/            # Java model per standard (an/, booking/, cs/, ebl/, ovs/, portcall/, tnt/, vgm/)
    core/               # Shared model classes reused across standards (e.g., core/v101/model/)
    dt/                 # Domain type models shared across standards (e.g., dt/v100/model/)
src/main/resources/
  standards/            # OpenAPI YAML specs, changelogs, descriptions per standard version
  ibp/                  # BPMN Industry Blueprint diagrams
  lists/                # DCSA-maintained public lists
  api-dni-guidelines.md
generated-resources/    # Committed generated OpenAPI YAML + data overview output per standard
```

Each standard is versioned (e.g., `vgm/v100/`, `booking/v204/`) and contains model classes, endpoint definitions, types, and message definitions.

## Architecture Details

### Generation Pipeline (`generator/` package)

The generation is orchestrated by `StandardSpecification` (abstract base class):

1. **Constructor** initializes an `OpenAPI` object (version 3.0.3), sets info/license/contact, adds standard headers (`API-Version`, `Next-Page-Cursor`).
2. **`modelClassesStream()`** — abstract method returning a `Stream<Class<?>>` of all model classes for this version. Subclasses override this to list their models.
3. **`ModelValidatorConverter`** — a custom Swagger `ModelConverter` registered with `ModelConverters.getInstance()`. It reads Java model classes and enhances schemas with constraint information, handles `oneOf` discriminators, date formatting (`FormattedDateTime`), and `@ClearSchemaConstraints`.
4. **Schema generation** — iterates `modelClassesStream()` sorted by `getSimpleName()`, calls `ModelConverters.getInstance().read(modelClass)`, and adds results to `openAPI.getComponents().schemas`.
5. **`generateArtifacts()`** — serializes the OpenAPI object to YAML via `SpecificationToolkit.createYamlObjectMapper()` (Jackson YAML with `SchemaMixin` and `ParameterMixin`), writes to `generated-resources/standards/<std>/v<ver>/`, then creates `DataOverview` for Excel/CSV export.

### YAML Serialization

- `SpecificationToolkit.createYamlObjectMapper()` uses Jackson's `YAMLFactory` with custom mixins:
  - `SchemaMixin` — `@JsonIgnore` on internal schema fields to keep YAML clean.
  - `ParameterMixin` — proper serialization of OpenAPI `Parameter` objects.
- Output path: `./generated-resources/standards/<resourcesDirName>/v<versionNoPoints>/<prefix>-v<version>-openapi.yaml`

### CSV/Excel Data Overview Export

- `DataOverview` creates multiple sheets: `AttributesHierarchicalSheet`, `AttributesNormalizedSheet`, `QueryParametersSheet`, `QueryFiltersSheet`.
- `AttributesData` traverses the OpenAPI schema tree to build hierarchical and normalized attribute rows.
- Excel export uses Apache POI (`addToExcelWorkbook` with formatting and tables).
- CSV export uses Jackson CSV mapper (`exportToCsvFile`).
- Diff tracking: `getOldDataValuesBySheetClass()` returns previous version's CSV data (read from `generated-resources/`) so the data overview can mark added/removed/changed rows.

### Endpoint Definitions

- Classes like `GetVGMDeclarationsEndpoint`, `GetBookingEndpoint` extend `QueryParametersFilterEndpoint`.
- They define query parameters (as OpenAPI `Parameter` objects) and required/optional filter combinations.
- Used both in the specification constructor (to build OpenAPI paths) and in `DataOverview` (to generate query parameter/filter sheets).

## How @Schema and Related Annotations Work

### On Model Classes

```java
@Data
@Schema(description = "Description of the model type")
public class MyModel {
  @Schema(description = "Field description", maxLength = 50, example = "ABC", pattern = "^\\S+$", format = "date")
  private String myField;

  @Schema(description = "Reference to another type")
  private OtherModel otherModel;  // generates a $ref in OpenAPI
}
```

- `@Schema` on the class sets the type-level description.
- `@Schema` on fields sets description, example, maxLength, minLength, pattern, format, minimum, maximum, etc.
- Lombok `@Data`/`@Getter`/`@Setter` generates accessors that Swagger reads.
- `@ClearSchemaConstraints` on a subclass clears inherited constraints.
- Multiline descriptions use Java text blocks (`""" ... """`).

### Enum Types

- Enums annotated with `@Schema` become string enums in OpenAPI.
- Custom types like `FormattedDateTime` are handled specially by `ModelValidatorConverter` to produce `type: string, format: date-time`.

### Class-Level Constants for Descriptions

Old standards often define `public static final String CLASS_SCHEMA_DESCRIPTION = "..."` so patch subclasses can reference it:
```java
@Schema(description = org.dcsa.standards.specifications.standards.booking.v2.model.Booking.CLASS_SCHEMA_DESCRIPTION)
public class Booking extends org.dcsa.standards.specifications.standards.booking.v2.model.Booking { /* ... */ }
```

## Versioning & Patching

### New Standards (AN, VGM, PortCall, TNT) — From Scratch

Each version is a **standalone** `StandardSpecification` subclass:
- `VGM100StandardSpecification extends StandardSpecification` — defines all models, endpoints, paths from scratch.
- `VGM101StandardSpecification extends StandardSpecification` — also standalone (may share `core/` model classes but defines its own full model list).
- Models live in their own version package: `vgm/v100/model/`, `vgm/v101/model/`.

### Old Standards (Booking, EBL, OVS, CS) — Incremental Inheritance

A base specification class defines the full model list for the major version:
- `Booking2StandardSpecification extends StandardSpecification` — lists all v2.x model classes, defines endpoints/paths.
- `Booking200StandardSpecification extends Booking2StandardSpecification` — just calls `super("2.0.0", "")`.

**Patch versions** use `modelClassesStreamWithReplacementClasses()`:
- `Booking201StandardSpecification extends Booking2StandardSpecification`:
  ```java
  // super("2.0.1", "2.0.0");  // version, baselineVersion
  @Override
  protected Stream<Class<?>> modelClassesStream() {
    return modelClassesStreamWithReplacementClasses(
        super.modelClassesStream(), Set.of(Booking.class, OtherDocumentParty.class));
  }
  ```
- This replaces parent model classes (by `getSimpleName()` match) with newer versions.

**Patch model classes** extend the previous version's model:
```
booking/v2/model/Booking.java          (base, all v2.0.0 fields)
booking/v201/model/Booking.java        extends v2.model.Booking (adds v2.0.1 fields)
booking/v202/model/Booking.java        extends v201.model.Booking (adds v2.0.2 fields)
```
Each patch model only declares NEW fields added in that version; inherited fields come from the parent.

## Testing

### Test Structure

Tests are in `src/test/java/org/dcsa/standards/specifications/standards/`. The shared helper is `StandardSpecificationTestToolkit` (a utility enum with static methods).

### New Standard Tests (e.g., `VGM1StandardSpecificationTest`)

```java
void buildAndCheckV100() {
  VGM100StandardSpecification spec = new VGM100StandardSpecification();
  spec.generateArtifacts();  // writes YAML + Excel/CSV to generated-resources/
  // Verify generated YAML is self-consistent (round-trip parse and compare schemas)
  StandardSpecificationTestToolkit.verifyTypeExport(
      VGMDeclaration.class.getSimpleName(), yamlFilePath, spec);
  // Hash assertion ensures no unexpected changes
  Assertions.assertEquals("D805AF29...", StandardSpecificationTestToolkit.getFileHash(yamlFilePath));
}
```

- **Generates artifacts first**, then verifies.
- Compares against its OWN generated YAML (round-trip consistency check).
- Uses SHA-256 hash assertions to detect unintended changes — update the hash when intentional changes are made.

### Old Standard Tests (e.g., `Booking2StandardSpecificationsTest`)

```java
private static void buildAndCheckV200() {
  Booking200StandardSpecification spec = new Booking200StandardSpecification();
  // Verify Java model matches the MANUALLY-WRITTEN source YAML
  StandardSpecificationTestToolkit.verifyTypeExport(
      Booking.class.getSimpleName(),
      "./src/main/resources/standards/booking/v200/booking_v2.0.0.yaml",  // source of truth
      spec);
  spec.generateArtifacts();  // generates data overview only (YAML already exists)
}
```

- **Verifies BEFORE generating** — compares generated schemas against the hand-written source YAML in `src/main/resources/standards/`.
- The source YAML is the ground truth; the Java model must match it.
- Tests run versions in order (v200, v201, v202...) so diff data is available for each subsequent version.
- No hash assertions (the source YAML is external).

### `StandardSpecificationTestToolkit.verifyTypeExport()`

Recursively compares schemas between a reference YAML and the in-memory `StandardSpecification`:
- Parses reference YAML with `OpenAPIV3Parser`.
- For each type, compares: property names, required list, description, type, format, pattern, minLength, maxLength, minItems, maxItems, minimum, maximum, exclusiveMinimum, exclusiveMaximum, examples, enum values, oneOf/$ref, discriminator.
- Uses soft assertions (logs `WRONG VALUE` warnings) but `FAIL_ON_FIRST_WRONG_VALUE` is effectively always true (the condition `System.currentTimeMillis() > 0` is always true), so tests fail immediately on mismatch.

## Coding Conventions

- Use **Lombok** annotations (`@Data`, `@Builder`, `@Value`, `@Getter`, `@Setter`, `@RequiredArgsConstructor`, `@EqualsAndHashCode(callSuper = true)`, `@ToString`) to reduce boilerplate in model classes.
- OpenAPI metadata is declared via **Swagger annotations** (`@Schema`, `@Operation`, etc.) from `io.swagger.v3.oas.annotations.media`.
- Standard versions are organized in sub-packages named by version (e.g., `v100`, `v200`, `v201`).
- Model classes use multiline descriptions via Java text blocks.
- Shared/reusable models live in `standards/core/` or `standards/dt/` packages.
- Tests validate that the generated OpenAPI specification matches expectations.

## Running Tests and Interpreting Failures

### How to Run Tests

```powershell
# Run all tests
cd C:\Users\GeorgeJuan\sys\dev\DCSA-Information-Model
mvn test 2>&1 | Select-Object -Last 200

# Run a single test class
mvn test -pl . -Dtest="VGM1StandardSpecificationTest" 2>&1 | Select-Object -Last 150

# Run a single test class (old standard)
mvn test -pl . -Dtest="Booking2StandardSpecificationsTest" 2>&1 | Select-Object -Last 150
```

**Important**: Always use `Select-Object -Last 200` (or more) because:
- Maven prints compilation output, then INFO-level log lines for each type comparison, then the failure.
- A single failure output can be **20+ lines** because descriptions/values can be multi-line (text blocks).
- The failure message format is shown below — the `expected` and `actual` values can each span many lines.

### Test Failure Output Format

When a schema mismatch is found, the output contains TWO parts:

**Part 1 — Log warning (from `log.warn`):**
```
WARN  [...] -
WRONG VALUE:
================
description
<<<<<<<< expected <<<<<<<<
The full expected value which may
span multiple lines if it's a
text block description
>>>>>>>>  actual  >>>>>>>>
The full actual value which may
also span multiple lines
================
(escaped view)
expected: The full expected value...
actual:   The full actual value...
```

**Part 2 — JUnit assertion failure (immediately after):**
```
org.opentest4j.AssertionFailedError: Wrong value for: description ==> expected: <expected value> but was: <actual value>
```

The JUnit line truncates long values, so the **log warning block** is the authoritative source for understanding the mismatch. The `(escaped view)` section shows non-printable characters as `\xHH`.

### Hash Mismatch Failures (New Standards Only)

```
org.opentest4j.AssertionFailedError: expected: <OLD_HASH> but was: <NEW_HASH>
```

This means the generated YAML changed. The new hash is in the `but was:` part — copy it into the test to update.

### Key Tips for Capturing Output

- Use `-Last 200` minimum; use `-Last 300` if testing old standards with many versions (they run sequentially and a failure midway leaves lots of preceding INFO lines).
- The `INFO` lines like `Comparing object: Booking` and `Comparing Booking carrierExportVoyageNumber` tell you the traversal path leading to the failure — these precede the WRONG VALUE block.
- If output is still cut off, read the surefire report directly: `target/surefire-reports/<fully.qualified.TestClassName>.txt`

## When Making Changes

- **When encountering errors or unfamiliar patterns**, look at how similar situations are handled in other standards in this project rather than trying generic approaches from scratch. The codebase has many non-obvious conventions (e.g., how `oneOf` discriminators are set up, how `FormattedDateTime` works, how patch models inherit descriptions) that are best learned by example from existing working code.
- When adding or modifying a standard, define the Java model first; the OpenAPI YAML and data overview are derived from it.
- Run `mvn clean install` to regenerate specifications and run tests.
- Ensure generated output in `generated-resources/` is kept in sync with model changes.
- Follow existing package/version naming conventions when adding new standard versions.
- When modifying new standards: update the SHA-256 hash in the test after verifying the change is correct. Note: SHA-256 hash assertions are only added to **released** standards to prevent accidental post-release changes. Standards still in development do **not** use hash assertions in their tests.
- When modifying old standards: ensure the Java model still matches the source YAML in `src/main/resources/standards/`.
- For patch versions of old standards: create a new model class extending the previous version's model, add only new fields, and create a specification class using `modelClassesStreamWithReplacementClasses()`.

