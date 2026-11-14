package uk.co.tomosfamily.domain

import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class PairingRulesTest {
    @Test fun challengeExpiresAndCannotBeReusedAfterExpiry() {
        val challenge = PairingChallenge("opaque-id", FamilyId("family"), MemberId("guardian"), 10_000, PairingStatus.CREATED)
        assertTrue(PairingRules.isUsable(challenge, 9_999))
        assertFalse(PairingRules.isUsable(challenge, 10_000))
    }
    @Test fun revokedChallengeIsNotUsable() {
        val challenge = PairingChallenge("opaque-id", FamilyId("family"), MemberId("guardian"), 10_000, PairingStatus.REVOKED)
        assertFalse(PairingRules.isUsable(challenge, 1_000))
    }
}
