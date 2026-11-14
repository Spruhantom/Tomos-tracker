package uk.co.tomosfamily.domain

import kotlinx.serialization.Serializable

@Serializable enum class AccountStatus { PENDING_VERIFICATION, ACTIVE, SUSPENDED }
@Serializable data class FamilyAccount(val id: String, val email: String, val displayName: String, val status: AccountStatus)
@Serializable data class CreateGuardianAccountRequest(val email: String, val displayName: String)
@Serializable data class CreateChildProfileRequest(val displayName: String, val dateOfBirthYear: Int)

/** Client validation is for usability only; the API must repeat checks and enforce age/guardian rules. */
object AccountInputValidator {
    fun validEmail(value: String): Boolean =
        value.length <= 254 && value.count { it == '@' } == 1 &&
            value.substringAfter('@', "").contains('.') && value.none { it.isWhitespace() }

    fun validDisplayName(value: String): Boolean = value.trim().length in 1..80
}
