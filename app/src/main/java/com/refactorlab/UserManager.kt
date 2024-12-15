package com.refactorlab


class UserManager(
    private val userService: UserService,
    private val authService: AuthService,
    private val userValidator: UserValidator
) {
    fun registerUser(
        userData: User,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        val user = User(userData.firstName, userData.lastName, userData.email, userData.password, userData.age)
        val validationError = userValidator.validateUserFields(user)
        if (validationError != null) {
            onError(validationError)
            return
        }
        userService.registerUser(user, onSuccess, onError)
    }

    fun authenticateUser(
        email: String,
        password: String,
        onResult: (Boolean) -> Unit
    ) {
        authService.authenticateUser(email, password, onResult)
    }
}
