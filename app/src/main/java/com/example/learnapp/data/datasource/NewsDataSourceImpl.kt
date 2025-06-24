package com.example.learnapp.data.datasource

import com.example.learnapp.data.api.ApiService
import com.example.learnapp.data.entity.LearnResponse
import retrofit2.Response
import javax.inject.Inject

class NewsDataSourceImpl @Inject constructor(
    private val apiService: ApiService
): NewsDataSource {


    override suspend fun getNewsHeadline(country:String): Response<LearnResponse> {

        return apiService.getNewsHeadline(country)

    }


}