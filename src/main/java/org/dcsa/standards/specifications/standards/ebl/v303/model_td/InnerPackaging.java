package org.dcsa.standards.specifications.standards.ebl.v303.model_td;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Schema(
    description =
"""
Object for `Inner Packaging` specification
""")
@Getter
@Setter
@RequiredArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
public class InnerPackaging
    extends org.dcsa.standards.specifications.standards.ebl.v3.model.InnerPackaging {

  @Schema(description = "A list of `Inner Packagings` contained inside this `Inner Packaging`.")
  private List<InnerPackaging> innerPackagings;
}
