package org.dcsa.standards.specifications.standards.ovs.v301.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Schema(
    description =
"""
The timetable of the roundtrip sequence of ports being served by a (number of) vessel(s) on a specific Proforma (schedule). Synonyms are rotation, loop, or string. A Service is defined by rotation, transit times, weekdays of departure per port, and frequencies.
""")
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ServiceSchedule
    extends org.dcsa.standards.specifications.standards.ovs.v300.model.ServiceSchedule {}

