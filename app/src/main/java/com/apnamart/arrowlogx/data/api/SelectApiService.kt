package com.apnamart.arrowlogx.data.api

import com.apnamart.arrowlogx.data.entity.SelectWarehouseRequest
import com.apnamart.arrowlogx.data.entity.SelectWarehouseResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST


interface SelectApiService {
    @POST("wms/select_warehouse/")
    suspend fun selectWarehouse(
        @Header("Authorization") token: String,
        @Body request: SelectWarehouseRequest
    ): Response<SelectWarehouseResponse>
}
