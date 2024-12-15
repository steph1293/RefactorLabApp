package com.refactorlab

class AuthService(private val database: Database) {
    fun authenticateUser(email: String, password: String, onResult: (Boolean) -> Unit) {
        val user = database.getUserByEmail(email)
        if (user == null) {
            onResult(false)
            return
        }
        onResult(user.password == password)
    }
}
