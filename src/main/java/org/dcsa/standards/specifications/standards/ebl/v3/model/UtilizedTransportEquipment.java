package org.dcsa.standards.specifications.standards.ebl.v3.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Schema(
    description =
"""
Specifies the container (`equipment`), the total `weight`, total `volume`, possible `ActiveReeferSettings`, `seals` and `references`
""")
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class UtilizedTransportEquipment extends org.dcsa.standards.specifications.standards.dt.v100.model.UtilizedTransportEquipment {}
