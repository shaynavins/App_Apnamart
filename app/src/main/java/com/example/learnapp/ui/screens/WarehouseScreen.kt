package com.example.learnapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.learnapp.ui.viewmodel.WarehouseViewModel

@Composable
fun WarehouseScreen(
    token: String,
    viewModel: WarehouseViewModel = hiltViewModel(),
    onWarehouseSelected: (Int) -> Unit,
) {
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(token) {
        viewModel.fetchWarehouses(token)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        when {
            uiState.isLoading -> {
                Text("Loading...")
            }

            uiState.errorMessage.isNotEmpty() -> {
                Text(
                    text = "Error: ${uiState.errorMessage}",
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
            }

            uiState.selectionMessage.isNotEmpty() -> {
                Text(
                    text = uiState.selectionMessage,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
            }
        }

        LazyColumn {
            items(uiState.warehouses) { warehouse ->
                Column(modifier = Modifier.padding(vertical = 8.dp)) {
                    Text("Name: ${warehouse.name}", style = MaterialTheme.typography.bodyLarge)
                    Text("City: ${warehouse.city}")
                    Text("State: ${warehouse.state}")
                    Text("Address: ${warehouse.address}")

                    Button(
                        onClick = {
                            viewModel.selectWarehouse(token, warehouse.id)
                            onWarehouseSelected(warehouse.id)
                        },
                        modifier = Modifier.padding(top = 8.dp)
                    ) {
                        Text("Select Warehouse")
                    }

                    Divider(modifier = Modifier.padding(vertical = 8.dp))
                }
            }
        }
    }
}

