package com.example.learnapp.data.api

import com.example.learnapp.data.entity.Warehouse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface WarehouseApiService {
    @GET("wms/get_available_warehouses/")
    suspend fun getWarehouses(
        @Header("Authorization") authHeader: String,
        @Header("version") version: String = "2.15.0.0",
        @Header("User-Agent") userAgent: String = "PostmanRuntime/7.44.1",
        @Header("Accept") accept: String = "*/*",
        @Header("Accept-Encoding") encoding: String = "gzip, deflate, br",
        @Header("Content-Type") contentType: String = "application/json"
    ): Response<List<Warehouse>>
}
