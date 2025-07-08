package com.apnamart.arrowlogx.data.entity

import com.google.gson.annotations.SerializedName

data class Warehouse(
    @SerializedName("id")
    val id: Int,

    @SerializedName("name")
    val name: String,

    @SerializedName("state")
    val state: String,

    @SerializedName("city")
    val city: String,

    @SerializedName("address")
    val address: String,

    @SerializedName("pin")
    val pin: String
)

data class SelectWarehouseRequest(
    @SerializedName("wh_id")
    val whId: Int
)

data class SelectWarehouseResponse(
    @SerializedName("message")
    val message: String
)
