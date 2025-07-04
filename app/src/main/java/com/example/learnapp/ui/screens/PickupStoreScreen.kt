package com.example.learnapp.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.learnapp.ui.viewmodel.PickupStoreViewModel

@Composable
fun PickupStoreScreen(
    token: String,
    warehouseId: Int,
    onStoreSelected: (Int) -> Unit,
    viewModel: PickupStoreViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.fetchPickupStores(token, warehouseId)
    }

    Column(Modifier.fillMaxSize().padding(16.dp)) {
        if (uiState.errorMessage.isNotEmpty()) {
            Text("Error: ${uiState.errorMessage}", color = MaterialTheme.colorScheme.error)
        }

        LazyColumn {
            items(uiState.stores) { store ->
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onStoreSelected(store.storeId) }
                        .padding(vertical = 8.dp)
                ) {
                    Text("Store Name: ${store.storeName}")
                    Text("Cartons: ${store.cartonCount}")
                    Divider(modifier = Modifier.padding(vertical = 8.dp))
                }
            }
        }
    }
}

