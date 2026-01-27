package org.dcsa.standards.specifications.standards.ebl.v302.model_iss;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.dcsa.standards.specifications.standards.ebl.v302.model.IdentifyingCode;

import java.util.List;

@Schema(
    description =
        org.dcsa.standards.specifications.standards.ebl.v302.model.NotifyParty
            .CLASS_SCHEMA_DESCRIPTION)
@Getter
@Setter
@RequiredArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
public class NotifyParty
    extends org.dcsa.standards.specifications.standards.ebl.v302.model.NotifyParty {

  @Schema private List<IdentifyingCode> identifyingCodes;
}
