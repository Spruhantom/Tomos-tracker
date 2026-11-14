package uk.co.tomosfamily.domain

import kotlinx.serialization.Serializable

@Serializable enum class PairingStatus {
    CREATED, AWAITING_CHILD_APPROVAL, AWAITING_GUARDIAN_CONFIRMATION, PAIRED, EXPIRED, REVOKED
}
@Serializable data class PairingChallenge(
    val challengeId: String, val familyId: FamilyId, val guardianMemberId: MemberId,
    val expiresAtEpochMillis: Long, val status: PairingStatus
)
@Serializable data class PairingApproval(
    val challengeId: String, val deviceDisplayName: String, val devicePublicKeyBase64: String
)

/** QR payload is only a short-lived opaque challenge reference, never a bearer credential or secret. */
@Serializable data class PairingQrPayload(
    val protocol: String = "tomos-family-pairing-v1",
    val challengeId: String,
    val expiresAtEpochMillis: Long
)

object PairingRules {
    const val MAX_CHALLENGE_LIFETIME_MILLIS: Long = 5 * 60 * 1000L
    fun isUsable(challenge: PairingChallenge, nowEpochMillis: Long): Boolean =
        challenge.status in setOf(
            PairingStatus.CREATED, PairingStatus.AWAITING_CHILD_APPROVAL,
            PairingStatus.AWAITING_GUARDIAN_CONFIRMATION
        ) && nowEpochMillis < challenge.expiresAtEpochMillis
}
