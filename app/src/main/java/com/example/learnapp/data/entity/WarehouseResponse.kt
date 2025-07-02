package com.example.learnapp.data.entity

data class Warehouse(
    val id: Int,
    val name: String,
    val state: String,
    val city: String,
    val address: String,
    val pin: String
)

data class SelectWarehouseRequest(
    val wh_id: Int
)

data class SelectWarehouseResponse(
    val message: String
)

