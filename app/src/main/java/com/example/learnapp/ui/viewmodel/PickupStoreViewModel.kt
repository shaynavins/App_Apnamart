package com.example.learnapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.learnapp.data.api.PickupStoreApiService
import com.example.learnapp.data.entity.Store
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


data class PickupStoreUiState(
    val stores: List<Store> = emptyList(),
    val errorMessage: String = ""
)

@HiltViewModel
class PickupStoreViewModel @Inject constructor(
    private val apiService: PickupStoreApiService
) : ViewModel() {

    private val _uiState = MutableStateFlow(PickupStoreUiState())
    val uiState = _uiState.asStateFlow()

    fun fetchPickupStores(token: String, warehouseId: Int) {
        viewModelScope.launch {
            try {
                val response = apiService.getPickupStores(
                    warehouseId = warehouseId,
                    destinationType = "STR",
                    pageNo = 1,
                    limit = 20,
                    searchQuery = "",
                    token = "token $token"
                )

                if (response.isSuccessful) {
                    _uiState.value = _uiState.value.copy(
                        stores = response.body()?.data ?: emptyList(),
                        errorMessage = ""
                    )
                } else {
                    _uiState.value = _uiState.value.copy(
                        errorMessage = "Error: ${response.code()} ${response.message()}"
                    )
                }
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    errorMessage = "Exception: ${e.localizedMessage}"
                )
            }
        }
    }
}

