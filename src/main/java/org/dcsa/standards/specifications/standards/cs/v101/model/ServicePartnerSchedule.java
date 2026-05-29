package org.dcsa.standards.specifications.standards.cs.v101.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Schema(
    description =
        "The service code and voyage number as identified by the carriers that are partners in the service.")
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ServicePartnerSchedule
    extends org.dcsa.standards.specifications.standards.cs.v100.model.ServicePartnerSchedule {

  @Schema(
      description =
"""
Identifies the code list provider used for the `carrierCode`.
- `SMDG` (Ship Message Design Group)
- `NMFTA` (National Motor Freight Traffic Association)
""",
      allowableValues = {"SMDG", "NMFTA"},
      example = "NMFTA")
  private String carrierCodeListProvider;
}
