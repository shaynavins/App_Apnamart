package com.example.learnapp.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.learnapp.data.api.SelectApiService
import com.example.learnapp.data.api.WarehouseApiService
import com.example.learnapp.data.entity.SelectWarehouseRequest
import com.example.learnapp.data.entity.Warehouse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WarehouseViewModel @Inject constructor(
    private val warehouseApi: WarehouseApiService,
    private val selectApi: SelectApiService
) : ViewModel() {

    private val _warehouses = MutableStateFlow<List<Warehouse>>(emptyList())
    val warehouses: StateFlow<List<Warehouse>> get() = _warehouses

    private val _errorMessage = MutableStateFlow("")
    val errorMessage: StateFlow<String> get() = _errorMessage

    private val _selectionMessage = MutableStateFlow("")
    val selectionMessage: StateFlow<String> get() = _selectionMessage

    fun fetchWarehouses(token: String) {
        viewModelScope.launch {
            try {
                Log.d("WarehouseViewModel", "Fetching warehouses with token: token $token")

                val response = warehouseApi.getWarehouses("token $token")
                if (response.isSuccessful && response.body() != null) {
                    _warehouses.value = response.body()!!
                    Log.d("WarehouseViewModel", "Warehouses fetched: ${_warehouses.value.size}")
                } else {
                    _errorMessage.value = "API Error ${response.code()}: ${response.message()}"
                    Log.e("WarehouseViewModel", _errorMessage.value)
                }
            } catch (e: Exception) {
                _errorMessage.value = "Exception: ${e.message ?: "Unknown error"}"
                Log.e("WarehouseViewModel", "Exception occurred: ${e.message}", e)
            }
        }
    }


    fun selectWarehouse(token: String, whId: Int) {
        viewModelScope.launch {
            try {
                val response = selectApi.selectWarehouse(
                    token = "token $token",
                    request = SelectWarehouseRequest(wh_id = whId)
                )
                if (response.isSuccessful) {
                    _selectionMessage.value = response.body()?.message ?: "No message"
                    Log.d("WarehouseViewModel", "Warehouse selected: ${_selectionMessage.value}")
                } else {
                    _selectionMessage.value = "Error: ${response.code()} - ${response.message()}"
                    Log.e("WarehouseViewModel", _selectionMessage.value)
                }
            } catch (e: Exception) {
                _selectionMessage.value = "Exception: ${e.localizedMessage}"
                Log.e("WarehouseViewModel", "Exception occurred: ${e.message}", e)
            }
        }
    }
}
