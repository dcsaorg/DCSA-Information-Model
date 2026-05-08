package org.dcsa.standards.specifications.standards.booking.v204.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.dcsa.standards.specifications.standards.dt.v100.model.InnerPackaging;

import java.util.List;

@Schema(
    description =
        org.dcsa.standards.specifications.standards.booking.v2.model.DangerousGoods
            .CLASS_SCHEMA_DESCRIPTION)
@Getter
@Setter
@RequiredArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
public class DangerousGoods
    extends org.dcsa.standards.specifications.standards.booking.v2.model.DangerousGoods {

  @Schema(description = "A list of `Inner Packagings` contained inside this `Outer Packaging/Overpack`.")
  private List<InnerPackaging> innerPackagings;
}
