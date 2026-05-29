Retrieves the list of DGD feedback elements matching the criteria provided as query parameters.

If no feedback elements match the criteria, the response should not be an HTTP 404 error message, but a regular HTTP 200 response with an empty array in the message body.

The ordering of feedback elements in the response message is unspecified.

The `declarationReference` query parameter is mandatory for all requests.

