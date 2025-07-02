package com.example.learnapp.data.entity

import com.google.gson.annotations.SerializedName


data class Vehicle(
    @SerializedName("vehicle_id") val id: Int,
    @SerializedName("transporter_name") val name: String,
    @SerializedName("vehicle_number") val number: String,
    val type: String = "" // optional fallback
)


data class VehicleResponse(
    @SerializedName("data")
    val data: List<Vehicle>
)