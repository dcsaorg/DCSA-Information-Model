# DCSA Verified Gross Mass (VGM) API

This is the OpenAPI specification of the **DCSA Verified Gross Mass (VGM)** standard.

This API allows the transfer of structured VGM declarations from a VGM Producer to a VGM Consumer.

Each VGM Producer implements the `GET /vgm-declarations` endpoint, which can be called by the authorized VGM Consumers to retrieve relevant available VGM declarations.

Each VGM Consumer implements the `POST /vgm-declarations` endpoint, which can be called by VGM Producers to send relevant VGM declarations as they become available or are updated.

The registration of VGM Consumers with VGM Producers is out of scope.

The authentication and authorization in both directions between VGM Producers and VGM Consumers is out of scope.

### Work in progress ⚠️

The DCSA Verified Gross Mass (VGM) standard, including this API, is currently being reviewed and is **not** ready for general adoption yet.
