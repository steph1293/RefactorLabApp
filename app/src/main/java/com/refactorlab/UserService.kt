package com.refactorlab


class UserService(private val database: Database, private val emailService: EmailService) {
    fun registerUser(user: User, onSuccess: () -> Unit, onError: (String) -> Unit) {
        val validationError = UserValidator.validateUserFields(user)
        if (validationError != null) {
            onError(validationError)
            return
        }
        database.saveUser(user)
        emailService.sendWelcomeEmail(user.email)
        onSuccess()
    }
}