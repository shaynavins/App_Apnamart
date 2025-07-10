package com.apnamart.arrowlogx.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apnamart.arrowlogx.data.api.SelectApiService
import com.apnamart.arrowlogx.data.api.WarehouseApiService
import com.apnamart.arrowlogx.data.entity.SelectWarehouseRequest
import com.apnamart.arrowlogx.data.entity.Warehouse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class WarehouseUiState(
    val warehouses: List<Warehouse> = emptyList(),
    val errorMessage: String = "",
    val selectionMessage: String = "",
    val isLoading: Boolean = false
)
@HiltViewModel
class WarehouseViewModel @Inject constructor(
    private val warehouseApi: WarehouseApiService,
    private val selectApi: SelectApiService
) : ViewModel() {
    private val hardcodedToken = "124d6f6988b880c73f5f590a2d694dd96c9636bec2dc533c1f8e3f0a788989b5"

    private val _uiState = MutableStateFlow(WarehouseUiState())
    val uiState: StateFlow<WarehouseUiState> = _uiState

//    fun fetchWarehouses(token: String) {
    fun fetchWarehouses() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, errorMessage = "", selectionMessage = "")
            try {
                val response = warehouseApi.getWarehouses("token $hardcodedToken")
                if (response.isSuccessful && response.body() != null) {
                    _uiState.value = _uiState.value.copy(
                        warehouses = response.body()!!,
                        isLoading = false
                    )
                } else {
                    _uiState.value = _uiState.value.copy(
                        errorMessage = "API Error ${response.code()}: ${response.message()}",
                        isLoading = false
                    )
                }
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    errorMessage = "Exception: ${e.message ?: "Unknown error"}",
                    isLoading = false
                )
            }
        }
    }

    fun selectWarehouse(token: String, whId: Int) {
        viewModelScope.launch {
            try {
                val response = selectApi.selectWarehouse(
                    token = "token $token",
                    request = SelectWarehouseRequest(whId = whId)
                )
                if (response.isSuccessful) {
                    _uiState.value = _uiState.value.copy(
                        selectionMessage = response.body()?.message ?: "No message"
                    )
                } else {
                    _uiState.value = _uiState.value.copy(
                        selectionMessage = "Error: ${response.code()} - ${response.message()}"
                    )
                }
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    selectionMessage = "Exception: ${e.localizedMessage}"
                )
            }
        }
    }
}

