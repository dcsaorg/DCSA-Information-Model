package org.dcsa.standards.specifications.standards.ebl.v301.model_iss;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Schema(
    description =
        org.dcsa.standards.specifications.standards.ebl.v3.model_iss.Address
            .CLASS_SCHEMA_DESCRIPTION)
@Getter
@Setter
@RequiredArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
public class Address extends org.dcsa.standards.specifications.standards.ebl.v3.model_iss.Address {

  @Schema(description = "The post code.", example = "1047 HM", maxLength = 10)
  protected String postCode;
}
