package com.apnamart.arrowlogx.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

import com.apnamart.arrowlogx.ui.navigation.Routes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter

import androidx.compose.ui.res.painterResource
import com.apnamart.arrowlogx.R

const val TAG = "HomeScreen"

//@Composable
//fun HomeScreen(
//    token: String,
//    warehouseId: Int,
//    navController: NavController
//) {
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(16.dp)
//    ) {
//        Text("Welcome to Home")
//        Text("Token: $token")
//        Text("Warehouse ID: $warehouseId")
//
//        Button(
//            onClick = {
//                navController.navigate("${Routes.PICKUP_STORE_SCREEN}/$token/$warehouseId")
//            },
//            modifier = Modifier.padding(top = 16.dp)
//        ) {
//            Text("Select Pickup Store")
//        }
//    }
//}
//
//@Preview(showBackground = true)
//@Composable
//fun HomeScreenPreview() {
//    val fakeNavController = TestNavHostController(LocalContext.current)
//    HomeScreen(
//        token = "sample-token",
//        warehouseId = 1,
//        navController = fakeNavController
//    )
//}
@Composable
fun HomeScreen(
    token: String,
    warehouseId: Int,
    vehicleNumber: String,

    navController: NavController
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("App Name", style = MaterialTheme.typography.titleLarge)

        Spacer(modifier = Modifier.height(16.dp))

        // Warehouse Display
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(8.dp))
                .background(Color(0xFFF9FAFB))
                .padding(horizontal = 16.dp, vertical = 12.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        painter = painterResource(id = R.drawable.warehouse),
                        contentDescription = "Warehouse",
                        tint = Color(0xFF6B7280),
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Warehouse ID: $warehouseId",
                        style = MaterialTheme.typography.titleMedium,
                        color = Color(0xFF374151)
                    )
                }
                Icon(
                    painter = painterResource(id = R.drawable.ic_round_keyboard_arrow_down_24),
                    contentDescription = "Dropdown",
                    tint = Color(0xFF6B7280),
                    modifier = Modifier.size(16.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Driver & Vehicle Card
        Surface(
            shape = RoundedCornerShape(16.dp),
            tonalElevation = 2.dp,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(Modifier.padding(16.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        painter = painterResource(id = R.drawable.vehicle),
                        contentDescription = "Driver",
                        modifier = Modifier
                            .size(48.dp)
                            .clip(CircleShape)
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Column {
                        Text(
                            text = "Driver ID: ",
                            style = MaterialTheme.typography.bodyLarge.copy(color = Color(0xFF0075FF))
                        )
                        Text("Vehicle No: $vehicleNumber", style = MaterialTheme.typography.bodySmall)
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Vehicle Row
                Surface(
                    shape = RoundedCornerShape(12.dp),
                    color = Color(0xFFF6F8FA),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier
                            .padding(12.dp)
                            .clickable { /* maybe open vehicle details */ },
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(painter = painterResource(id = R.drawable.vehicle), contentDescription = null)
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(vehicleNumber, style = MaterialTheme.typography.bodyMedium)
                        Spacer(modifier = Modifier.weight(1f))
                        Icon(imageVector = Icons.Default.KeyboardArrowRight, contentDescription = null)
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Action Buttons
        ActionRow(
            icon = painterResource(id = R.drawable.ic_carton),
            label = "Pick Cartons",
            onClick = {
                navController.navigate("${Routes.PICKUP_STORE_SCREEN}/$token/$warehouseId")
            }
        )

        ActionRow(
            icon = painterResource(id = R.drawable.vehicle),
            label = "Drop Cartons",
            onClick = {
                navController.navigate("${Routes.DROP_CARTON_SCREEN}/$token/$warehouseId")
            }
        )
    }
}


@Composable
fun ActionRow(
    icon: Painter,
    label: String,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp)
            .clickable { onClick() },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = icon,
            contentDescription = null,
            modifier = Modifier.size(24.dp),
            tint = Color(0xFF2C2C2E) // icon color
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = label,
            style = MaterialTheme.typography.titleMedium,
            color = Color(0xFF2C2C2E) // text color
        )
        Spacer(modifier = Modifier.weight(1f))
        Icon(
            imageVector = Icons.Default.KeyboardArrowRight,
            contentDescription = null,
            tint = Color(0xFF2C2C2E)
        )
    }
}
