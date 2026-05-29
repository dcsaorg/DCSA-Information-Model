package org.dcsa.standards.specifications.standards.cs.v102.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Schema(
    description =
"""
Timestamp defined by a type (Arrival or Departure), a classifier (Planned, Estimated or Actual) and a date and time.
""")
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Timestamp
    extends org.dcsa.standards.specifications.standards.cs.v101.model.Timestamp {}
