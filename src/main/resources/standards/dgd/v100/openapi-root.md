# DCSA Dangerous Good Declaration (DGD) API

This is the OpenAPI specification of the **DCSA Dangerous Good Declaration (DGD)** standard.

This API allows the transfer of structured DG declarations from a DGD Producer to a DGD Consumer.

Each DGD Producer implements the `GET /dgds` endpoint, which can be called by the authorized DGD Consumers to retrieve relevant available DG declarations.

Each DGD Consumer implements the `POST /dgds` endpoint, which can be called by DGD Producers to send relevant DG declarations as they become available or are updated.

The registration of DGD Consumers with DGD Producers is out of scope.

The authentication and authorization in both directions between DGD Producers and DGD Consumers is out of scope.

### Work in progress ⚠️

The DCSA Dangerous Goods Declaration (DGD) standard, including this API, is currently being designed and is **not** ready for general adoption yet.
