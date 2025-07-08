package com.apnamart.arrowlogx.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.apnamart.arrowlogx.data.api.VehicleApiService
import com.apnamart.arrowlogx.data.entity.Vehicle
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

data class VehicleUiState(
    val vehicles: List<Vehicle> = emptyList(),
    val errorMessage: String = "",
    val isLoading: Boolean = false
)
@HiltViewModel
class VehicleViewModel @Inject constructor(
    private val vehicleApiService: VehicleApiService
): ViewModel() {

    private val _uiState = MutableStateFlow(VehicleUiState())
    val uiState = _uiState.asStateFlow()

    fun fetchVehicles(token: String, warehouseId: Int) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, errorMessage = "")

            try {
                val response = vehicleApiService.getVehicles(
                    listType = "detail",
                    warehouseId = warehouseId,
                    token = "token $token",
                    warehouseHeader = warehouseId
                )
                if (response.isSuccessful) {
                    _uiState.value = _uiState.value.copy(
                        vehicles = response.body()?.data ?: emptyList(),
                        isLoading = false,
                        errorMessage = ""
                    )
                } else {
                    _uiState.value = _uiState.value.copy(
                        errorMessage = "Error: ${response.code()} - ${response.message()}",
                        isLoading = false
                    )
                }

            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    errorMessage = "Exception: ${e.message}",
                    isLoading = false
                )
            }
        }
    }
}
