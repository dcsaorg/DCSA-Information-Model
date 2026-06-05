package org.dcsa.standards.specifications.standards.booking.v2.model;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.dcsa.standards.specifications.standards.ebl.v3.types.NationalCommodityCodeValue;

@Schema(
    description =
"""
The national commodity classification code linked to a country with a value.

An example could look like this:

| Type  | Country | Value |
|-------|:-------:|-------------|
|NCM|BR|['1515', '2106', '2507', '2512']|
""")
@Getter
@Setter
@RequiredArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
public class NationalCommodityCode
    extends org.dcsa.standards.specifications.standards.dt.v100.model.NationalCommodityCode {
  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      description = "A list of `national commodity codes` values.")
  @ArraySchema(minItems = 1)
  private List<NationalCommodityCodeValue> values;
}
