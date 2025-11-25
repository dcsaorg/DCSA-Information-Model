# DCSA Arrival Notice API

This is the OpenAPI specification of the **DCSA Arrival Notice** standard.

This API allows the transfer of structured arrival notice documents, including optional embedded PDF visualizations, from a publisher to a subscriber.

The arrival notice publisher implements the `GET` endpoint, which authorized API consumers can call to retrieve available arrival notices.

The arrival notice subscribers implement the `POST /arrival-notices` endpoint, which arrival notice publishers call to send relevant arrival notices as they become available.

The arrival notice subscribers implement the `POST /arrival-notice-notifications` endpoint, which arrival notice publishers call to send lightweight notifications about relevant arrival notices as they become available. When subscribers receive such notifications, they can choose to call the `GET` endpoint to retrieve the arrival notices that are relevant to them at the time.

The registration of subscribers with arrival notice publishers is out of scope.

The authentication and authorization in both directions between arrival notice publishers and subscribers is out of scope.
