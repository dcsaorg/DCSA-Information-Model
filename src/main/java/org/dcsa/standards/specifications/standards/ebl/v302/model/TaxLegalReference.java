package org.dcsa.standards.specifications.standards.ebl.v302.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Schema(
    description =
"""
Reference that uniquely identifies a party for tax and/or legal purposes in accordance with the relevant jurisdiction.

A small list of **potential** examples:

| Type  | Country | Description |
|-------|:-------:|-------------|
|EORI|NL|Economic Operators Registration and Identification|
|PAN|IN|Goods and Services Tax Identification Number in India|
|GSTIN|IN|Goods and Services Tax Identification Number in India|
|IEC|IN|Importer-Exported Code in India|
|RUC|EC|Registro Único del Contribuyente in Ecuador|
|RUC|PE|Registro Único del Contribuyente in Peru|
|NIF|MG|Numéro d'Identification Fiscal in Madagascar|
|NIF|DZ|Numéro d'Identification Fiscal in Algeria|
""")
@Getter
@Setter
@RequiredArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
public class TaxLegalReference
    extends org.dcsa.standards.specifications.standards.ebl.v3.model.TaxLegalReference {

  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      description =
"""
The 2 characters for the country code using [ISO 3166-1 alpha-2](https://www.iso.org/obp/ui/#iso:pub:PUB500001:en)

**Note:** In case the `countryCode` is unknown it is possible to use the code `ZZ`.
""",
      example = "NL",
      minLength = 2,
      maxLength = 2,
      pattern = "^[A-Z]{2}$")
  private String countryCode;
}
