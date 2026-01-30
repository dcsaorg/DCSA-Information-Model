package org.dcsa.standards.specifications.standards.ebl.v302.model_si;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.dcsa.standards.specifications.standards.ebl.v3.model.OnBehalfOfShipper;
import org.dcsa.standards.specifications.standards.ebl.v3.model_td.OnBehalfOfConsignee;

@Schema(
    description =
        org.dcsa.standards.specifications.standards.ebl.v301.model_si
            .DocumentPartiesShippingInstructions.CLASS_SCHEMA_DESCRIPTION)
@Getter
@Setter
@RequiredArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
public class DocumentPartiesShippingInstructions
    extends org.dcsa.standards.specifications.standards.ebl.v301.model_si
        .DocumentPartiesShippingInstructions {

  @Schema private OnBehalfOfShipper onBehalfOfShipper;

  @Schema private OnBehalfOfConsignee onBehalfOfConsignee;
}
