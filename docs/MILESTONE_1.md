# Milestone 1 — Accounts, pairing and location authorization foundation

## Added
- Guardian account and child-profile request models.
- Client-side input validation (server validation remains mandatory).
- QR pairing protocol data model and pairing state vocabulary.
- Challenge expiry and revocation rules.
- Location-sharing gate requiring visible active state, OS permission and server authorization.
- Unit tests for validation, expiry and sharing gates.

## Not implemented
No production account creation, sessions, QR image generation/scanning, device binding, GPS transmission or credential storage. These require backend persistence, cryptographic key handling, authentication, rate limiting and integration tests. QR payloads must contain only an opaque short-lived challenge identifier.

## Next
1. Add migrations for identities, sessions, device keys and pairing challenges.
2. Implement verified registration without plaintext password storage.
3. Implement transactional one-time pairing.
4. Bind public keys and issue short-lived scoped tokens.
5. Accept locations only from paired devices with sharing enabled.
6. Add family-authorized WebSocket fan-out.
7. Implement Android location lifecycle after disclosures and battery tests.

Tests are authored but not yet run; no live pairing workflow is available.
