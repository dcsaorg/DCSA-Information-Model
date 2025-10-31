# DCSA Track and Trace API

This is the OpenAPI specification of the **DCSA Track and Trace** standard.

This API allows the transfer of structured track and trace Events from an  Event Producer to an Event Consumer.

Each Event Producer implements the `GET /events` endpoint, which can be called by the authorized Event Consumers to retrieve relevant available Events.

Each Event Consumer implements the `POST /events` endpoint, which can be called by Event Producers call to send relevant Events as they become available.

The registration of Event Consumers with Event Producers is out of scope.

The authentication and authorization in both directions between Event Producers and Event Consumers is out of scope.

### Work in progress ⚠️

The DCSA Track and Trace standard, including this API, is currently being designed and is **not** ready for general adoption yet.
