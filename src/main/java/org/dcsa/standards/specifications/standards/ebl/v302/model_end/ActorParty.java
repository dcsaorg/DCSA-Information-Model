package org.dcsa.standards.specifications.standards.ebl.v302.model_end;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.dcsa.standards.specifications.standards.ebl.v3.model_end.RepresentedActorParty;

@Schema(
    description =
        org.dcsa.standards.specifications.standards.ebl.v3.model_end.ActorParty
            .CLASS_SCHEMA_DESCRIPTION)
@Getter
@Setter
@RequiredArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
public class ActorParty
    extends org.dcsa.standards.specifications.standards.ebl.v3.model_end.ActorParty {

  @Schema private RepresentedActorParty representedParty;
}
