package com.refactorlab.refactorlab

import com.refactorlab.*
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Before
import org.junit.Test
import org.junit.Assert.*

class UserManagerTest {

    private lateinit var database: Database
    private lateinit var emailService: EmailService
    private lateinit var userValidator: UserValidator
    private lateinit var userService: UserService
    private lateinit var authService: AuthService
    private lateinit var userManager: UserManager

    @Before
    fun setUp() {
        database = mockk()
        emailService = mockk(relaxed = true) // Use relaxed = true to allow no-op calls
        userValidator = mockk()
        userService = UserService(database, emailService)
        authService = AuthService(database)
        userManager = UserManager(userService, authService, userValidator)
    }

    @Test
    fun when_registerUser_given_validData_then_shouldSucceed() {
        val user = User("John", "Doe", "john.doe@example.com", "password123", 25)
        val userData = User(user.firstName, user.lastName, user.email, user.password, user.age)

        every { userValidator.validateUserFields(user) } returns null
        every { database.saveUser(user) } returns Unit
        every { emailService.sendWelcomeEmail(user.email) } returns Unit

        userManager.registerUser(userData, onSuccess = {
            verify { database.saveUser(user) }
            verify { emailService.sendWelcomeEmail(user.email) }
        }, onError = {
            fail("Expected success but got error: $it")
        })
    }

    @Test
    fun when_registerUser_given_invalidEmail_then_shouldFail() {
        val user = User("John", "Doe", "invalid-email", "password123", 25)
        val userData = User(user.firstName, user.lastName, user.email, user.password, user.age)

        every { userValidator.validateUserFields(user) } returns "Invalid email address"

        userManager.registerUser(userData, onSuccess = {
            fail("Expected error but got success")
        }, onError = { error ->
            assertEquals("Invalid email address", error)
            verify(exactly = 0) { database.saveUser(any()) }
            verify(exactly = 0) { emailService.sendWelcomeEmail(any()) }
        })
    }

    @Test
    fun when_authenticateUser_given_validCredentials_then_shouldSucceed() {
        val email = "john.doe@example.com"
        val password = "password123"
        val user = User("John", "Doe", email, password, 25)

        every { database.getUserByEmail(email) } returns user

        userManager.authenticateUser(email, password) { result ->
            assertTrue(result)
        }
    }

    @Test
    fun when_authenticateUser_given_invalidCredentials_then_shouldFail() {
        val email = "john.doe@example.com"
        val password = "wrongpassword"
        val user = User("John", "Doe", email, "password123", 25)

        every { database.getUserByEmail(email) } returns user

        userManager.authenticateUser(email, password) { result ->
            assertFalse(result)
        }
    }

    @Test
    fun when_authenticateUser_given_nonExistentUser_then_shouldFail() {
        val email = "nonexistent@example.com"
        val password = "password123"

        every { database.getUserByEmail(email) } returns null

        userManager.authenticateUser(email, password) { result ->
            assertFalse(result)
        }
    }
}
