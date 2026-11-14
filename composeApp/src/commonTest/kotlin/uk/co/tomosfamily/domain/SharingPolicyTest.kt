package uk.co.tomosfamily.domain

import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class SharingPolicyTest {
    @Test fun sharingRequiresAllThreeGates() {
        assertTrue(SharingPolicy(true, true, true).mayShareLocation)
        assertFalse(SharingPolicy(false, true, true).mayShareLocation)
        assertFalse(SharingPolicy(true, false, true).mayShareLocation)
        assertFalse(SharingPolicy(true, true, false).mayShareLocation)
    }
}
