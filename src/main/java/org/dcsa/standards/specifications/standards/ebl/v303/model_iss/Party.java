package org.dcsa.standards.specifications.standards.ebl.v303.model_iss;

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
"""
Refers to a company or a legal entity.
""")
@Getter
@Setter
@RequiredArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
public class Party extends org.dcsa.standards.specifications.standards.ebl.v303.model_td.Party {

  @Schema private List<IdentifyingCode> identifyingCodes;
}
