CREATE EXTENSION IF NOT EXISTS pgcrypto;
CREATE EXTENSION IF NOT EXISTS postgis;

CREATE TABLE families (
 id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
 display_name TEXT NOT NULL CHECK (length(display_name) BETWEEN 1 AND 100),
 created_at TIMESTAMPTZ NOT NULL DEFAULT now()
);
CREATE TABLE members (
 id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
 family_id UUID NOT NULL REFERENCES families(id) ON DELETE CASCADE,
 display_name TEXT NOT NULL CHECK (length(display_name) BETWEEN 1 AND 80),
 role TEXT NOT NULL CHECK (role IN ('GUARDIAN','CHILD')),
 location_sharing_enabled BOOLEAN NOT NULL DEFAULT FALSE,
 created_at TIMESTAMPTZ NOT NULL DEFAULT now()
);
CREATE INDEX members_family_id_idx ON members(family_id);
CREATE TABLE devices (
 id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
 member_id UUID NOT NULL REFERENCES members(id) ON DELETE CASCADE,
 public_key BYTEA NOT NULL, device_label TEXT NOT NULL, revoked_at TIMESTAMPTZ,
 created_at TIMESTAMPTZ NOT NULL DEFAULT now()
);
CREATE TABLE safe_zones (
 id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
 family_id UUID NOT NULL REFERENCES families(id) ON DELETE CASCADE,
 name TEXT NOT NULL, center GEOGRAPHY(POINT,4326) NOT NULL,
 radius_metres INTEGER NOT NULL CHECK (radius_metres BETWEEN 50 AND 5000),
 created_at TIMESTAMPTZ NOT NULL DEFAULT now()
);
CREATE INDEX safe_zones_center_idx ON safe_zones USING GIST(center);
CREATE TABLE current_locations (
 member_id UUID PRIMARY KEY REFERENCES members(id) ON DELETE CASCADE,
 position GEOGRAPHY(POINT,4326) NOT NULL,
 accuracy_metres REAL NOT NULL CHECK (accuracy_metres >= 0),
 recorded_at TIMESTAMPTZ NOT NULL, received_at TIMESTAMPTZ NOT NULL DEFAULT now()
);
CREATE INDEX current_locations_position_idx ON current_locations USING GIST(position);
CREATE TABLE location_history (
 id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
 member_id UUID NOT NULL REFERENCES members(id) ON DELETE CASCADE,
 position GEOGRAPHY(POINT,4326) NOT NULL,
 accuracy_metres REAL NOT NULL CHECK (accuracy_metres >= 0),
 recorded_at TIMESTAMPTZ NOT NULL, expires_at TIMESTAMPTZ NOT NULL
);
CREATE INDEX location_history_member_time_idx ON location_history(member_id, recorded_at DESC);
CREATE INDEX location_history_expiry_idx ON location_history(expires_at);
CREATE TABLE family_alerts (
 id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
 family_id UUID NOT NULL REFERENCES families(id) ON DELETE CASCADE,
 member_id UUID NOT NULL REFERENCES members(id) ON DELETE CASCADE,
 kind TEXT NOT NULL CHECK (kind IN ('SAFE_ZONE_ARRIVAL','SAFE_ZONE_DEPARTURE','SOS','LOW_BATTERY','DEVICE_OFFLINE')),
 payload JSONB NOT NULL DEFAULT '{}'::jsonb,
 created_at TIMESTAMPTZ NOT NULL DEFAULT now(), acknowledged_at TIMESTAMPTZ
);
CREATE INDEX family_alerts_family_time_idx ON family_alerts(family_id, created_at DESC);
-- Production must add family-scoped row-level security, authorization and scheduled retention deletion.
