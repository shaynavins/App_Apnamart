package com.example.learnapp.data.api

import com.example.learnapp.data.entity.StoreResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface PickupStoreApiService {
    @GET("/api/outward/list_reusable_carton_at_stores/")
    suspend fun getPickupStores(
        @Query("warehouse_id") warehouseId: Int,
        @Query("destination_type") destinationType: String,
        @Query("page_no") pageNo: Int,
        @Query("limit") limit: Int,
        @Query("search_query") searchQuery: String,
        @Header("Authorization") token: String
    ): Response<StoreResponse>
}
