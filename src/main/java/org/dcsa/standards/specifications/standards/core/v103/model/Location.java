package org.dcsa.standards.specifications.standards.core.v103.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.dcsa.standards.specifications.standards.core.v102.model.Address;
import org.dcsa.standards.specifications.standards.core.v102.types.FacilityTypeCode;
import org.dcsa.standards.specifications.standards.core.v102.types.UNLocationCode;

@Data
@Schema(description = "Location information")
public class Location {

  @Schema(maxLength = 100, example = "Port of Amsterdam", description = "The name of the location.")
  private String locationName;

  @Schema()
  private FacilityTypeCode facilityTypeCode;

  @Schema(description = "Location address")
  private Address address;

  @Schema(name = "UNLocationCode")
  private UNLocationCode unLocationCode;

  @Schema()
  private Facility facility;

  @Schema() private GeoCoordinate geoCoordinate;
}
