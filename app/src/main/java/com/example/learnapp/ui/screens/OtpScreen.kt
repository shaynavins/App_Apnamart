package com.example.learnapp.ui.screens

import android.provider.Settings
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.learnapp.ui.viewmodel.OtpViewModel
import android.util.Log


@Composable
fun OtpScreen(
    viewModel: OtpViewModel = hiltViewModel(),
    onOtpVerified: (String) -> Unit, // token will be passed here
) {
    val context = LocalContext.current
    val deviceId = Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID)

    var phone by remember { mutableStateOf("") }
    var otp by remember { mutableStateOf("") }
    val message by viewModel.message.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {
        OutlinedTextField(
            value = phone,
            onValueChange = { phone = it },
            label = { Text("Phone Number") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        Button(
            onClick = { viewModel.sendOtp(phone) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Send OTP")
        }

        Spacer(modifier = Modifier.height(32.dp))

        OutlinedTextField(
            value = otp,
            onValueChange = { otp = it },
            label = { Text("Enter OTP") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        Button(
            onClick = {
                Log.d("OtpScreen", "Verify OTP clicked")
                viewModel.verifyOtp(phone, otp, deviceId) { success, token ->
                    if (success && token != null) {
                        onOtpVerified(token)
                    }
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Verify OTP")
        }


        Spacer(modifier = Modifier.height(20.dp))

        Text(text = message)
    }
}
