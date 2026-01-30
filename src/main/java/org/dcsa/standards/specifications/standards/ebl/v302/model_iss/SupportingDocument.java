package org.dcsa.standards.specifications.standards.ebl.v302.model_iss;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Schema(
    description =
        org.dcsa.standards.specifications.standards.ebl.v3.model_iss.SupportingDocument
            .CLASS_SCHEMA_DESCRIPTION)
@Getter
@Setter
@RequiredArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
public class SupportingDocument
    extends org.dcsa.standards.specifications.standards.ebl.v3.model_iss.SupportingDocument {

  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      description =
"""
Name of the Supporting Document
""",
      example = "Carrier rendered copy of the EBL.pdf",
      maxLength = 100)
  private String name;

  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      description =
"""
The actual contents of the visual rendering.
""",
      example =
          "iVBORw0KGgoAAAANSUhEUgAAABAAAAAQCAYAAAAf8/9hAAAAAXNSR0IArs4c6QAAAARnQU1BAACxjwv8YQUAAAAJcEhZcwAADsQAAA7EAZUrDhsAAAGaSURBVDhPnZO/S0JRFMe/zygxwgqcfZtz0N7SFNgPaKlJpTFLCqwotV9qRYN/gIOL1FK22NISWUNT0BTUkNLgIL2iHxYRnc697/kKzdA+cOD8uOec77uXB/oHngMPnd2eSb/pAf5DP2EWhGlQ8ChIikiiQa7vruFacwHdHHwC9nY7mhqgRBTdsbDdA/nVvHQbYnxvHHhnp4XtFZjon4DapTam4Lx4jt7NXlO6WEsreltDA5RFlt4qHDaWXlgrwNnplDX5CcWnIo5vjmWimsGdQV7HjjjJ0gMDAbNZopU1wgwfmQSlL9JCkEkunyMEuLbMFgZZ161G5RsFES5WNrC8lC8Fb49XDlcWWLNVOHqttFGCo90haxUsyeEk8GhEfEm+lA/ZqyyGdof0ocJegMhIpKZZIC8xfhLH0v6SfstCzRubeK42tg9Od3RDm9c4qMV8hWguinAmDHTJvC5bVB6A8nYZtlabTFcjX0EQ6gshNhqTDSbPQGIsUbdZIhT8ZOt0izDFu+dAakI1svX59W/MXGbIveM2or8g+gL+Fn3DwcYf+gAAAABJRU5ErkJggg==",
      format = "byte")
  private String content;
}
