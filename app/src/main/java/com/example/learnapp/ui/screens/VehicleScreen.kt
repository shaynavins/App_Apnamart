package com.example.learnapp.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
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
    onVehicleSelected: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.fetchVehicles(token, warehouseId)
    }

    Column(Modifier.fillMaxSize().padding(16.dp)) {
        when {
            uiState.isLoading -> {
                Text("Loading...")
            }

            uiState.errorMessage.isNotEmpty() -> {
                Text("Error: ${uiState.errorMessage}", color = MaterialTheme.colorScheme.error)
            }

            uiState.vehicles.isEmpty() -> {
                Text("No vehicles available.")
            }

            else -> {
                LazyColumn {
                    items(uiState.vehicles) { vehicle ->
                        Column(Modifier.padding(vertical = 8.dp)) {
                            Text("Transporter: ${vehicle.name}")
                            Text("Vehicle Number: ${vehicle.number}")
                            Button(onClick = { onVehicleSelected() }) {
                                Text("Continue")
                            }
                            Divider(Modifier.padding(top = 8.dp))
                        }
                    }
                }
            }
        }
    }
}


