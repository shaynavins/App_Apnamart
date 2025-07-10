package com.apnamart.arrowlogx.data.api

import com.apnamart.arrowlogx.data.entity.Warehouse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface WarehouseApiService {
    @GET("wms/get_available_warehouses/")
    suspend fun getWarehouses(
        @Header("Authorization") authHeader: String = "token 124d6f6988b880c73f5f590a2d694dd96c9636bec2dc533c1f8e3f0a788989b5",
        @Header("version") version: String = "2.15.0.0",
        @Header("User-Agent") userAgent: String = "PostmanRuntime/7.44.1",
        @Header("Accept") accept: String = "*/*",
        @Header("Accept-Encoding") encoding: String = "gzip, deflate, br",
        @Header("Content-Type") contentType: String = "application/json"
    ): Response<List<Warehouse>>
}
