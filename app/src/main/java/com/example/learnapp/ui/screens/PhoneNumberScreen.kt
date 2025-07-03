package com.example.learnapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.learnapp.ui.navigation.Routes

@Composable
fun PhoneNumberScreen(onPhoneEntered: (String) -> Unit) {
    var phone by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        OutlinedTextField(
            value = phone,
            onValueChange = { phone = it },
            label = { Text("Phone Number") }
        )
        Spacer(modifier = Modifier.height(24.dp))
        Button(onClick = {
            onPhoneEntered(phone)
        }) {
            Text("Continue")
        }
    }
}
