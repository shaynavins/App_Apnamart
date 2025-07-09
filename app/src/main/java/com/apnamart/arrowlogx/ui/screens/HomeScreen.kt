package com.apnamart.arrowlogx.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.testing.TestNavHostController
import androidx.compose.ui.platform.LocalContext
import com.example.learnapp.ui.navigation.Routes

const val TAG = "HomeScreen"

@Composable
fun HomeScreen(
    token: String,
    warehouseId: Int,
    navController: NavController
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("Welcome to Home")
        Text("Token: $token")
        Text("Warehouse ID: $warehouseId")

        Button(
            onClick = {
                navController.navigate("${Routes.PICKUP_STORE_SCREEN}/$token/$warehouseId")
            },
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text("Select Pickup Store")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    val fakeNavController = TestNavHostController(LocalContext.current)
    HomeScreen(
        token = "sample-token",
        warehouseId = 1,
        navController = fakeNavController
    )
}
