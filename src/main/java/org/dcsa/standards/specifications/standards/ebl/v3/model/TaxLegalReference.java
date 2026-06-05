package org.dcsa.standards.specifications.standards.ebl.v3.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
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
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class TaxLegalReference extends org.dcsa.standards.specifications.standards.dt.v100.model.TaxLegalReference {}
