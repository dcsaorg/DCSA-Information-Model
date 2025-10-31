package org.dcsa.standards.specifications.standards.an.v100.types;

import org.dcsa.standards.specifications.generator.EnumBase;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@Schema(
    type = "string",
    maxLength = 50,
    example = "FULLY_PAID",
    description =
"""
Payment status of the collect freight charges.
""")
@AllArgsConstructor
public enum FreightPaymentStatus implements EnumBase {
  ZERO_PAID("Zero Paid"),
  PARTIALLY_PAID("Partially Paid"),
  FULLY_PAID("Fully Paid"),
  OVERPAID("Overpaid");

  private final String valueDescription;
}
