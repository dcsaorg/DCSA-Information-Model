Retrieves the list of arrival notices matching the criteria provided as query parameters.

If no arrival notices match the criteria provided as query parameters, the response should not be an HTTP 404 error message, but a regular HTTP 200 response with an empty array in the message body.

The ordering of arrival notices in the response message is unspecified.

The arrival notice publisher is expected to additionally filter the arrival notices based on the authorization and registration profile of the authenticated party calling this endpoint, for example by returning only arrival notices with or without charges, or localized for a certain region or in a certain language. Authorization, registration and any such additional filtering are out of scope in this standard.

## Filtering

Every arrival notice publisher **must** support the retrieval of arrival notices based on the transport document references of one or more bills of lading, specified as the value(s) of the `transportDocumentReferences` query parameter.

Each arrival notice publisher can decide whether to support any of the additional filtering query parameters defined in the standard for this endpoint, and should document the decision in their published copy of the API specification.

When receiving requests containing an unsupported query parameter, an arrival notice publisher can choose to either ignore the query parameter (if possible) or to reject the request with an HTTP 400. In particular, the publisher can reject with HTTP 400 a GET request with a number of `transportDocumentReferences` or `equipmentReferences` larger than the maximum supported (which in the case of some publishers is likely to be `1`). Publishers should document these limits in their copy of the API specification.

If multiple versions of an arrival notice exists, only the latest version will be returned in the result. The standard does not provide a way to retrieve older versions of an arrival notice.

### Filtering by transport document reference

When filtering using the `transportDocumentReferences` query parameter, the response contains arrival notices for all the bills of lading with the specified transport document reference for which an arrival notice is available (and accessible by the party making the API request).

Even when requesting arrival notices for a single transport document reference, in the absence of further explicit filtering in the request or implicit filtering by the arrival notice publisher, the response is expected to contain all the arrival notices corresponding to that bill of lading: with or without charges, and in every language in which it is available.

### Filtering alternatives to the transport document reference

As an alternative to retrieving arrival notices by the bill of lading using the `transportDocumentReferences` query parameter, if support is implemented by the arrival notice publisher, it is also possible to filter arrival notices using these query parameters:
* `equipmentReferences`
* `portOfDischarge`
* `vesselIMONumber` or `vesselName`
* `carrierImportVoyageNumber` or `universalImportVoyageReference`
* `carrierServiceCode` or `universalServiceReference`

The arrival notice publisher can decide to also allow retrieving arrival notices without specifying any of these filtering query parameters, instead filtering the results only based on the authorization and registration profile of the authenticated party calling this endpoint.

### Filtering by POD arrival date

Whenever the query parameters filter could retrieve large number of historical arrival notices (for example when filtering only by vessel name or IMO number), the request should also include at least the `portOfDischargeArrivalDateMin` query parameter and could also include the `portOfDischargeArrivalDateMax` query parameter.

The arrival notice publisher can separately also decide to have default relative date ranges and only return arrival notices having a `portOfDischargeArrivalDate` within those ranges.

### Filtering by region or language

There are no query parameters with which to request the filtering of arrival notices based on language or any other content or format localization for a specific region.

## Pagination

Response pagination uses a mechanism based on the `limit` and `cursor` query parameters and on the `Next-Page-Cursor` response header.

### Optional support

Each arrival notice publisher can decide whether to support the pagination of results, or to only allow limiting the response size using filtering query parameters.

If the arrival notice publisher does not provide pagination support, it can choose to either reject with an HTTP 400 any request containing the `limit` or `cursor` query parameters, or to handle the request normally while silently ignoring the pagination query parameters.

### Enabling pagination

The arrival notice retriever can use the `limit` query parameter to specify a maximum number of arrival notice objects that should be included in the response.

The arrival notice publisher can separately choose (and document on their copy of the published API specification) its own limit to the number of arrival notice objects that it would include in responses.

The effective page size limit is the minimum between the `limit` query parameter (if specified) and the publisher's own maximum page size configuration setting.

### Retrieving results pages

If based on filtering the response would include more arrival notices than this effective page size limit, the arrival notice publisher only adds arrival notices to the response up to this effective page size limit, setting in the response the `Next-Page-Cursor` to a value that it can accept as the value of the `cursor` query parameter in a subsequent request and use it to return the next page of results.

The API client reads the value of the `Next-Page-Cursor` response header and sets it as the value of the `cursor` query parameter in its next request.

The API client **must** keep alongside the `cursor` all the original query parameters from the request that retrieved the first page, in order to allow either a stateful or a stateless implementation of the pagination mechanism by the arrival notice publisher.

The effect of changing any of the original query parameters in subsequent pagination requests is unspecified and may result in the rejection of the request by the arrival notice publisher with an HTTP 4xx response.

The effect of attempting to use a cursor more than once (for example to "go back" to previous pages) is unspecified and may result in the rejection of the request by the arrival notice publisher with an HTTP 4xx response. (This is by design a backend-to-backend API, not intended for use directly from frontend applications and therefore not directly supporting a typical full set of web frontend pagination actions.)

When responding with the last page of results, the arrival notice publisher no longer includes a `Next-Page-Cursor` response header. The last page of results may be empty, for example in the case of a stateless pagination implementation by the arrival notice publisher.
