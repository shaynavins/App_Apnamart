package com.apnamart.arrowlogx.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.apnamart.arrowlogx.R
import com.apnamart.arrowlogx.ui.theme.InterMedium

import androidx.compose.runtime.LaunchedEffect
import kotlinx.coroutines.delay

@Composable
fun LandingScreen(navController: NavController) {

    LaunchedEffect(Unit) {
        delay(3000)
        navController.navigate("phone_number") {
            popUpTo("landing_screen") { inclusive = true }
        }
    }

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
                text = "Apnamart",
                color = MaterialTheme.colorScheme.primary, // uses value from LightColorScheme or DarkColorScheme
                fontFamily = InterMedium
            )
            Spacer(modifier = Modifier.height(24.dp))

        }

}

