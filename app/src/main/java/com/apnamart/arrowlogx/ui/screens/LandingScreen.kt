package com.apnamart.arrowlogx.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.apnamart.arrowlogx.ui.theme.InterMedium
import com.example.learnapp.ui.navigation.Routes
import com.apnamart.arrowlogx.ui.theme.LearnAppTheme

@Composable
fun LandingScreen(onContinue: () -> Unit) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(32.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Welcome",
                color = MaterialTheme.colorScheme.primary, // uses value from LightColorScheme or DarkColorScheme
                fontFamily = InterMedium
            )
            Spacer(modifier = Modifier.height(24.dp))
            Button(onClick = onContinue) {
                Text("Continue")
            }
        }

}

