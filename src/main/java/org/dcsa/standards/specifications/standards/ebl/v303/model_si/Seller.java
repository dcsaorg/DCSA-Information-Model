package org.dcsa.standards.specifications.standards.ebl.v303.model_si;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.dcsa.standards.specifications.standards.core.v104.types.AddressLine;

@Schema(
    description =
        org.dcsa.standards.specifications.standards.ebl.v3.model_si.Seller.CLASS_SCHEMA_DESCRIPTION)
@Getter
@Setter
@RequiredArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
public class Seller extends org.dcsa.standards.specifications.standards.ebl.v3.model_si.Seller {

  @Schema(
      description =
          "Unstructured address lines, used as a fallback when structured address fields are unavailable.")
  @ArraySchema(maxItems = 5)
  protected List<AddressLine> addressLines;
}
