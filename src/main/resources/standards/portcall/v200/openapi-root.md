# DCSA Port Call API

This is the OpenAPI specification of the **DCSA Port Call** standard.

This API allows the transfer of structured Port Call events from a publisher to a subscriber.

Port Call events are used by port call service providers and consumers to exchange information about the service, including:
- estimated, requested and planned service timestamps as part of [the "ERP" pattern defined by the IMO](https://wwwcdn.imo.org/localresources/en/OurWork/Facilitation/FAL%20related%20nonmandatory%20documents/FAL.5-Circ.52.pdf);
- actual service timestamps;
- container move counts (loads, discharges and restows).

The event publisher implements the `GET /events` endpoint, which can be called by authorized API consumers to retrieve relevant available events.

The event subscribers implement the `POST /events` endpoint, which can be called by event publishers to send relevant events as they become available.

The registration of event subscribers with event publishers is out of scope.

The authentication and authorization in both directions between event publishers and subscribers is out of scope.

### Work in progress ⚠️

The DCSA Port Call standard, including this API, is currently in beta validation and is **not** ready for general adoption yet.
