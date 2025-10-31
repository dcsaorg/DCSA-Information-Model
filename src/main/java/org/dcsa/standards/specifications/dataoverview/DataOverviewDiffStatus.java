package org.dcsa.standards.specifications.dataoverview;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor()
public enum DataOverviewDiffStatus {
  ADDED("Added"),
  REMOVED("Removed"),
  OLD_VALUE("Old value"),
  NEW_VALUE("New value"),
  UNMODIFIED("");

  private final String displayName;
}
