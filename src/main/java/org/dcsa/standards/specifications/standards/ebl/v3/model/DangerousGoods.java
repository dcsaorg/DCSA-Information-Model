package org.dcsa.standards.specifications.standards.ebl.v3.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Schema(
    description =
"""
Specification for `Dangerous Goods`. It is mandatory to provide one of `UNNumber` or `NANumber`. Dangerous Goods is based on **IMDG Amendment Version 41-22**.
""")
@Getter
@Setter
@RequiredArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
public class DangerousGoods
    extends org.dcsa.standards.specifications.standards.dt.v100.model.DangerousGoods {

  @Schema protected GrossWeight grossWeight;

  @Schema protected NetWeight netWeight;

  @Schema protected NetExplosiveContent netExplosiveContent;

  @Schema protected NetVolume netVolume;
}
