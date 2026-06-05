package org.dcsa.standards.specifications.standards.ebl.v3.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Schema(
    description =
"""
The specifications for a Reefer equipment.

**Condition:** Only applicable when `isNonOperatingReefer` is set to `false`
""")
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ActiveReeferSettings extends org.dcsa.standards.specifications.standards.dt.v100.model.ActiveReeferSettings {}
