package org.dcsa.standards.specifications.standards.ebl.v302.model_iss;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.dcsa.standards.specifications.standards.ebl.v3.model.NotifyParty;

@Schema(
    description =
        org.dcsa.standards.specifications.standards.ebl.v302.model_td.DocumentParties
            .CLASS_SCHEMA_DESCRIPTION)
@Getter
@Setter
@RequiredArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
public class DocumentParties
    extends org.dcsa.standards.specifications.standards.ebl.v302.model_td.DocumentParties {

  @Schema(
      description =
"""
List of up to 3 `Notify Parties`. The first item in the list is the **First Notify Party** (`N1`), the second item is the **Second Notify Party** (`N2`) and the last item is the **Other Notify Party** (`NI`).

**Conditions:** If provided:
  - mandatory for To Order BLs, `isToOrder=true`
  - the order of the items in this array **MUST** be preserved as by the provider of the API.
""")
  @ArraySchema(maxItems = 3)
  private List<NotifyParty> notifyParties;
}
