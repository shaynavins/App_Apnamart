// VehicleApiService.kt
package com.example.learnapp.data.api

import com.example.learnapp.data.entity.Vehicle
import com.example.learnapp.data.entity.VehicleResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface VehicleApiService {
    @GET("wms/list_warehouse_vehicles/")
    suspend fun getVehicles(
        @Query("list_type") listType: String = "detail",
        @Query("warehouse_id") warehouseId: Int,
        @Header("Authorization") token: String,
        @Header("version") version: String = "2.15.0.0",
        @Header("warehouse-id") warehouseHeader: Int
    ): Response<VehicleResponse>
}