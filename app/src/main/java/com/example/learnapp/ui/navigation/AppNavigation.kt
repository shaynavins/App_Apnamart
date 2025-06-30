package com.example.learnapp.ui.components

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.learnapp.ui.navigation.Routes
import com.example.learnapp.ui.screens.HomeScreen
import com.example.learnapp.ui.screens.OtpScreen

@Composable
fun AppNavigationGraph() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Routes.OTP_SCREEN) {

        composable(Routes.OTP_SCREEN) {
            OtpScreen(
                onOtpVerified = {
                    navController.navigate(Routes.HOME_SCREEN) {
                        popUpTo(Routes.OTP_SCREEN) { inclusive = true }
                    }
                }
            )
        }

        composable(Routes.HOME_SCREEN) {
            HomeScreen()
        }
    }
}
