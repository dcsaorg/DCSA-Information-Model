package org.dcsa.standards.specifications.standards.ebl.v301.model;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.dcsa.standards.specifications.standards.dt.v100.types.DisplayedAddressLine;

@Schema(
    description =
        org.dcsa.standards.specifications.standards.ebl.v3.model.Shipper
            .CLASS_SCHEMA_DESCRIPTION)
@Getter
@Setter
@RequiredArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
public class Shipper
    extends org.dcsa.standards.specifications.standards.ebl.v3.model.Shipper {

  @Schema(
      description =
"""
The address of the party to be displayed on the `Transport Document`. The displayed address may be used to match the address provided in the `Letter of Credit`.

**Conditions:** If provided:
  - the displayed address must be included in the `Transport Document`.
  - for physical BL (`isElectronic=false`), it is only allowed to provide max 2 lines of 35 characters. **Note:** Some carriers may choose to allow more lines, please consult the carrier's API documentation to check if this is the case.
  - for electronic BL (`isElectronic=true`), the limit is 6 lines of 35 characters
  - the order of the items in this array **MUST** be preserved as by the provider of the API.
""")
  @ArraySchema(maxItems = 6)
  private List<DisplayedAddressLine> displayedAddress;
}
