package com.example.learnapp.ui.screens

import android.provider.Settings
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.learnapp.ui.viewmodel.OtpViewModel

@Composable
fun OtpScreen(
    phone: String, // ✅ receive phone number
    onOtpVerified: (String) -> Unit,
    viewModel: OtpViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val deviceId = Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID)

    val otp by viewModel.otp.collectAsState()
    val message by viewModel.message.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {
        Text("OTP sent to $phone", style = MaterialTheme.typography.bodyLarge)

        Spacer(modifier = Modifier.height(32.dp))

        OutlinedTextField(
            value = otp,
            onValueChange = { viewModel.setOtp(it) }, // ✅ Delegate input state handling to ViewModel
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
