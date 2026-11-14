# TomOS Maps integration boundary

Reference: https://github.com/Spruhantom/Tomos-maps

TomOS Maps is a separate, feature-frozen project. Its README describes a Kotlin Multiplatform/Compose app with MapLibre Compose, an OSM-derived OpenFreeMap Liberty style, UK postcode lookup and Valhalla routing. Its feature freeze excludes unrelated integrations and offline map downloads.

Family must not add features or change dependencies in TomOS Maps. Implement a Family-owned map port and independently configured MapLibre client after verifying provider terms and attribution. Reuse approved public data/provider strategy, not private app internals or copied app code. Keep OSM/provider attribution visible. Do not download from the public OSM standard tile service or add offline map downloads.
