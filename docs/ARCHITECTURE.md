# Architecture

## Components
- Android: guardian and child roles in one app; child location collection is visible and permission-gated.
- Desktop: Linux and Windows guardian dashboard.
- Shared Kotlin domain: identities, pairing, location-sharing policy and alerts.
- Ktor backend: authentication, family authorization, device registration, location ingestion, WebSocket fan-out and alert orchestration.
- PostgreSQL/PostGIS: family membership, devices, current positions, opt-in expiring history, safe zones and alerts.

## Trust boundaries
Clients are untrusted. Every read/write is authorized against the authenticated principal and family membership. Device identity is key-based and revocable. Location writes require active pairing, OS permission and user-visible sharing state.

## Location flow
1. Child explicitly enables sharing.
2. OS permission and server authorization are checked.
3. Device sends an authenticated fix over TLS.
4. API validates membership, timestamp, accuracy and rate limits.
5. Current position is updated; history is appended only when enabled and expires automatically.
6. Geofence transitions are debounced.
7. Guardians receive minimal alerts and open the app for details.

Desktop is a guardian client, not a hidden tracking agent.
