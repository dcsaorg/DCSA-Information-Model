package org.dcsa.standards.specifications.standards.ebl.v303.model_si;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Schema(
    description =
"""
An object for storing address related information. Either **Street number** or **PO Box** should be provided to meet **ICS2** filing requirements.
""")
@Getter
@Setter
@RequiredArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
public class AddressHBL
    extends org.dcsa.standards.specifications.standards.ebl.v302.model.Address {}
