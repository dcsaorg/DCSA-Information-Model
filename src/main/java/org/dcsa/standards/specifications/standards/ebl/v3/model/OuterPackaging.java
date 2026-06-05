package org.dcsa.standards.specifications.standards.ebl.v3.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Schema(
    description =
"""
Object for outer packaging/overpack specification. Examples of overpacks are a number of packages stacked on to a pallet and secured by strapping or placed in a protective outer packaging such as a box or crate to form one unit for the convenience of handling and stowage during transport.
""")
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class OuterPackaging extends org.dcsa.standards.specifications.standards.dt.v100.model.OuterPackaging {}
