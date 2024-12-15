package com.refactorlab

object UserValidator {
    fun validateUserFields(user: User): String? {
        if (user.firstName.isEmpty() || user.lastName.isEmpty() || user.email.isEmpty() || user.password.isEmpty()) {
            return "All fields must be filled"
        }
        if (!user.email.contains("@")) {
            return "Invalid email address"
        }
        if (user.password.length < 6) {
            return "Password must be at least 6 characters"
        }
        if (user.age < 18) {
            return "User must be at least 18 years old"
        }
        return null
    }
}
