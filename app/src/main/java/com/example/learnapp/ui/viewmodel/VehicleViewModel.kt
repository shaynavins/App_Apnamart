package com.example.learnapp.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.learnapp.data.api.VehicleApiService
import com.example.learnapp.data.entity.Vehicle
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch


@HiltViewModel
class VehicleViewModel @Inject constructor(
    private val vehicleApiService: VehicleApiService
): ViewModel() {
    private val _vehicles = MutableStateFlow<List<Vehicle>> (emptyList())
    val vehicles = _vehicles.asStateFlow()

    private val _errorMessage = MutableStateFlow("")
    val errorMessage = _errorMessage.asStateFlow()

    fun fetchVehicles(token: String, warehouseId: Int) {
        viewModelScope.launch {
            try {
                Log.d("VehicleAPI", "Calling getVehicles with:")
                Log.d("VehicleAPI", "Token: token $token")
                Log.d("VehicleAPI", "Warehouse ID: $warehouseId")

                val response = vehicleApiService.getVehicles(
                    listType = "detail",
                    warehouseId = warehouseId,
                    token = "token $token",
                    warehouseHeader = warehouseId
                )
                if (response.isSuccessful) {
                    _vehicles.value = response.body()?.data ?: emptyList()
                } else {
                    _errorMessage.value = "Error: ${response.code()} - ${response.message()}"
                }

            } catch (e: Exception) {
                _errorMessage.value = "Exception: ${e.message}"
            }
        }
    }



}