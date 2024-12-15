package com.refactorlab

// Database.kt

object Database {
    private val users = mutableListOf<User>()

    fun saveUser(user: User) {
        users.add(user)
    }

    fun getUserByEmail(email: String): User? {
        return users.find { it.email == email }
    }
}
