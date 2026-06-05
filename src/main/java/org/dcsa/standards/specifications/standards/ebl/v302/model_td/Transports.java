package org.dcsa.standards.specifications.standards.ebl.v302.model_td;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Schema(
    description =
"""
This object consolidates information related to dates, origin and destination points, pre- and on-carriage mode of transport and vessel details.
""")
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Transports extends org.dcsa.standards.specifications.standards.ebl.v3.model_td.Transports {}
