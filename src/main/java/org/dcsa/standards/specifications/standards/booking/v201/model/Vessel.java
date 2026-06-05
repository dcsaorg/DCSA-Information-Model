package org.dcsa.standards.specifications.standards.booking.v201.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Schema(
    description =
"""
Vessels related to this booking request.

**Condition:** Mandatory if `carrierExportVoyageNumber` is provided and `carrierServiceCode` or `carrierServiceName` are blank. If `routingReference` is provided - this object MUST not be provided.
""")
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Vessel extends org.dcsa.standards.specifications.standards.booking.v2.model.Vessel {}
