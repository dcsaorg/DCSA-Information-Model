# Track and Trace v3.0.0 Changelog

## Snapshot v3.0.0-20260116-alpha

Added back the `ShipmentEventTypeCode` value `CANC` (Cancelled). (SD-2752)


## Snapshot v3.0.0-20251205-alpha

Moved all shipment-related attributes from the `Event` root into the `ShipmentDetails`. (SD-2718)

Added support for USDA / COA UDM reefer cargo probe temperatures. (SD-2483)


## Snapshot v3.0.0-20251121-alpha

Clarified that `GeoCoordinate` attributes `latitude` and `longitude` are expected to be in ISO 6709 decimal degrees. (SD-2643)

Added `Seal` placement and new source values. (SD-2655)


## Snapshot v3.0.0-20251107-design

Added the missing `EquipmentEventTypeCode` values. (SD-2514)

Added more `ShipmentEventTypeCode` values. (SD-290)

Fixed the description of `ShipmentEventTypeCode` value `ISSU`. (SD-2515)

Aligned the actor names with the use cases and user stories. (SD-2531)

Updated rail and truck attributes. (SD-263)

Added support for multiple `delayReasonCodes`. (SD-2405)

Added support for retrieving events by `equipmentReference` independently of a shipment. (SD-2580)

Added support for filtering events using an `eventTypes` query parameter. (SD-2582)


## Snapshot v3.0.0-20250926-design

Added to type `Event` attribute `shipmentLocationType` of type `ShipmentLocationTypeCode`. (SD-2144)

Replaced in type `EquipmentDetails` attribute `isTransshipmentMove` with a new `movementType` with values `IMPORT`, `EXPORT`, `TRANSSHIPMENT`. (SD-2475)

Added `EventRouting` including originating, destination and forwarding parties. (SD-2145)

Added to type `Event` attribute `isRetracted`. (SD-2477)

Added to type `Event` attributes `documentReferenceReplacements` and `shipmentReferenceReplacements` to support split / combine and related use cases. (SD-302)

Added `DocumentTypeCode` value `AMF` for an "Advance Manifest Filing" of any subtype. (SD-305)

Added `DocumentTypeCode` value `PFD` for "Proof of Delivery". (SD-2460)

Added `IotEventCode` value `LOC` for "Location update". (SD-2480)

Updated in type `Event` the attribute `eventID` removing the `uuid` format constraint. (SD-2478)

Added the missing `API-Version` header in the requests of the `POST` endpoint. (SD-2490)


## Snapshot v3.0.0-20250912-design

Initial Track and Trace v3.0.0 design stage snapshot. (SD-2436)
