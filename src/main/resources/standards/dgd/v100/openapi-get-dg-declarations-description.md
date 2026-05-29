Retrieves the list of DG declarations matching the criteria provided as query parameters.

If no DG declarations match the criteria provided as query parameters, the response should not be an HTTP 404 error message, but a regular HTTP 200 response with an empty array in the message body.

The ordering of DG declarations in the response message is unspecified.

The DGD Producer is expected to additionally filter the DG declarations based on the authorization and registration profile of the authenticated DGD Consumer calling this endpoint. Authorization, registration and any such additional filtering are out of scope in this standard.

## DGD filtering

The `declarationReference` query parameter is mandatory for all requests.

Every DGD Producer must support the following combinations of additional query parameter filters:
* `carrierBookingReference`
* `carrierBookingReference`, `equipmentReference`
* `transportDocumentReference`
* `transportDocumentReference`, `equipmentReference`
* `equipmentReference`

Every DGD Producer must support combining any of the query parameter filters above with all of these additional filters:
* `declarationDateTimeMin`
* `declarationDateTimeMax`
* `declarationDateTimeMin`, `declarationDateTimeMax`

## Pagination

Response pagination uses a mechanism based on the `limit` and `cursor` query parameters and on the `Next-Page-Cursor` response header.

