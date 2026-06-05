package org.dcsa.standards.specifications.standards.booking.v202.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Schema(
    description =
"""
The date and time and location for the empty container pick-up.

**Condition:** Only applicable to merchant haulage service at origin (`Receipt type at origin = 'CY'`).

**Deprecated:** In case both provider and consumer are on API v2.0.2 (or later), please use `originEmptyContainerPickup` instead as it contains no conditions.
""")
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class EmptyContainerPickup extends org.dcsa.standards.specifications.standards.booking.v2.model.EmptyContainerPickup {}
