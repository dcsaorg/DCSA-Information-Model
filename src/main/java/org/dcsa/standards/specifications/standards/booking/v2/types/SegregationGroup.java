package org.dcsa.standards.specifications.standards.booking.v2.types;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
    type = "string",
    description =
"""
Grouping of Dangerous Goods having certain similar chemical properties. Possible values are:
- `1` (Acids)
- `2` (Ammonium Compounds)
- `3` (Bromates)
- `4` (Chlorates)
- `5` (Chlorites)
- `6` (Cyanides)
- `7` (Heavy metals and their salts)
- `8` (Hypochlorites)
- `9` (Lead and its compounds)
- `10` (Liquid halogenated hydrocarbons)
- `11` (Mercury and mercury compounds)
- `12` (Nitrites and their mixtures)
- `13` (Perchlorates)
- `14` (Permanganates)
- `15` (Powdered metals)
- `16` (Peroxides),
- `17` (Azides)
- `18` (Alkalis)
""",
    maxLength = 2)
public class SegregationGroup {}
