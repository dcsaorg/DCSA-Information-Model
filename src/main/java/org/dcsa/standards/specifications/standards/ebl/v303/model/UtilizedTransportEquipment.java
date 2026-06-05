package org.dcsa.standards.specifications.standards.ebl.v303.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Schema(
    description =
"""
Specifies the container (`equipment`) information, possible `ActiveReeferSettings`, `seals`, `references`, and other related details.
""")
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class UtilizedTransportEquipment extends org.dcsa.standards.specifications.standards.ebl.v3.model.UtilizedTransportEquipment {}
