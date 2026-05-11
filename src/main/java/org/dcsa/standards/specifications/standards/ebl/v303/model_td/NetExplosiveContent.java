package org.dcsa.standards.specifications.standards.ebl.v303.model_td;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Schema(
    description =
        org.dcsa.standards.specifications.standards.ebl.v3.model.NetExplosiveContent
            .CLASS_SCHEMA_DESCRIPTION)
@Getter
@Setter
@RequiredArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
public class NetExplosiveContent
    extends org.dcsa.standards.specifications.standards.ebl.v3.model.NetExplosiveContent {

  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      description =
          "The total weight of the explosive substances, without the packaging's, casings, etc.",
      example = "2400",
      minimum = "0",
      exclusiveMinimum = true,
      format = "float")
  protected Double value;
}
