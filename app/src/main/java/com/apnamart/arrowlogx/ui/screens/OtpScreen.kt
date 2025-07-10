//package com.example.learnapp.ui.screens
//
//import android.provider.Settings
//import android.util.Log
//import androidx.compose.foundation.Image
//
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material3.*
//import androidx.compose.runtime.*
//import androidx.compose.ui.platform.LocalContext
//import androidx.compose.ui.unit.dp
//import androidx.hilt.navigation.compose.hiltViewModel
//import com.apnamart.arrowlogx.ui.viewmodel.OtpViewModel
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.layout.ContentScale
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.text.style.TextAlign
//import androidx.compose.ui.unit.dp
//import androidx.hilt.navigation.compose.hiltViewModel
//import com.apnamart.arrowlogx.R
//import com.apnamart.arrowlogx.ui.theme.Inter
//import com.apnamart.arrowlogx.ui.theme.InterBold
//import com.apnamart.arrowlogx.ui.theme.InterMedium
//import com.apnamart.arrowlogx.ui.theme.InterSemiBold
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.graphics.Color
//
//@Composable
//fun OtpScreen(
//    phone: String, // ✅ receive phone number
//    onOtpVerified: (String) -> Unit,
//    viewModel: OtpViewModel = hiltViewModel()
//) {
//    val context = LocalContext.current
//    val deviceId = Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID)
//
//    val otp by viewModel.otp.collectAsState()
//    val message by viewModel.message.collectAsState()
//
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(24.dp)
//    ) {
//        Image(
//            painter = painterResource(id = R.drawable.logo), // your truck logo
//            contentDescription = "Logo",
//            modifier = Modifier.size(160.dp),
//            contentScale = ContentScale.FillBounds
//        )
//
//        Spacer(modifier = Modifier.height(32.dp))
//
//        Text(
//            text = "Enter OTP",
//            style = MaterialTheme.typography.titleLarge,
//            fontFamily = InterBold,
//        )
//        Spacer(modifier = Modifier.height(4.dp))
//        Text(
//            text = "Please enter the OTP we have sent to your mobile number $phone ✎",
//            textAlign = TextAlign.Center,
//            style = MaterialTheme.typography.bodyMedium,
//            fontFamily = Inter
//        )
//
//        Spacer(modifier = Modifier.height(24.dp))
//
//        OutlinedTextField(
//            value = otp,
//            onValueChange = { viewModel.setOtp(it) },
//            label = { Text("Enter OTP") },
//            modifier = Modifier.fillMaxWidth()
//        )
//
//        Spacer(modifier = Modifier.height(12.dp))
//
//        Button(
//            onClick = {
//                Log.d("OtpScreen", "Verify OTP clicked")
//                viewModel.verifyOtp(phone, otp, deviceId) { success, token ->
//                    if (success && token != null) {
//                        onOtpVerified(token)
//                    }
//                }
//            },
//            modifier = Modifier
//                .fillMaxWidth()
//                .height(50.dp),
//            shape = RoundedCornerShape(12.dp),
//            colors = ButtonDefaults.buttonColors(
//                containerColor = MaterialTheme.colorScheme.primary,         // Blue background
//                contentColor = MaterialTheme.colorScheme.onPrimary          // White text
//            )
//        ) {
//            Text(
//                text = "Continue",
//                fontFamily = InterSemiBold,
//                style = MaterialTheme.typography.bodyLarge
//            )
//        }
//
//
//
//
//    }
//}
//
//@Composable
//fun OtpInputField(
//    otpText: String,
//    onOtpTextChange: (String) -> Unit,
//    charCount: Int = 6
//) {
//    Row(
//        horizontalArrangement = Arrangement.spacedBy(12.dp),
//        modifier = Modifier.fillMaxWidth(),
//    ) {
//        repeat(charCount) { index ->
//            val char = otpText.getOrNull(index)?.toString() ?: ""
//            OutlinedTextField(
//                value = char,
//                onValueChange = {
//                    if (it.length <= 1) {
//                        val newText = buildString {
//                            append(otpText.take(index))
//                            append(it)
//                            append(otpText.drop(index + 1))
//                        }
//                        onOtpTextChange(newText.take(charCount))
//                    }
//                },
//                modifier = Modifier
//                    .weight(1f)
//                    .height(56.dp),
//                singleLine = true,
//                textStyle = LocalTextStyle.current.copy(
//                    textAlign = TextAlign.Center,
//                    fontFamily = InterBold
//                ),
//                colors = OutlinedTextFieldDefaults.colors(
//                    focusedBorderColor = if (char.isNotEmpty()) Color(0xFF218203) else Color(0xFFDEDFE1),
//                    unfocusedBorderColor = Color(0xFFDEDFE1)
//                )
//            )
//        }
//    }
//}
//

package com.example.learnapp.ui.screens

import android.annotation.SuppressLint
import android.provider.Settings
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.apnamart.arrowlogx.R
import com.apnamart.arrowlogx.ui.theme.Inter
import com.apnamart.arrowlogx.ui.theme.InterBold
import com.apnamart.arrowlogx.ui.theme.InterSemiBold
import com.apnamart.arrowlogx.ui.viewmodel.OtpViewModel
import kotlinx.coroutines.delay

@SuppressLint("HardwareIds")
@Composable
fun OtpScreen(
    phone: String,
    onOtpVerified: (String) -> Unit,
    viewModel: OtpViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val otp by viewModel.otp.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Truck Logo",
            modifier = Modifier.size(160.dp),
            contentScale = ContentScale.FillBounds
        )

        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = "Enter OTP",
            style = MaterialTheme.typography.titleLarge,
            fontFamily = InterBold,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Please enter the OTP we have sent to your mobile number +91 $phone ✎",
            style = MaterialTheme.typography.bodyMedium,
            fontFamily = Inter,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(24.dp))

        OtpInputField(
            otpText = otp,
            onOtpTextChange = { viewModel.setOtp(it) }
        )

        Spacer(modifier = Modifier.height(16.dp))
        val timer = remember { mutableStateOf(30) }

        LaunchedEffect(Unit) {
            while (timer.value > 0) {
                delay(1000)
                timer.value -= 1
            }
        }

        Text(
            text = if (timer.value > 0) "Resend OTP in (${timer.value}s)" else "Resend OTP",
            style = MaterialTheme.typography.bodySmall,
            color = Color.Gray,
            modifier = Modifier.padding(vertical = 8.dp)
        )

        Spacer(modifier = Modifier.height(24.dp))

//        Button(
//            onClick = {
//                Log.d("OtpScreen", "Verify OTP clicked")
//                viewModel.verifyOtp(phone, otp, deviceId) { success, token ->
//                    if (success && token != null) {
//                        onOtpVerified(token)
//                    }
//                }
//            },
//            modifier = Modifier
//                .fillMaxWidth()
//                .height(50.dp),
//            shape = RoundedCornerShape(12.dp),
//            colors = ButtonDefaults.buttonColors(
//                containerColor = MaterialTheme.colorScheme.primary,
//                contentColor = MaterialTheme.colorScheme.onPrimary
//            )
//        ) {
//            Text(
//                text = "Continue",
//                fontFamily = InterSemiBold,
//                style = MaterialTheme.typography.bodyLarge
//            )
//        }
        Button(
            onClick = {
                // Bypass verification for now
                onOtpVerified("mockToken")
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary
            )
        ) {
            Text(
                text = "Continue",
                fontFamily = InterSemiBold,
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}
@Composable
fun OtpInputField(
    otpText: String,
    onOtpTextChange: (String) -> Unit,
    charCount: Int = 6
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier.fillMaxWidth(),
    ) {
        repeat(charCount) { index ->
            val char = otpText.getOrNull(index)?.toString() ?: ""
            OutlinedTextField(
                value = char,
                onValueChange = {
                    if (it.length <= 1) {
                        val newText = buildString {
                            append(otpText.take(index))
                            append(it)
                            append(otpText.drop(index + 1))
                        }
                        onOtpTextChange(newText.take(charCount))
                    }
                },
                modifier = Modifier
                    .weight(1f)
                    .height(56.dp),
                singleLine = true,
                textStyle = LocalTextStyle.current.copy(
                    textAlign = TextAlign.Center,
                    fontFamily = InterBold
                ),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = if (char.isNotEmpty()) Color(0xFF218203) else Color(0xFFDEDFE1),
                    unfocusedBorderColor = Color(0xFFDEDFE1)
                )
            )
        }
    }
}
