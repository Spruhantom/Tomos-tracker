# Development and verification

Intended checks after build configuration is validated:
```bash
./gradlew :composeApp:desktopTest
./gradlew :backend:test
./gradlew :backend:run
./gradlew :androidApp:assembleDebug
```

A Gradle wrapper and local PostGIS Compose service still need adding. Never use production family/child location data in development or CI.

CI should cover shared tests, backend tests, Linux desktop, Windows desktop, Android debug build, manifest permission review, dependency/license scanning and secret scanning.

Physical testing is required for GPS lifecycle, geofences, push, battery behaviour and SOS delivery. Start with consenting adult-owned devices and simulated locations.
