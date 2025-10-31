package org.dcsa.standards.specifications.standards.an.v100.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(
    description =
"""
Bank account information provided by the carrier for customers to make payments of the outstanding charges
""")
public class PaymentRemittance {

  @Schema(
      maxLength = 100,
      example = "Example Bank",
      description = "Name of the carrier's bank where payment should be sent")
  private String bankName;

  @Schema(
      maxLength = 40,
      example = "NL99ABNA0123456789",
      description = "Bank account number for receiving payment")
  private String accountNumber;

  @Schema(
      name = "SWIFTCode",
      maxLength = 11,
      example = "ABNANL2A",
      description = "SWIFT/BIC code for international wire transfers")
  private String swiftCode;

  @Schema(
      maxLength = 100,
      example = "Example Carrier",
      description = "Legal name of the payment recipient (carrier)")
  private String beneficiary;

  @Schema(
      maxLength = 100,
      example = "carrier@example.com",
      description = "Email address where proof of payment remittance should be sent")
  private String remittanceEmail;
}
