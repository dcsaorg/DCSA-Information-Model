package org.dcsa.standards.specifications.standards.vgm.v100.types;

import org.dcsa.standards.specifications.generator.EnumBase;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@Schema(
    type = "string",
    example = "SI",
    description =
"""
Code qualifying a reference.

In addition to the codes listed here, any other code as defined in
[UN/EDIFACT 16A 1153](https://unece.org/fileadmin/DAM/trade/untdid/d16a/tred/tred1153.htm)
or a subsequent revision may also be used.
""")
@AllArgsConstructor
public enum OtherReferenceTypeCode implements EnumBase {
  AOW("Transportation Control Number (TCN)"),
  SI("Shipper's identifying number for shipment (SID)"),
  SQ("Equipment sequence number"),
  VOR("Transport equipment gross mass verification order reference");

  private final String valueDescription;
}
