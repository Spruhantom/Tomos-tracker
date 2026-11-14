package uk.co.tomosfamily.domain

import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class AccountInputValidatorTest {
    @Test fun validatesEmailShape() {
        assertTrue(AccountInputValidator.validEmail("parent@example.co.uk"))
        assertFalse(AccountInputValidator.validEmail("not-an-email"))
        assertFalse(AccountInputValidator.validEmail("parent @example.co.uk"))
    }
    @Test fun validatesDisplayNameBounds() {
        assertTrue(AccountInputValidator.validDisplayName("Parent"))
        assertFalse(AccountInputValidator.validDisplayName(" "))
        assertFalse(AccountInputValidator.validDisplayName("x".repeat(81)))
    }
}
