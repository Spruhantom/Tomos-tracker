package uk.co.tomosfamily.domain

/** Location sharing requires the visible user switch, OS permission and family authorization. */
data class SharingPolicy(
    val userEnabled: Boolean,
    val platformPermissionGranted: Boolean,
    val familyAuthorizationActive: Boolean
) {
    val mayShareLocation: Boolean
        get() = userEnabled && platformPermissionGranted && familyAuthorizationActive
}
