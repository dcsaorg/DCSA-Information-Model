package org.dcsa.standards.specifications.standards.cs.v102.messages;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Schema(description = "Unexpected error")
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ErrorResponse
    extends org.dcsa.standards.specifications.standards.cs.v101.messages.ErrorResponse {}
