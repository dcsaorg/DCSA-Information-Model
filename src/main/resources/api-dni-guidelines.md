# DCSA API Design and Implementation Guidelines

This document outlines the guidelines used by DCSA in designing API standards and the guidelines expected to be followed by adopters of DCSA API standards.

## Changelog

Changes with respect to the published [API DNI Principles v2.0](https://developer.dcsa.org/api_design):
* We have updated, simplified and cleaned up the document to reflect the current and desired reality of DCSA API standard ecosystems.
* The rationale and other background information are discussed separately and are no longer maintained as part of this document.
* The document is now clearly role-focused (design guidelines followed by DCSA, implementation guidelines to be followed by adopters).
* All conformance information and guidelines are maintained separately in [a dedicated Developer Portal section](https://developer.dcsa.org/conformance).
* We no longer repeat any information that is already available in relevant external resources (most notably in the [OpenAPI Specification 3.0.x](https://spec.openapis.org/oas/v3.0.3.html)) or that is considered to be common best practice in designing and implementing APIs (for example the [OWASP Top 10 API Security Risks](https://owasp.org/API-Security/editions/2023/en/0x11-t10/)).
* Custom error code ranges never gained any traction and were removed.
* Endpoint paths no longer start with "/vN/" version prefixes.


## Design Guidelines

This section outlines the guidelines used by DCSA in designing API standards.

Target audience:
* DCSA Standards team - to make sure that all newly created standards adhere to these guidelines
* adopters of DCSA standards - to understand why and how decisions were made in designing the standard

### OpenAPI specifications

The OpenAPI specification description must link to the specific version of this document on which it is based.

The APIs in our standard are currently based on [OpenAPI Specification 3.0.x](https://spec.openapis.org/oas/v3.0.3.html).

### Versioning

We use a flexible SemVer versioning strategy whereby:
* patches introduce small fixes and enhancements in a backwards compatible way
* minor versions introduce new features (e.g., endpoints) in a backwards compatible way
* major versions introduce any changes without backwards compatibility restrictions

Elements can be deprecated in patches and minor versions, but can only be removed in major versions.

### Content type

All API requests and responses use `Content-Type: application/json` (with the implicit UTF-8 character set).

When necessary, PATCH requests can be used with content type `application/json-patch+json` or `application/merge-patch+json` instead.

Any binary data that needs to be included in an API message must be written Base64-encoded in a string attribute of the JSON message.

### Style and case

All API elements of the same type are written in a common case:
* URL path elements use `kebab-case`
* object type names use `PascalCase`
* property names use `camelCase`
* query parameters use `camelCase` and match the corresponding property names
* pseudo-enum string values use `UPPER_SNAKE_CASE`

Common prefixes:
* boolean properties are typically prefixed with `is`, `has`, `are` or `have`
* `universal` for properties whose values are unique across the entire ecosystem of API providers

Common suffixes
* `Date` for date properties
* `DateTime` for datetime properties
* `Time` for time properties
* `Number` for countable quantity properties
* `Code` for properties whose values reference external list elements
* `Unit` for properties representing a measurement unit
* `Id` for globally unique id properties (typically UUID)
* `Reference` for properties representing the business key of an object in an API provider's system
* `SubReference` for properties representing a second element of the composite business key of an object in an API provider's system

Other conventions:
* array / collection names are plural
* date properties use the `YYYY-MM-DD` format
* date-time properties use [the RFC 3339 format](https://swagger.io/docs/specification/v3_0/data-models/data-types/#strings)
* regular expressions match the ECMA262
* a common (container) shipping industry or legal term is typically preferred even when not meeting these criteria

### Methods and response codes

We use standard REST semantics and error codes for all endpoints:
* POST (200, 201, 202, 204) with the idempotency explicitly stated where applicable
* PUT (200, 201, 202, 204)
* PATCH (200, 202, 204) with the semantics always explicitly stated
* GET (200)
* HEAD (200) using ETag for resource version caching
* DELETE (200, 202, 204)

### Headers

Every API request and response must contain the `API-Version` header, set to the full version of the implemented DCSA standard.

We do not use deprecated `X-` headers.

### Default values

Every optional property or query parameter without a default value must specify the semantics of it not being present.

### Minimal constraints

All properties contain the "max" constraints (e.g., `maximum`, `maxItems`) that are typically required by API adopters in database sizing and in ensuring compatibility with existing systems and standards.

No other constraint (e.g., required properties, string pattern regex, minimum length or items) is added unless there is a clear business need or value for it.

Conformance validations (e.g., "the adopter has demonstrated the correct use of the freeTime object") are used to help ensure that adopters can use, if and when needed, all the technically optional properties that are functionally required.

### Code lists

When property values are defined in code lists managed by external organizations, we use one of the following approaches:
* we reference the original list source, providing a URL and optionally several example values
* we copy the full or relevant partial list and include it as a pseudo-enum in the API specification

### Array ordering

By default, adopters do not have to preserve between API calls the ordering of elements in arrays and collections.

When the order of elements in an array or collection must be preserved (e.g., the lines of text to be displayed on a package), the API specification explicitly states this.


### Error object

When specifying 4xx error responses in an endpoint, we require the error response body to have a standard structure that can be programmatically processed by API consumers.

The Booking 2.0.0 API contains [an example](https://app.swaggerhub.com/apis/dcsaorg/DCSA_BKG/2.0.0#/ErrorResponse) of how we define and use the "error object".

### Feedback object

In API specifications that allow API consumers to submit updated or amended entities, we include in the regular response (and if available, in notifications, see the dedicated section) a list of "feedback" objects that can be used to programmatically exchange information about what, why and how needs to be updated.

The Booking 2.0.0 API contains [an example](https://app.swaggerhub.com/apis/dcsaorg/DCSA_BKG/2.0.0#/Feedback) of how we define and use the "feedback object".

### Pagination

We use a simple and pragmatic pagination mechanism, suitable for a backend-to-backend API and implementable either in a stateful or stateless way, based on a "limit" and a "cursor" query parameters and on a "Next-Page-Cursor" response header.

The Arrival Notice 1.0.0 API contains [an example](https://app.swaggerhub.com/apis/dcsaorg/DCSA_AN/1.0.0#/AN%20Publisher%20Endpoints/get-arrival-notices) of how we define the pagination mechanism.

### Notifications

Instead of callbacks or webhooks, we use standard endpoints to be implemented by the API consumer of the main endpoints and allowing the API provider to send lightweight or full notifications to its counterpart.

The Arrival Notice 1.0.0 API contains [an example lightweight notification endpoint](https://app.swaggerhub.com/apis/dcsaorg/DCSA_AN/1.0.0#/AN%20Subscriber%20Endpoints/put-arrival-notice-notifications).

A "full notification" is similar with a "lightweight notification" but it also includes a property containing the full entity that the notification is about (in the example above, it would be an `arrivalNotice` property containing an entire `ArrivalNotice` object).

### Out of scope

The sorting of elements in a result is out of scope in DCSA standards.

Authentication and authorization are out of scope in DCSA standards.

Except for pagination, performance considerations (like rate limiting) are out of scope in DCSA standards.

### Avoided constructs and approaches

We **do not use** the following constructs and approaches in newly developed DCSA APIs:
* HATEOAS
* JSON-LD
* callbacks or webhooks
  * see the "Notifications" subsection for our alternative approach
* CloudEvents for notifications
* strings with custom parsing (except where required for compatibility with existing standards)


## Implementation Guidelines

This section outlines the guidelines expected by DCSA to be followed by adopters of DCSA API standards.

Target audience:
* adopters of DCSA standards - to understand how to implement the standard as API providers or consumers

### Backend-to-backend APIs

Unless explicitly specified otherwise in any individual standard, all DCSA API standards are designed as backend-to-backend APIs and are therefore not intended to be used directly from a web application frontend.

### Additional properties

While DCSA generally leaves "additionalProperties" unset, which according to the OpenAPI specification means that additional properties are allowed, API providers should never add non-standard properties except when using the DCSA extensions mechanism.

### Endpoint URLs

While not strictly required by the OpenAPI specification, API providers should deploy all the implemented endpoints of a standard under a single common base URL expanded with the exact URI path of each endpoint from the DCSA standard OpenAPI specification.

### Headers

Every API request and response must contain the `API-Version` header, set to the full version of the implemented DCSA standard.

### Pseudo-enum string values

When a string property can have a certain list of values, instead of defining the values in an OpenAPI `enum` that could not be changed in a backwards compatible way in a patch or minor version, DCSA defines the values in a "pseudo-enum", either by listing the values in the standard's OpenAPI specification or by referencing an external list maintained by another organization.

To support such pseudo-enum values in a backwards-compatible way, implement your system in a way that handles gracefully any new values introduced in patches or service packs and adopted by any of your counterparts who implemented the newer standard version before you.

### API documentation

Starting with a copy of the OpenAPI specification included in the DCSA standard, make all the necessary adjustments (extensions, pagination limits, etc.) without changing the semantics of the API specification.

### Extensions

When as an API provider you want to implement a released DCSA standard API in a conformant way but still require changes with respect to the released standard, you can contact DCSA for options.

In most cases, your requirements will be addressed by DCSA by releasing a patch or service pack including the necessary changes.

For the special situations in which the changes are specific to a single adopter, DCSA has a special "extensions" process through it will provide you with guidance on how to extend the standard to fit your needs.
