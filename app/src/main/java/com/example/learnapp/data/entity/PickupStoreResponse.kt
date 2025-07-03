package com.example.learnapp.data.entity

data class StoreResponse(
    val data: List<Store>
)

data class Store(
    val storeId: Int,
    val storeName: String,
    val cartonCount: Int
)
