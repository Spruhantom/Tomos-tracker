# Architecture decisions

## ADR-001: Separate repository
Family is isolated from feature-frozen TomOS Maps to keep identity, geofencing and child privacy concerns independent.

## ADR-002: One Android app, separate roles
Shared networking/security/domain code; server-enforced roles.

## ADR-003: Desktop guardian-only
Linux and Windows desktop clients are guardian dashboards. Child location collection is Android-only initially.

## ADR-004: Self-hosted backend
Ktor and PostgreSQL/PostGIS run on infrastructure controlled by the project owner.

## ADR-005: History disabled by default
Current location and historical location are separate paths; history requires explicit enablement and expiry.
