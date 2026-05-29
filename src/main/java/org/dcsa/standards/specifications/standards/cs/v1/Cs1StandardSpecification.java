package org.dcsa.standards.specifications.standards.cs.v1;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.dcsa.standards.specifications.dataoverview.AttributesHierarchicalSheet;
import org.dcsa.standards.specifications.dataoverview.AttributesNormalizedSheet;
import org.dcsa.standards.specifications.dataoverview.DataOverviewSheet;
import org.dcsa.standards.specifications.dataoverview.LegendMetadata;
import org.dcsa.standards.specifications.generator.StandardSpecification;
import org.dcsa.standards.specifications.standards.cs.v100.messages.DetailedError;
import org.dcsa.standards.specifications.standards.cs.v100.messages.ErrorResponse;
import org.dcsa.standards.specifications.standards.cs.v100.model.*;

/**
 * CS 1.x standard specification base class created for maintaining and exporting a new-style IM and
 * DO.
 */
public abstract class Cs1StandardSpecification extends StandardSpecification {

  protected final String modulePrefix;

  public Cs1StandardSpecification(String moduleName, String modulePrefix, String versionNumber) {
    super("Commercial Schedules - " + moduleName, versionNumber, "cs", "cs-" + modulePrefix);
    this.modulePrefix = modulePrefix;
  }

  @Override
  protected LegendMetadata getLegendMetadata() {
    return new LegendMetadata(standardName, standardVersion, "", "", 2);
  }

  @Override
  protected Stream<Class<?>> modelClassesStream() {
    return Stream.of(
        Address.class,
        Barge.class,
        BargeTransport.class,
        CutOffTime.class,
        DetailedError.class,
        ErrorResponse.class,
        Facility.class,
        Leg.class,
        Location.class,
        OtherTransport.class,
        PlaceOfArrival.class,
        PlaceOfDeparture.class,
        PlaceOfDelivery.class,
        PlaceOfReceipt.class,
        PointToPoint.class,
        PortSchedule.class,
        PortScheduleLocation.class,
        Schedule.class,
        ServicePartner.class,
        ServicePartnerSchedule.class,
        ServiceSchedule.class,
        Timestamp.class,
        TransportCall.class,
        TransportCallLocation.class,
        Vessel.class,
        VesselSchedule.class,
        VesselTransport.class);
  }

  @Override
  protected Map<Class<? extends DataOverviewSheet>, List<List<String>>>
      getOldDataValuesBySheetClass() {
    return Map.ofEntries(
            Map.entry(AttributesHierarchicalSheet.class, "attributes-hierarchical"),
            Map.entry(AttributesNormalizedSheet.class, "attributes-normalized"))
        .entrySet()
        .stream()
        .collect(Collectors.toMap(Map.Entry::getKey, _ -> List.of()));
  }

  @Override
  protected Map<Class<? extends DataOverviewSheet>, Map<String, String>>
      getChangedPrimaryKeyByOldPrimaryKeyBySheetClass() {
    return Map.ofEntries(
        Map.entry(AttributesHierarchicalSheet.class, Map.ofEntries()),
        Map.entry(AttributesNormalizedSheet.class, Map.ofEntries()));
  }

  @Override
  protected boolean swapOldAndNewInDataOverview() {
    return false;
  }
}
