package org.dcsa.standards.specifications.standards.cs.v103.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Schema(
    description =
"""
A transportCall in the schedule. A transportCall can be either just a Port or further specified as a terminalCall.
""")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class TransportCall
    extends org.dcsa.standards.specifications.standards.cs.v102.model.TransportCall {}

