package org.dcsa.standards.specifications.standards.tnt.v300.types;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.dcsa.standards.specifications.generator.EnumBase;

@Getter
@Schema(type = "string", example = "APPROVED", description = "The type of a shipment event")
@AllArgsConstructor
public enum ShipmentEventType implements EnumBase {
  AMENDMENT_CANCELLED("Amendment Cancelled"),
  AMENDMENT_CONFIRMED("Amendment Confirmed"),
  AMENDMENT_DECLINED("Amendment Declined"),
  AMENDMENT_IN_PROGRESS("Amendment In Progress"),
  AMENDMENT_RECEIVED("Amendment Received"),
  APPROVED("Approved"),
  CANCELLATION_CONFIRMED("Cancellation Confirmed"),
  CANCELLATION_DECLINED("Cancellation Declined"),
  CANCELLATION_RECEIVED("Cancellation Received"),
  CANCELLED("Cancelled"),
  COMPLETED("Completed"),
  CONFIRMED("Confirmed"),
  DECLINED("Declined"),
  DRAFTED("Drafted"),
  ISSUED("Issued"),
  ON_HOLD("On Hold"),
  PENDING_AMENDMENT("Pending Amendment"),
  PENDING_APPROVAL("Pending Approval"),
  PENDING_SURRENDER_FOR_AMENDMENT("Pending Surrender for Amendment"),
  PENDING_SURRENDER_FOR_DELIVERY("Pending Surrender for Delivery"),
  PENDING_UPDATE("Pending Update"),
  RECEIVED("Received"),
  REJECTED("Rejected"),
  RELEASED("Released"),
  REQUESTED("Requested"),
  SUBMITTED("Submitted"),
  SURRENDERED("Surrendered"),
  SURRENDERED_FOR_AMENDMENT("Surrendered for Amendment"),
  SURRENDERED_FOR_DELIVERY("Surrendered for Delivery"),
  UPDATE_CANCELLED("Update Cancelled"),
  UPDATE_CONFIRMED("Update Confirmed"),
  UPDATE_DECLINED("Update Declined"),
  UPDATE_IN_PROGRESS("Update In Progress"),
  UPDATE_RECEIVED("Update Received"),
  VOIDED("Voided");

  private final String valueDescription;
}
