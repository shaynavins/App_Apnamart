package com.apnamart.arrowlogx.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.apnamart.arrowlogx.R
import com.apnamart.arrowlogx.ui.theme.Inter
import com.apnamart.arrowlogx.ui.theme.InterBold
import com.apnamart.arrowlogx.ui.theme.InterMedium
import com.apnamart.arrowlogx.ui.theme.InterSemiBold
import com.apnamart.arrowlogx.ui.viewmodel.OtpViewModel
import androidx.compose.ui.Alignment

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
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = null,
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .size(120.dp) // uniform square frame
                .align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(30.dp))

        Text (
            text ="Login",
            color = MaterialTheme.colorScheme.primary, // uses value from LightColorScheme or DarkColorScheme
            fontFamily = InterBold
        )

        Text (
            text ="Enter your mobile number to proceed",
            color = MaterialTheme.colorScheme.primary, // uses value from LightColorScheme or DarkColorScheme
            fontFamily = Inter
        )

        OutlinedTextField(
            value = phone,
            onValueChange = { viewModel.setPhone(it) },
            label = { Text("Phone Number") },
            modifier = Modifier.fillMaxWidth(),
                    leadingIcon = {
                Text(
                    text = "+91 |",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(start = 4.dp)
                )
            },
            singleLine = true
        )


        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                viewModel.sendOtp(phone)
                onPhoneEntered(phone)
            },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            contentPadding = PaddingValues(0.dp),
            elevation = ButtonDefaults.buttonElevation(0.dp),
            
        ) {
            Text(
                text = "Continue",
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        }

    }
}

