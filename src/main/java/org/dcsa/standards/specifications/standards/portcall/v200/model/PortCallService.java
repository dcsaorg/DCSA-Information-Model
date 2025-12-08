package org.dcsa.standards.specifications.standards.portcall.v200.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.dcsa.standards.specifications.standards.core.v101.model.Location;
import org.dcsa.standards.specifications.standards.core.v101.types.UniversallyUniqueID;

@Data
@Schema(description = "Port call service information")
public class PortCallService {

  @Schema(description = "Universal unique identifier of the port call service")
  private UniversallyUniqueID portCallServiceID;

  @Schema(
      maxLength = 50,
      example = "BERTH",
      description =
"""
The type of the port call service, with the value from one of the service lists below.

These port call services are negotiable through an "ERP" pattern including an "A":
- `BERTH` (Berth)
- `CARGO_OPERATIONS` (Cargo operations)
- `PILOTAGE` (Pilotage)
- `TOWAGE` (Towage)
- `MOORING` (Mooring)
- `BUNKERING` (Bunkering)
- `PILOT_BOARDING_PLACE` (Pilot Boarding Place)
- `ANCHORAGE` (Anchorage)
- `SLUDGE` (Sludge)

These port call services only an "A" timestamp without an "ERP" pattern:
- `SEA_PASSAGE` (Sea Passage)
- `ALL_FAST` (All Fast)
- `GANGWAY_DOWN_AND_SECURE` (Gangway down and secure)
- `VESSEL_READY_FOR_CARGO_OPERATIONS` (Vessel Ready for cargo operations)
- `VESSEL_READY_TO_SAIL` (Vessel Ready to sail)
- `DISCHARGE_CARGO_OPERATIONS` (Discharge cargo operations)
- `LOADING_CARGO_OPERATIONS` (Loading cargo operations)
- `LASHING` (Lashing)
- `SAFETY` (Safety)
- `ANCHORAGE_OPERATIONS` (Anchorage Operations)
- `SHORE_POWER` (ShorePower)

This port call service has neither an "A" timestamp nor an "ERP" pattern:
- `MOVES` (Moves)
""")
  private String portCallServiceTypeCode;

  @Schema(
      maxLength = 4,
      example = "STRT",
      description =
"""
The code to identify the type of event that is related to the port call service:
- `STRT` (Started)
- `CMPL` (Completed)
- `ARRI` (Arrived)
- `DEPA` (Departed)

Services with these `portCallServiceTypeCode` values only have `portCallServiceEventTypeCode` the values `STRT` or `CMPL`:
- `CARGO_OPERATIONS` (Cargo operations)
- `PILOTAGE` (Pilotage)
- `TOWAGE` (Towage)
- `MOORING` (Mooring)
- `BUNKERING` (Bunkering)
- `ANCHORAGE` (Anchorage)
- `SLUDGE` (Sludge)
- `SEA_PASSAGE` (Sea Passage)
- `DISCHARGE_CARGO_OPERATIONS` (Discharge cargo operations)
- `LOADING_CARGO_OPERATIONS` (Loading cargo operations)
- `LASHING` (Lashing)
- `ANCHORAGE_OPERATIONS` (Anchorage Operations)
- `SHORE_POWER` (ShorePower)

Services with these `portCallServiceTypeCode` values only have the `portCallServiceEventTypeCode` value `ARRI`:
- `PILOT_BOARDING_PLACE` (Pilot Boarding Place)
- `ALL_FAST` (All Fast)
- `GANGWAY_DOWN_AND_SECURE` (Gangway down and secure)
- `VESSEL_READY_FOR_CARGO_OPERATIONS` (Vessel Ready for cargo operations)
- `VESSEL_READY_TO_SAIL` (Vessel Ready to sail)
- `MOVES` (Moves)

Services with  `portCallServiceTypeCode='SAFETY'` (Safety) only have the `portCallServiceEventTypeCode` value `DEPA`.

Services with `portCallServiceTypeCode='BERTH'` (Berth) only have the `portCallServiceEventTypeCode` values `ARRI` or `DEPA`.
""")
  private String portCallServiceEventTypeCode;

  @Schema(
      maxLength = 4,
      example = "ALGS",
      description =
"""
The general direction of the vessel during this port call service:
- `INBD` (Inbound)
- `ALGS` (Alongside)
- `SHIF` (Shifting)
- `OUTB` (Outbound)

The direction `INBD` should be used with one of these `portCallServiceTypeCode` values:
- `BERTH` (Berth)
- `PILOTAGE` (Pilotage)
- `TOWAGE` (Towage)
- `MOORING` (Mooring)
- `PILOT_BOARDING_PLACE` (Pilot Boarding Place)
- `SEA_PASSAGE` (Sea Passage)

The direction `ALGS` should be used with one of these `portCallServiceTypeCode` values:
- `BERTH` (Berth)
- `BUNKERING` (Bunkering)
- `CARGO_OPERATIONS` (Cargo operations)
- `ALL_FAST` (All Fast)
- `GANGWAY_DOWN_AND_SECURE` (Gangway down and secure)
- `VESSEL_READY_FOR_CARGO_OPERATIONS` (Vessel Ready for cargo operations)
- `VESSEL_READY_TO_SAIL` (Vessel Ready to sail)
- `DISCHARGE_CARGO_OPERATIONS` (Discharge cargo operations)
- `LOADING_CARGO_OPERATIONS` (Loading cargo operations)
- `LASHING` (Lashing)
- `SAFETY` (Safety)
- `SHORE_POWER` (ShorePower)

The direction `SHIF` should be used with one of these `portCallServiceTypeCode` values:
- `PILOTAGE` (Pilotage)
- `TOWAGE` (Towage)
- `MOORING` (Mooring)

The direction `OUTB` should be used with one of these `portCallServiceTypeCode` values:
- `BERTH` (Berth)
- `PILOTAGE` (Pilotage)
- `TOWAGE` (Towage)
- `MOORING` (Mooring)
- `SEA_PASSAGE` (Sea Passage)

The `portCallPhaseTypeCode` is not applicable for these `portCallServiceTypeCode` values:
- `ANCHORAGE` (Anchorage)
- `SLUDGE` (Sludge)
- `ANCHORAGE_OPERATIONS` (Anchorage Operations)
- `MOVES` (Moves)
""")
  private String portCallPhaseTypeCode;

  @Schema(
      maxLength = 4,
      example = "BRTH",
      description =
"""
Code identifying a specific type of facility involved in a port call service:
- `PBPL` (Pilot boarding place)
- `BRTH` (Berth)
- `ANCH` (Anchorage Location)

The `PBPL` facility type is relevant for port call services with the following `portCallServiceTypeCode`:
- `PILOTAGE` (Pilotage)
- `TOWAGE` (Towage)
- `PILOT_BOARDING_PLACE` (Pilot Boarding Place)

The `BRTH` facility type is relevant for port call services with the following `portCallServiceTypeCode`:
- `BERTH` (Berth)
- `CARGO_OPERATIONS` (Cargo operations)
- `PILOTAGE` (Pilotage)
- `TOWAGE` (Towage)
- `MOORING` (Mooring)
- `BUNKERING` (Bunkering)
- `SLUDGE` (Sludge)
- `ALL_FAST` (All Fast)
- `GANGWAY_DOWN_AND_SECURE` (Gangway down and secure)
- `VESSEL_READY_FOR_CARGO_OPERATIONS` (Vessel Ready for cargo operations)
- `VESSEL_READY_TO_SAIL` (Vessel Ready to sail)
- `DISCHARGE_CARGO_OPERATIONS` (Discharge cargo operations)
- `LOADING_CARGO_OPERATIONS` (Loading cargo operations)
- `LASHING` (Lashing)
- `SAFETY` (Safety)
- `ANCHORAGE_OPERATIONS` (Anchorage Operations)
- `SHORE_POWER` (ShorePower)

The `ANCH` facility type is relevant for port call services with the following `portCallServiceTypeCode`:
- `ANCHORAGE` (Anchorage)
- `ANCHORAGE_OPERATIONS` (Anchorage Operations)
""")
  private String facilityTypeCode;

  @Schema(description = "The location at which the port call service is provided")
  private Location serviceLocation;

  @Schema(
      example = "false",
      description =
          "Flag indicating that this port call service is canceled by the service provider.")
  private Boolean isCanceled;

  @Schema(
      example = "false",
      description =
          "Flag indicating that this port call service is declined by the service consumer.")
  private Boolean isDeclined;
}
