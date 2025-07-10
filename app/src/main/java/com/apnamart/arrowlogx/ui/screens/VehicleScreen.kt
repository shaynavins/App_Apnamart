//package com.example.learnapp.ui.screens
//
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.layout.size
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.foundation.lazy.items
//import androidx.compose.material3.Button
//import androidx.compose.material3.Divider
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.LaunchedEffect
//import androidx.compose.runtime.collectAsState
//import androidx.compose.runtime.getValue
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.layout.ContentScale
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.unit.dp
//import androidx.hilt.navigation.compose.hiltViewModel
//import com.apnamart.arrowlogx.R
//import com.apnamart.arrowlogx.ui.theme.Inter
//import com.apnamart.arrowlogx.ui.theme.InterBold
//import com.apnamart.arrowlogx.ui.viewmodel.VehicleViewModel
//
//
//@Composable
//fun VehicleScreen(
//    token: String,
//    warehouseId: Int,
//    viewModel: VehicleViewModel = hiltViewModel(),
//    onVehicleSelected: () -> Unit
//) {
//    val uiState by viewModel.uiState.collectAsState()
//
//     val hardcodedToken = "124d6f6988b880c73f5f590a2d694dd96c9636bec2dc533c1f8e3f0a788989b5"
//
//
//    LaunchedEffect(Unit) {
//        viewModel.fetchVehicles(hardcodedToken, warehouseId)
//    }
//
//    Column(Modifier.fillMaxSize().padding(16.dp)) {
//        Image(
//            painter = painterResource(id = R.drawable.vehicle),
//            contentDescription = "vehicle Logo",
//            modifier = Modifier.size(90.dp),
//            contentScale = ContentScale.FillBounds
//        )
//        Spacer(modifier = Modifier.height(24.dp))
//        Text("Select Vehicle", style = MaterialTheme.typography.titleLarge.copy(fontFamily = InterBold), fontFamily = InterBold)
//        Text("Select the vehicle to dispatch goods", style = MaterialTheme.typography.bodySmall, fontFamily = Inter)
//
//        Spacer(modifier = Modifier.height(16.dp))
//
//        when {
//            uiState.isLoading -> {
//                Text("Loading...")
//            }
//
//            uiState.errorMessage.isNotEmpty() -> {
//                Text("Error: ${uiState.errorMessage}", color = MaterialTheme.colorScheme.error)
//            }
//
//            uiState.vehicles.isEmpty() -> {
//                Text("No vehicles available.")
//            }
//
//            else -> {
//                LazyColumn {
//                    items(uiState.vehicles) { vehicle ->
//                        Column(Modifier.padding(vertical = 8.dp)) {
//                            Text("Transporter: ${vehicle.name}")
//                            Text("Vehicle Number: ${vehicle.number}")
//                            Button(onClick = { onVehicleSelected() }) {
//                                Text("Continue")
//                            }
//                            Divider(Modifier.padding(top = 8.dp))
//                        }
//                    }
//                }
//            }
//        }
//    }
//}
//
//
package com.apnamart.arrowlogx.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.apnamart.arrowlogx.R
import com.apnamart.arrowlogx.ui.theme.Inter
import com.apnamart.arrowlogx.ui.theme.InterBold
import com.apnamart.arrowlogx.ui.theme.InterSemiBold
import com.apnamart.arrowlogx.ui.viewmodel.VehicleViewModel
@Composable
fun VehicleScreen(
    warehouseId: Int,
    viewModel: VehicleViewModel = hiltViewModel(),
    onVehicleSelected: (vehicleNumber: String) -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()
    val hardcodedToken = "124d6f6988b880c73f5f590a2d694dd96c9636bec2dc533c1f8e3f0a788989b5"
    var selectedVehicleId by remember { mutableStateOf<Int?>(null) }

    LaunchedEffect(Unit) {
        viewModel.fetchVehicles(hardcodedToken, warehouseId)
    }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Image(
            painter = painterResource(id = R.drawable.vehicle),
            contentDescription = "Vehicle Logo",
            modifier = Modifier.size(90.dp),
            contentScale = ContentScale.FillBounds
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text("Select Vehicle", style = MaterialTheme.typography.titleLarge.copy(fontFamily = InterBold))
        Text("Select the vehicle to dispatch goods", style = MaterialTheme.typography.bodySmall.copy(fontFamily = Inter))
        Spacer(modifier = Modifier.height(16.dp))

        when {
            uiState.isLoading -> Text("Loading...")
            uiState.errorMessage.isNotEmpty() -> Text("Error: ${uiState.errorMessage}", color = MaterialTheme.colorScheme.error)
            uiState.vehicles.isEmpty() -> Text("No vehicles available.")
            else -> {
                LazyColumn {
                    items(uiState.vehicles) { vehicle ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 8.dp)
                                .border(BorderStroke(1.dp, Color(0xFFDDDDDD)), shape = RoundedCornerShape(12.dp))
                                .padding(16.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Column {
                                Text(vehicle.number, style = MaterialTheme.typography.titleMedium.copy(fontFamily = Inter))
                                Text("Transporter: ${vehicle.name}", style = MaterialTheme.typography.bodySmall.copy(fontFamily = Inter))
                            }

                            RadioButton(
                                selected = selectedVehicleId == vehicle.id,
                                onClick = {
                                    selectedVehicleId = vehicle.id
                                    viewModel.selectVehicle(vehicle.id)
                                },
                                colors = RadioButtonDefaults.colors(selectedColor = MaterialTheme.colorScheme.primary)
                            )
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        val selectedVehicle = uiState.vehicles.find { it.id == selectedVehicleId }

        OutlinedButton(
            onClick = {
                if (selectedVehicle != null) {
                    onVehicleSelected(selectedVehicle.number)
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            shape = RoundedCornerShape(12.dp),
            border = BorderStroke(1.dp, Color(0xFFDEDFE1)),
            colors = ButtonDefaults.outlinedButtonColors(
                containerColor = Color.Transparent,
                contentColor = MaterialTheme.colorScheme.primary
            )
        ) {
            Text("Continue", style = MaterialTheme.typography.bodyLarge.copy(fontFamily = InterBold))
        }
    }
}

