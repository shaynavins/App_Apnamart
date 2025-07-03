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

@HiltViewModel
class PickupStoreViewModel @Inject constructor(
    private val apiService: PickupStoreApiService
) : ViewModel() {

    private val _stores = MutableStateFlow<List<Store>>(emptyList())
    val stores = _stores.asStateFlow()

    private val _errorMessage = MutableStateFlow("")
    val errorMessage = _errorMessage.asStateFlow()

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
                    _stores.value = response.body()?.data ?: emptyList()
                } else {
                    _errorMessage.value = "Error: ${response.code()} ${response.message()}"
                }
            } catch (e: Exception) {
                _errorMessage.value = "Exception: ${e.localizedMessage}"
            }
        }
    }
}
