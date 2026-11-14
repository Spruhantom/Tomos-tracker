package uk.co.tomosfamily.domain

enum class LocationSharingState { OFF, AWAITING_PERMISSION, ACTIVE, PAUSED_BY_USER, PAUSED_BY_SYSTEM, CONNECTION_LOST }

/** Collection is allowed only while visible sharing, OS permission and server authorization are active. */
data class LocationSharingGate(
    val state: LocationSharingState,
    val platformPermissionGranted: Boolean,
    val serverAuthorizationActive: Boolean
) {
    val canTransmit: Boolean
        get() = state == LocationSharingState.ACTIVE && platformPermissionGranted && serverAuthorizationActive
}
