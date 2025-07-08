package com.apnamart.arrowlogx.data.entity

import com.google.gson.annotations.SerializedName

data class StoreResponse(
    @SerializedName("data")
    val data: List<Store>
)

data class Store(
    @SerializedName("store_id")
    val storeId: Int,

    @SerializedName("store_name")
    val storeName: String,

    @SerializedName("carton_count")
    val cartonCount: Int
)
