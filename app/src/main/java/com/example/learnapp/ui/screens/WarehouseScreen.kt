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
    val warehouses by viewModel.warehouses.collectAsState()
    val error by viewModel.errorMessage.collectAsState()
    val selectionMessage by viewModel.selectionMessage.collectAsState()

    // Fetch warehouses once on token change
    LaunchedEffect(token) {
        viewModel.fetchWarehouses(token)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        if (error.isNotEmpty()) {
            Text(
                text = "Error: $error",
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.padding(bottom = 16.dp)
            )
        }

        if (selectionMessage.isNotEmpty()) {
            Text(
                text = selectionMessage,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(bottom = 16.dp)
            )
        }

        LazyColumn {
            items(warehouses) { warehouse ->
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
