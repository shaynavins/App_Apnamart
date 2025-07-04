package com.example.learnapp.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.learnapp.ui.navigation.Routes
import com.example.learnapp.ui.screens.HomeScreen
import com.example.learnapp.ui.screens.OtpScreen
import com.example.learnapp.ui.screens.PickupStoreScreen
import com.example.learnapp.ui.screens.VehicleScreen
import com.example.learnapp.ui.screens.WarehouseScreen
import com.example.learnapp.ui.screens.LandingScreen
import com.example.learnapp.ui.screens.PhoneNumberScreen

@Composable
fun AppNavigationGraph() {
    val navController = rememberNavController()
    val useDevToken = true
    val devToken = "0da9f94cb4f29a8449b3c901373914482239b1ce3bf72d145751c5550e929f0d"

    NavHost(
        navController = navController,
        startDestination = Routes.LANDING_SCREEN
    ) {
        composable(
            route = "${Routes.OTP_SCREEN}/{phone}",
            arguments = listOf(navArgument("phone") { type = NavType.StringType })
        ) { backStackEntry ->
            val phone = backStackEntry.arguments?.getString("phone") ?: ""
            OtpScreen(
                phone = phone,
                onOtpVerified = { token ->
                    navController.navigate("${Routes.WAREHOUSE_SCREEN}/$token")
                }
            )
        }

        // Dev token shortcut (redirect immediately)
        composable(Routes.WAREHOUSE_SCREEN) {
            LaunchedEffect(Unit) {
                navController.navigate("${Routes.WAREHOUSE_SCREEN}/$devToken") {
                    popUpTo(Routes.WAREHOUSE_SCREEN) { inclusive = true }
                }
            }
        }

        composable(
            route = "${Routes.WAREHOUSE_SCREEN}/{token}",
            arguments = listOf(navArgument("token") { type = NavType.StringType })
        ) { backStackEntry ->
            val token = backStackEntry.arguments?.getString("token") ?: ""
            WarehouseScreen(
                token = token,
                onWarehouseSelected = { warehouseId ->
                    navController.navigate("${Routes.VEHICLE_SCREEN}/$token/$warehouseId")
                }
            )
        }

        composable(
            route = "${Routes.VEHICLE_SCREEN}/{token}/{warehouseId}",
            arguments = listOf(
                navArgument("token") { type = NavType.StringType },
                navArgument("warehouseId") { type = NavType.IntType }
            )
        ) { backStackEntry ->
            val token = backStackEntry.arguments?.getString("token") ?: ""
            val warehouseId = backStackEntry.arguments?.getInt("warehouseId") ?: 0
            VehicleScreen(
                token = token,
                warehouseId = warehouseId,
                onVehicleSelected = {
                    navController.navigate("${Routes.HOME_SCREEN}/$token/$warehouseId")
                }
            )
        }

        composable(
            route = "${Routes.HOME_SCREEN}/{token}/{warehouseId}",
            arguments = listOf(
                navArgument("token") { type = NavType.StringType },
                navArgument("warehouseId") { type = NavType.IntType }
            )
        ) { backStackEntry ->
            val token = backStackEntry.arguments?.getString("token") ?: ""
            val warehouseId = backStackEntry.arguments?.getInt("warehouseId") ?: 0
            HomeScreen(
                token = token,
                warehouseId = warehouseId,
                navController = navController // ✅ pass navController here
            )
        }

        composable(
            route = "${Routes.PICKUP_STORE_SCREEN}/{token}/{warehouseId}",
            arguments = listOf(
                navArgument("token") { type = NavType.StringType },
                navArgument("warehouseId") { type = NavType.IntType }
            )
        ) { backStackEntry ->
            val token = backStackEntry.arguments?.getString("token") ?: ""
            val warehouseId = backStackEntry.arguments?.getInt("warehouseId") ?: 0

            PickupStoreScreen(
                token = token,
                warehouseId = warehouseId,
                onStoreSelected = { storeId ->
                    // Handle store selection logic (e.g., navigate or show details)
                }
            )
        }
        composable(Routes.LANDING_SCREEN) {
            LandingScreen(
                onContinue = {
                    navController.navigate(Routes.PHONE_NUMBER_SCREEN)
                }
            )
        }

        composable(Routes.PHONE_NUMBER_SCREEN) {
            PhoneNumberScreen(
                onPhoneEntered = { phone ->
                    // Optionally: save phone to ViewModel/state
                    navController.navigate("${Routes.OTP_SCREEN}/$phone")
                }
            )
        }





    }
}
