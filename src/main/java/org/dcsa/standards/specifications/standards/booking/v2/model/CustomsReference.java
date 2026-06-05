package org.dcsa.standards.specifications.standards.booking.v2.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Schema(
    description =
"""
Reference associated with customs and/or excise purposes required by the relevant authorities for the import, export, or transit of the goods.

A small list of **potential** examples:

| Type  | Country | Description |
|-------|:-------:|-------------|
|ACID|EG|Advance Cargo Information Declaration in Egypt|
|CERS|CA|Canadian Export Reporting System|
|ITN|US|Internal Transaction Number in US|
|PEB|ID|PEB reference number|
|CSN|IN|Cargo Summary Notification (CSN)|
""")
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class CustomsReference extends org.dcsa.standards.specifications.standards.dt.v100.model.CustomsReference {}
