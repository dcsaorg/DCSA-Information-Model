package org.dcsa.standards.specifications.standards.cs.v102.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Schema(
    description =
        "The timetable of departure and arrival times for each `TransportCall` on a Service of the vessel in question.")
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class VesselSchedule
    extends org.dcsa.standards.specifications.standards.cs.v100.model.VesselSchedule {}
