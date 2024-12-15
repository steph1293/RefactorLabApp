package com.refactorlab.ui

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.refactorlab.User
import com.refactorlab.UserManager

@Composable
fun MainScreen(
    userManager: UserManager = hiltViewModel()
) {
    val context = LocalContext.current

    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var age by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Input Fields
        OutlinedTextField(
            value = firstName,
            onValueChange = { firstName = it },
            label = { Text("First Name") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = lastName,
            onValueChange = { lastName = it },
            label = { Text("Last Name") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Email),
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Password),
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = age,
            onValueChange = { age = it },
            label = { Text("Age") },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Register Button
        Button(onClick = {
            val userData = User(
                firstName = firstName,
                lastName = lastName,
                email = email,
                password = password,
                age = age.toIntOrNull() ?: 0
            )
            userManager.registerUser(
                userData,
                onSuccess = {
                    Toast.makeText(context, "Registration successful", Toast.LENGTH_SHORT).show()
                },
                onError = { error ->
                    Toast.makeText(context, error, Toast.LENGTH_SHORT).show()
                }
            )
        }) {
            Text("Register")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Authenticate Button
        Button(onClick = {
            userManager.authenticateUser(
                email = email,
                password = password
            ) { isAuthenticated ->
                if (isAuthenticated) {
                    Toast.makeText(context, "Authentication successful", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(context, "Authentication failed", Toast.LENGTH_SHORT).show()
                }
            }
        }) {
            Text("Authenticate")
        }
    }
}
