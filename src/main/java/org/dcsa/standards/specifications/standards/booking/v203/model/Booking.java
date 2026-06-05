package org.dcsa.standards.specifications.standards.booking.v203.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Schema(
    description =
"""
The Booking - it can be a Booking request or a Confirmed Booking, defined by the `bookingStatus` property.
""")
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Booking extends org.dcsa.standards.specifications.standards.booking.v202.model.Booking {}
