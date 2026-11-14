package uk.co.tomosfamily.domain

import kotlinx.serialization.Serializable

@Serializable @JvmInline value class FamilyId(val value: String)
@Serializable @JvmInline value class MemberId(val value: String)
@Serializable enum class MemberRole { GUARDIAN, CHILD }

@Serializable
data class FamilyMember(
    val id: MemberId,
    val displayName: String,
    val role: MemberRole,
    val locationSharingEnabled: Boolean
)

@Serializable
data class LocationFix(
    val memberId: MemberId,
    val latitude: Double,
    val longitude: Double,
    val accuracyMetres: Float,
    val recordedAtEpochMillis: Long
) {
    init {
        require(latitude in -90.0..90.0)
        require(longitude in -180.0..180.0)
        require(accuracyMetres >= 0f)
    }
}

@Serializable
enum class AlertKind { SAFE_ZONE_ARRIVAL, SAFE_ZONE_DEPARTURE, SOS, LOW_BATTERY, DEVICE_OFFLINE }

@Serializable
data class FamilyAlert(
    val id: String,
    val familyId: FamilyId,
    val memberId: MemberId,
    val kind: AlertKind,
    val createdAtEpochMillis: Long,
    val acknowledgedAtEpochMillis: Long? = null
)
