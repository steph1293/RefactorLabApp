package com.refactorlab.refactorlab



import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.refactorlab.ui.MainScreen
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun when_registerUser_given_validInput_then_shouldShowSuccessMessage() {
        composeTestRule.setContent {
            MainScreen()
        }

        // Fill in user details
        composeTestRule.onNodeWithText("First Name").performTextInput("John")
        composeTestRule.onNodeWithText("Last Name").performTextInput("Doe")
        composeTestRule.onNodeWithText("Email").performTextInput("john.doe@example.com")
        composeTestRule.onNodeWithText("Password").performTextInput("password123")
        composeTestRule.onNodeWithText("Age").performTextInput("25")

        // Click the Register button
        composeTestRule.onNodeWithText("Register").performClick()

        // Verify the success message is displayed
        composeTestRule.onNodeWithText("Registration successful").assertExists()
    }

    @Test
    fun when_registerUser_given_invalidEmail_then_shouldShowErrorMessage() {
        composeTestRule.setContent {
            MainScreen()
        }

        // Fill in user details
        composeTestRule.onNodeWithText("First Name").performTextInput("John")
        composeTestRule.onNodeWithText("Last Name").performTextInput("Doe")
        composeTestRule.onNodeWithText("Email").performTextInput("invalid-email")
        composeTestRule.onNodeWithText("Password").performTextInput("password123")
        composeTestRule.onNodeWithText("Age").performTextInput("25")

        // Click the Register button
        composeTestRule.onNodeWithText("Register").performClick()

        // Verify the error message is displayed
        composeTestRule.onNodeWithText("Invalid email address").assertExists()
    }

    @Test
    fun when_registerUser_given_emptyFields_then_shouldShowErrorMessage() {
        composeTestRule.setContent {
            MainScreen()
        }

        // Leave user details empty

        // Click the Register button
        composeTestRule.onNodeWithText("Register").performClick()

        // Verify the error message is displayed
        composeTestRule.onNodeWithText("All fields must be filled").assertExists()
    }
}
