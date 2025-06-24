package com.example.learnapp.data.datasource

import com.example.learnapp.data.entity.LearnResponse
import retrofit2.Response

interface NewsDataSource {
    suspend fun getNewsHeadline(
        country: String,

    ) : Response<LearnResponse>
}