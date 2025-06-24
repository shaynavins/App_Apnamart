package com.example.learnapp.data.api

import com.example.learnapp.data.entity.LearnResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiService {

    //define functions for all endpoints(get, post, etc)

    @GET("v2/top-headlines")
    suspend fun getNewsHeadline(
        @Query("country") country:String,
        @Query("apiKey") apiKey:String = "e63e1f804cad4517ba67438955ca1e99"
    ) : Response<LearnResponse>

}

//GET https://newsapi.org/v2/top-headlines?country=us&apiKey=API_KEY