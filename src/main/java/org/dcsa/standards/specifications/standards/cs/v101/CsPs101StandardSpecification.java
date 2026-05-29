package org.dcsa.standards.specifications.standards.cs.v101;

import java.util.Set;
import java.util.stream.Stream;
import org.dcsa.standards.specifications.standards.cs.v100.CsPs100StandardSpecification;
import org.dcsa.standards.specifications.standards.cs.v101.messages.ErrorResponse;
import org.dcsa.standards.specifications.standards.cs.v101.model.*;

/** CS 1.0.1 Port Schedules standard specification module. */
public class CsPs101StandardSpecification extends CsPs100StandardSpecification {

  public CsPs101StandardSpecification() {
    this("1.0.1", "1.0.0");
  }

  protected CsPs101StandardSpecification(String versionNumber, String baselineVersion) {
    super(versionNumber, baselineVersion);
  }

  @Override
  protected Stream<Class<?>> modelClassesStream() {
    return modelClassesStreamWithReplacementClasses(
        super.modelClassesStream(),
        Set.of(
            Timestamp.class,
            CutOffTime.class,
            Vessel.class,
            ServicePartnerSchedule.class,
            ErrorResponse.class));
  }
}
