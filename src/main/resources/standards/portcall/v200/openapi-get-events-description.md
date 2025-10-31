Retrieves the list of events matching the criteria provided as query parameters.

If no events match the criteria provided as query parameters, the response should not be an HTTP 404 error message, but a regular HTTP 200 response with an empty array in the message body.

The ordering of events in the response message is unspecified.

The event publisher is expected to additionally filter the events based on the authorization and registration profile of the authenticated party calling this endpoint. Authorization, registration and any such additional filtering are out of scope in this standard.

## Supported filtering parameters

Every event publisher **must** specify which of the query parameters below it supports in filtering events.

When receiving requests containing an unsupported query parameter, an event publisher can choose to either ignore the query parameter (if possible) or to reject the request with an HTTP 400.

### Filtering by event timestamp

Whenever the query parameters filter could retrieve large number of historical events, the request should also include at least the `eventTimestampMin` query parameter and could also include the `eventTimestampMax` query parameter.

The event publisher can separately also decide to have default relative date ranges and only return events having a timestamp within those ranges.

## Pagination

Response pagination uses a mechanism based on the `limit` and `cursor` query parameters and on the `Next-Page-Cursor` response header.

### Optional support

Each event publisher can decide whether to support the pagination of results, or to only allow limiting the response size using filtering query parameters.

If the event publisher does not provide pagination support, it can choose to either reject with an HTTP 400 any request containing the `limit` or `cursor` query parameters, or to handle the request normally while silently ignoring the pagination query parameters.

### Enabling pagination

The event retriever can use the `limit` query parameter to specify a maximum number of events that should be included in the response.

The event publisher can separately choose (and document on their copy of the published API specification) its own limit to the number of events that it would include in responses.

The effective page size limit is the minimum between the `limit` query parameter (if specified) and the publisher's own maximum page size configuration setting.

### Retrieving results pages

If based on filtering the response would include more events than this effective page size limit, the event publisher only adds events to the response up to this effective page size limit, setting in the response the `Next-Page-Cursor` to a value that it can accept as the value of the `cursor` query parameter in a subsequent request and use it to return the next page of results.

The API client reads the value of the `Next-Page-Cursor` response header and sets it as the value of the `cursor` query parameter in its next request.

The API client **must** keep alongside the `cursor` all the original query parameters from the request that retrieved the first page, in order to allow either a stateful or a stateless implementation of the pagination mechanism by the event publisher.

The effect of changing any of the original query parameters in subsequent pagination requests is unspecified and may result in the rejection of the request by the event publisher with an HTTP 4xx response.

The effect of attempting to use a cursor more than once (for example to "go back" to previous pages) is unspecified and may result in the rejection of the request by the event publisher with an HTTP 4xx response. (This is by design a backend-to-backend API, not intended for use directly from frontend applications and therefore not directly supporting a typical full set of web frontend pagination actions.)

When responding with the last page of results, the event publisher no longer includes a `Next-Page-Cursor` response header. The last page of results may be empty, for example in the case of a stateless pagination implementation by the event publisher.
