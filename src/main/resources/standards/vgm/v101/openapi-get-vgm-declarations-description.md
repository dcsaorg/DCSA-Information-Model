Retrieves the list of VGM declarations matching the criteria provided as query parameters.

If no VGM declarations match the criteria provided as query parameters, the response should not be an HTTP 404 error message, but a regular HTTP 200 response with an empty array in the message body.

The ordering of VGM declarations in the response message is unspecified.

The VGM Producer is expected to additionally filter the VGM declarations based on the authorization and registration profile of the authenticated VGM Consumer calling this endpoint. Authorization, registration and any such additional filtering are out of scope in this standard.

## VGM filtering

Every VGM Producer must support the following combinations of query parameter filters:
* `carrierBookingReference`
* `carrierBookingReference`, `equipmentReference`
* `transportDocumentReference`
* `transportDocumentReference`, `equipmentReference`
* `equipmentReference`

Every VGM Producer must support combining any of the query parameter filters above with all of these additional filters:
* `declarationDateTimeMin`
* `declarationDateTimeMax`
* `declarationDateTimeMin`, `declarationDateTimeMax`

Each VGM Producer can separately also decide to have default relative date ranges and only return VGM declarations having a `declarationDateTime` within those ranges.

When receiving requests containing an unsupported query parameter, a VGM Producer can choose to either ignore the query parameter (if possible) or to reject the request with an HTTP 400.

## Pagination

Response pagination uses a mechanism based on the `limit` and `cursor` query parameters and on the `Next-Page-Cursor` response header.

### Optional support

Each VGM Producer can decide whether to support the pagination of results, or to only allow limiting the response size using filtering query parameters.

If the VGM Producer does not provide pagination support, it can choose to either reject with an HTTP 400 any request containing the `limit` or `cursor` query parameters, or to handle the request normally while silently ignoring the pagination query parameters.

### Enabling pagination

The VGM Consumer can use the `limit` query parameter to specify a maximum number of VGM declarations that should be included in the response.

The VGM Producer can separately choose (and document on their copy of the published API specification) its own limit to the number of VGM declarations that it would include in responses.

The effective page size limit is the minimum between the `limit` query parameter (if specified) and the VGM Producer's own maximum page size configuration setting.

### Retrieving results pages

If based on filtering the response would include more VGM declarations than this effective page size limit, the VGM Producer only adds VGM declarations to the response up to this effective page size limit, setting in the response the `Next-Page-Cursor` to a value that it can accept as the value of the `cursor` query parameter in a subsequent request and use it to return the next page of results.

The VGM Consumer reads the value of the `Next-Page-Cursor` response header and sets it as the value of the `cursor` query parameter in its next request.

The VGM Consumer **must** keep alongside the `cursor` all the original query parameters from the request that retrieved the first page, in order to allow either a stateful or a stateless implementation of the pagination mechanism by the VGM Producer.

The effect of changing any of the original query parameters in subsequent pagination requests is unspecified and may result in the rejection of the request by the VGM Producer with an HTTP 4xx response.

The effect of attempting to use a cursor more than once (for example to "go back" to previous pages) is unspecified and may result in the rejection of the request by the VGM Producer with an HTTP 4xx response. (This is by design a backend-to-backend API, not intended for use directly from frontend applications and therefore not directly supporting a typical full set of web frontend pagination actions.)

When responding with the last page of results, the VGM Producer no longer includes a `Next-Page-Cursor` response header. The last page of results may be empty, for example in the case of a stateless pagination implementation by the VGM Producer.
