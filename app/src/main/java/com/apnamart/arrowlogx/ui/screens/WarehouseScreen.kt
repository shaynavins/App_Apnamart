//package com.apnamart.arrowlogx.ui.screens
//
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.foundation.lazy.items
//import androidx.compose.material3.*
//import androidx.compose.runtime.*
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.unit.dp
//import androidx.hilt.navigation.compose.hiltViewModel
//import com.apnamart.arrowlogx.ui.viewmodel.WarehouseViewModel
//
//@Composable
//fun WarehouseScreen(
//    token: String,
//    viewModel: WarehouseViewModel = hiltViewModel(),
//    onWarehouseSelected: (Int) -> Unit,
//) {
//    val uiState by viewModel.uiState.collectAsState()
//
////    LaunchedEffect(token) {
////        viewModel.fetchWarehouses(token)
////    }
//
//    LaunchedEffect(Unit) {
//        viewModel.fetchWarehouses()
//    }
//
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(16.dp)
//    ) {
//        when {
//            uiState.isLoading -> {
//                Text("Loading...")
//            }
//
//            uiState.errorMessage.isNotEmpty() -> {
//                Text(
//                    text = "Error: ${uiState.errorMessage}",
//                    color = MaterialTheme.colorScheme.error,
//                    modifier = Modifier.padding(bottom = 16.dp)
//                )
//            }
//
//            uiState.selectionMessage.isNotEmpty() -> {
//                Text(
//                    text = uiState.selectionMessage,
//                    color = MaterialTheme.colorScheme.primary,
//                    modifier = Modifier.padding(bottom = 16.dp)
//                )
//            }
//        }
//
//        LazyColumn {
//            items(uiState.warehouses) { warehouse ->
//                Column(modifier = Modifier.padding(vertical = 8.dp)) {
//                    Text("Name: ${warehouse.name}", style = MaterialTheme.typography.bodyLarge)
//                    Text("City: ${warehouse.city}")
//                    Text("State: ${warehouse.state}")
//                    Text("Address: ${warehouse.address}")
//
//                    Button(
//                        onClick = {
//                            viewModel.selectWarehouse(token, warehouse.id)
//                            onWarehouseSelected(warehouse.id)
//                        },
//                        modifier = Modifier.padding(top = 8.dp)
//                    ) {
//                        Text("Select Warehouse")
//                    }
//
//                    Divider(modifier = Modifier.padding(vertical = 8.dp))
//                }
//            }
//        }
//    }
//}
//
package com.apnamart.arrowlogx.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.apnamart.arrowlogx.ui.viewmodel.WarehouseViewModel
import androidx.hilt.navigation.compose.hiltViewModel
import com.apnamart.arrowlogx.R
import com.apnamart.arrowlogx.data.entity.Warehouse
import com.apnamart.arrowlogx.ui.theme.Inter
import com.apnamart.arrowlogx.ui.theme.InterBold
import com.apnamart.arrowlogx.ui.theme.InterSemiBold

@Composable
fun WarehouseScreen(
    token: String,
    viewModel: WarehouseViewModel = hiltViewModel(),
    onWarehouseSelected: (Int) -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()
    var selectedWarehouseId by remember { mutableStateOf<Int?>(null) }

//    LaunchedEffect(Unit) {
//        viewModel.fetchWarehouses(token)
//    }

    LaunchedEffect(Unit) {
        viewModel.fetchWarehouses()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(Icons.Default.ArrowBack, contentDescription = "Back")
            Spacer(modifier = Modifier.width(8.dp))
            Text("Warehouse", style = MaterialTheme.typography.titleLarge, fontFamily = InterBold)
        }

        Spacer(modifier = Modifier.height(24.dp))

        Image(
            painter = painterResource(id = R.drawable.warehouse),
            contentDescription = "warehouse Logo",
            modifier = Modifier.size(90.dp),
            contentScale = ContentScale.FillBounds
        )
        Spacer(modifier = Modifier.height(24.dp))

        Text("Select Warehouse", style = MaterialTheme.typography.titleLarge.copy(fontFamily = InterBold), fontFamily = InterBold)
        Text("Select the vehicle to dispatch goods", style = MaterialTheme.typography.bodySmall, fontFamily = Inter)

        Spacer(modifier = Modifier.height(16.dp))

        when {
            uiState.isLoading -> {
                CircularProgressIndicator()
            }

            uiState.errorMessage.isNotEmpty() -> {
                Text(
                    text = "Error: ${uiState.errorMessage}",
                    color = MaterialTheme.colorScheme.error
                )
            }

            else -> {
                LazyColumn(modifier = Modifier.weight(1f)) {
                    items(uiState.warehouses) { warehouse ->
                        WarehouseCard(
                            warehouse = warehouse,
                            isSelected = warehouse.id == selectedWarehouseId,
                            onClick = {
                                selectedWarehouseId = warehouse.id
                                viewModel.selectWarehouse(token, warehouse.id)
                            }

                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Continue Button
                Button(
                    onClick = {
                        selectedWarehouseId?.let { onWarehouseSelected(it) }
                    },
                    enabled = selectedWarehouseId != null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.outlinedButtonColors(
                        containerColor = Color.Transparent, // No fill
                        contentColor = Color.Gray           // Text color
                    ),
                    border = BorderStroke(1.dp, Color(0xFFA3ACBA))
                ) {
                    Text("Continue", fontFamily = InterSemiBold)
                }
            }
        }
    }
}


@Composable
fun WarehouseCard(
    warehouse: Warehouse,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    OutlinedButton(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .height(100.dp),
        shape = RoundedCornerShape(12.dp),
        colors = ButtonDefaults.outlinedButtonColors(
            containerColor = Color.Transparent,
            contentColor = MaterialTheme.colorScheme.onSurface
        ),
        border = BorderStroke(
            width = 1.dp,
            color = if (isSelected) MaterialTheme.colorScheme.primary else Color(0xFFDEDFE1)
        )
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = warehouse.name,
                style = MaterialTheme.typography.titleMedium,
                fontFamily = InterBold
            )
            Divider(Modifier.padding(top = 8.dp))
            Text(
                text = "${warehouse.city}, ${warehouse.state}",
                style = MaterialTheme.typography.bodyMedium,
                fontFamily = Inter
            )
            Text(
                text = warehouse.address,
                style = MaterialTheme.typography.bodySmall,
                fontFamily = Inter,
                maxLines = 1
            )
            RadioButton(selected = isSelected, onClick = onClick)

        }
    }


}

