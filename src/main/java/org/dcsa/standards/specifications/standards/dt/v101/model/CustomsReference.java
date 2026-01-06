package org.dcsa.standards.specifications.standards.dt.v101.model;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.Data;
import org.dcsa.standards.specifications.standards.dt.v101.types.CustomsReferenceValue;

@Schema(description = CustomsReference.CLASS_SCHEMA_DESCRIPTION)
@Data
public class CustomsReference {

  public static final String CLASS_SCHEMA_DESCRIPTION =
      "Reference associated with customs and/or excise purposes required by relevant authorities for the import, export, or transit of goods.\n\nExamples include:\n- UCR (Unique Consignment Reference)\n- ACID (Advance Cargo Info Declaration in Egypt)\n- ITN (Internal Transaction Number in the US)";

  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      description = "The reference type code as defined in the relevant customs jurisdiction.",
      example = "CUS",
      maxLength = 50,
      pattern = "^\\S(?:.*\\S)?$")
  private String type;

  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      description =
"""
The 2 characters for the country code using [ISO 3166-1 alpha-2](https://www.iso.org/obp/ui/#iso:pub:PUB500001:en)
""",
      example = "NL",
      minLength = 2,
      maxLength = 2,
      pattern = "^[A-Z]{2}$")
  private String countryCode;

  @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
  @ArraySchema(minItems = 1)
  private List<CustomsReferenceValue> values;
}
