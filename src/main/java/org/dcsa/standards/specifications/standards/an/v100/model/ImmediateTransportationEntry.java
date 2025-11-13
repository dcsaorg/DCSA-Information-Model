package org.dcsa.standards.specifications.standards.an.v100.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.dcsa.standards.specifications.standards.core.v101.types.FormattedDate;

@Data
@Schema(
    description =
"""
An Immediate Transportation (IT) entry is a U.S. Customs and Border Protection (CBP) mechanism
that allows imported cargo to be moved from the first port of arrival in the U.S.
to another port or bonded facility without being physically cleared by CBP at the first port.
""")
public class ImmediateTransportationEntry {

  @Schema(
      name = "FIRMSCode",
      maxLength = 4,
      example = "B456",
      description =
"""
The Facilities Information and Resources Management System (FIRMS) code is a unique four-digit alpha-numeric identifier
assigned by U.S. Customs and Border Protection (CBP) to facilities in the U.S. where imported goods are held or processed,
such as Container Freight Stations (CFS), bonded warehouses, intermodal rail ramps or temporary storage facilities near ports.
It tells CBP and freight systems where the cargo is physically located.
""")
  private String firmsCode;

  @Schema(
      name = "ITNumber",
      maxLength = 100,
      example = "V4914303071",
      description =
"""
The reference number of the bonded movement under a U.S. Customs and Border Protection (CBP) Immediate Transportation (IT) entry,
often assigned by the carrier or customs broker. It allows goods to be moved under bond from the Port of Discharge
to another inland port or bonded facility without paying duties at the first port. The IT number is used
for tracking the movement and verifying arrival at the inland port or bonded facility.
""")
  private String itNumber;

  @Schema(
      name = "ITDate",
      description =
"""
The date the in-bond movement began, i.e., when the Immediate Transportation (IT) entry was filed and accepted
by U.S. Customs and Border Protection (CBP).
""")
  private FormattedDate itDate;
}
