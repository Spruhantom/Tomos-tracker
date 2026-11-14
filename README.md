# TomOS Family

A privacy-first family location-sharing and safety app for Android, Linux and Windows, designed for self-hosted deployment.

> **Development status:** Early foundation only. Account registration, device pairing, live location, alerts, geofencing, map UI and production authentication are not yet implemented. Do not deploy this as a tracking service.

## Goals

- Guardian and child roles with transparent, revocable location sharing.
- Safe zones, SOS alerts, location history and device/battery alerts.
- Self-hosted Ktor backend with PostgreSQL/PostGIS.
- Shared Kotlin domain layer and cross-platform clients.
- Integration boundary for TomOS Maps without modifying its feature-frozen repository.

## Safety and privacy

Location sharing must be visible to the device user, permission-gated and revocable. Pairing requires explicit approval; QR codes carry only short-lived opaque challenge identifiers. Location history is opt-in, access-controlled and subject to retention/deletion rules. Complete a UK GDPR/Children's Code assessment and DPIA before real-world deployment.

## Repository layout

- `composeApp/` shared Kotlin domain models and tests
- `androidApp/` Android application entry point and manifest
- `backend/` Ktor service starter
- `database/` PostgreSQL/PostGIS initial schema
- `api/` OpenAPI contract draft
- `docs/` architecture, privacy, map integration and milestone notes

## Build status

This is a source scaffold, not a verified release. The initial tests are authored but have not yet been run. Build configuration and platform targets still need validation and completion.

See `docs/MILESTONE_1.md` and `docs/DEVELOPMENT.md` before contributing.
