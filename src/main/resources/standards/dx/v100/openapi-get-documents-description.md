Retrieves the list of documents matching the criteria provided as query parameters.

If no documents match the criteria provided as query parameters, the response should not be an HTTP 404 error message, but a regular HTTP 200 response with an empty array in the message body.

The ordering of documents in the response message is unspecified.

The DX Producer is expected to additionally filter the documents based on the authorization and registration profile of the authenticated DX Consumer calling this endpoint. Authorization, registration and any such additional filtering are out of scope in this standard.

## Document filtering

The `documentID` query parameter is mandatory for all requests.

## Pagination

Response pagination uses a mechanism based on the `limit` and `cursor` query parameters and on the `Next-Page-Cursor` response header.

