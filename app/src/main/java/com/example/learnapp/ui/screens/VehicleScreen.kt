package com.example.learnapp.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.learnapp.ui.viewmodel.VehicleViewModel


@Composable
fun VehicleScreen(
    token: String,
    warehouseId: Int,
    viewModel: VehicleViewModel = hiltViewModel(),
    onVehicleSelected: () -> Unit // Add this
) {
    val vehicles by viewModel.vehicles.collectAsState()
    val error by viewModel.errorMessage.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.fetchVehicles(token, warehouseId)
    }

    Column(Modifier.fillMaxSize().padding(16.dp)) {
        if (vehicles.isEmpty() && error.isEmpty()) {
            Text("Loading...")
        }

        if (error.isNotEmpty()) {
            Text("Error: $error")
        }

        LazyColumn {
            items(vehicles) { vehicle ->
                Column {
                    Text("Transporter: ${vehicle.name}")
                    Text("Vehicle Number: ${vehicle.number}")
                    Button(onClick = { onVehicleSelected() }) {
                        Text("Continue")
                    }
                    Divider(Modifier.padding(vertical = 8.dp))
                }
            }
        }
    }
}

