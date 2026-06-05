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
The person to be notified when a shipment arrives at its destination.

**Condition:** Mandatory for To Order HBLs (HouseBL `isToOrder=true`)
""")
@Getter
@Setter
@RequiredArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
public class NotifyPartyHBL
    extends org.dcsa.standards.specifications.standards.ebl.v3.model_si.NotifyPartyHBL {

  @Schema private AddressHBL address;
}
