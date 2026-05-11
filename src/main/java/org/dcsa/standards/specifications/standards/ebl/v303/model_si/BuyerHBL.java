package org.dcsa.standards.specifications.standards.ebl.v303.model_si;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Schema(
    description =
        org.dcsa.standards.specifications.standards.ebl.v3.model_si.BuyerHBL
            .CLASS_SCHEMA_DESCRIPTION)
@Getter
@Setter
@RequiredArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
public class BuyerHBL extends org.dcsa.standards.specifications.standards.ebl.v3.model_si.BuyerHBL {

  @Schema private AddressHBL address;
}
