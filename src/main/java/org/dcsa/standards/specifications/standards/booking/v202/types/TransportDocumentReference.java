package org.dcsa.standards.specifications.standards.booking.v202.types;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
    type = "string",
    description = "The `transportDocumentReference` to be associated to this Booking.",
    example = "reserved-HHL123",
    maxLength = 20,
    pattern = "^\\S(?:.*\\S)?$")
public class TransportDocumentReference {}
