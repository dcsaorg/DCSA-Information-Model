package org.dcsa.standards.specifications.standards.ebl.v302.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(
    description = """
A binary document representing the visualisation of the eBL.
""",
    title = "Supporting Document",
    requiredProperties = {"name", "content"})
@Data
public class SupportingDocument {

  @Schema(
      description = """
Name of the Supporting Document
""",
      example = "Carrier rendered copy of the EBL.pdf",
      maxLength = 100)
  private String name;

  @Schema(
      description = """
The actual contents of the visual rendering.
""",
      example = "iVBORw0KGgoAAAANSUhEUgAAABAAAAAQCAYAAAAf8/9hAAAAAXNSR0IArs4c6QAAAARnQU1BAACxjwv8YQUAAAAJcEhZcwAADsQAAA7EAZUrDhsAAAGaSURBVDhPnZO/S0JRFMe/zygxwgqcfZtz0N7SFNgPaKlJpTFLCqwotV9qRYN/gIOL1FK22NISWUNT0BTUkNLgIL2iHxYRnc697/kKzdA+cOD8uOec77uXB/oHngMPnd2eSb/pAf5DP2EWhGlQ8ChIikiiQa7vruFacwHdHHwC9nY7mhqgRBTdsbDdA/nVvHQbYnxvHHhnp4XtFZjon4DapTam4Lx4jt7NXlO6WEsreltDA5RFlt4qHDaWXlgrwNnplDX5CcWnIo5vjmWimsGdQV7HjjjJ0gMDAbNZopU1wgwfmQSlL9JCkEkunyMEuLbMFgZZ161G5RsFES5WNrC8lC8Fb49XDlcWWLNVOHqttFGCo90haxUsyeEk8GhEfEm+lA/ZqyyGdof0ocJegMhIpKZZIC8xfhLH0v6SfstCzRubeK42tg9Od3RDm9c4qMV8hWguinAmDHTJvC5bVB6A8nYZtlabTFcjX0EQ6gshNhqTDSbPQGIsUbdZIhT8ZOt0izDFu+dAakI1svX59W/MXGbIveM2or8g+gL+Fn3DwcYf+gAAAABJRU5ErkJggg==",
      format = "byte")
  private String content;

  @Schema(
      description = """
The `Media Type` of the content being transmitted as defined by [Iana](https://www.iana.org/assignments/media-types/media-types.xhtml). Can be left out if the content is `application/pdf` (PDF).

**Condition:** This property is mandatory to provide if it differs from `application/pdf`
""",
      example = "application/msword",
      defaultValue = "application/pdf",
      maxLength = 100)
  private String contentType;
}
