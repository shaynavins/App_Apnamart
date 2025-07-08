package com.example.learnapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.apnamart.arrowlogx.ui.viewmodel.OtpViewModel

@Composable
fun PhoneNumberScreen(
    onPhoneEntered: (String) -> Unit,
    viewModel: OtpViewModel = hiltViewModel()
) {
    val phone by viewModel.phone.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        OutlinedTextField(
            value = phone,
            onValueChange = { viewModel.setPhone(it) },
            label = { Text("Phone Number") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                viewModel.sendOtp(phone)
                onPhoneEntered(phone)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Send OTP")
        }
    }
}

