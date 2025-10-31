package org.dcsa.standards.specifications.standards.tnt.v300.types;

import org.dcsa.standards.specifications.generator.EnumBase;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@Schema(
    type = "string",
    example = "APPR",
    description = "Code used to denote the type of a shipment event")
@AllArgsConstructor
public enum ShipmentEventTypeCode implements EnumBase {
  AMCF("Amendment Confirmed"),
  AMCN("Amendment Cancelled"),
  AMDC("Amendment Declined"),
  AMPR("Amendment In Progress"),
  AMRE("Amendment Received"),
  APPR("Approved"),
  CACF("Cancellation Confirmed"),
  CADC("Cancellation Declined"),
  CARE("Cancellation Received"),
  CMPL("Completed"),
  CONF("Confirmed"),
  DECL("Declined"),
  DRFT("Drafted"),
  HOLD("On Hold"),
  ISSU("Issued"),
  PENA("Pending Approval"),
  PENM("Pending Amendment"),
  PENU("Pending Update"),
  PSAM("Pending Surrender for Amendment"),
  PSDL("Pending Surrender for Delivery"),
  RECE("Received"),
  REJE("Rejected"),
  RELS("Released"),
  REQS("Requested"),
  SUAM("Surrendered for Amendment"),
  SUBM("Submitted"),
  SUDL("Surrendered for Delivery"),
  SURR("Surrendered"),
  UPCF("Update Confirmed"),
  UPCN("Update Cancelled"),
  UPDC("Update Declined"),
  UPDT("Update In Progress"),
  UPRE("Update Received"),
  VOID("Voided");

  private final String valueDescription;
}
