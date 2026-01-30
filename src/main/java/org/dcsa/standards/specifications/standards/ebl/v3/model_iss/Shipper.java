package org.dcsa.standards.specifications.standards.ebl.v3.model_iss;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.dcsa.standards.specifications.standards.ebl.v3.model.IdentifyingCode;

@Schema(
    description =
        org.dcsa.standards.specifications.standards.ebl.v3.model.Shipper.CLASS_SCHEMA_DESCRIPTION)
@Getter
@Setter
@RequiredArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
public class Shipper extends org.dcsa.standards.specifications.standards.ebl.v3.model.Shipper {

  @Schema private List<IdentifyingCode> identifyingCodes;
}
