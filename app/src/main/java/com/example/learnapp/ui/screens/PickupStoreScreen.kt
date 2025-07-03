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
    val stores by viewModel.stores.collectAsState()
    val error by viewModel.errorMessage.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.fetchPickupStores(token, warehouseId)
    }

    Column(Modifier.fillMaxSize().padding(16.dp)) {
        if (error.isNotEmpty()) {
            Text("Error: $error", color = MaterialTheme.colorScheme.error)
        }

        LazyColumn {
            items(stores) { store ->
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
