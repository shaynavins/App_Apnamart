package com.example.learnapp.ui.repository

import com.example.learnapp.data.datasource.NewsDataSource
import com.example.learnapp.data.entity.LearnResponse
import com.example.utilities.ResourceState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import javax.inject.Inject

class LearnRepository @Inject constructor(
    private val newsDataSource: NewsDataSource
){

//    suspend fun getNewsHeadline(country:String): Response<LearnResponse>{
//
//        return newsDataSource.getNewsHeadline(country)
//
//    }

    suspend fun getNewsHeadline(country: String): Flow<ResourceState<LearnResponse>> {
        return flow<ResourceState<LearnResponse>> {
            emit(ResourceState.Loading)

            val response = newsDataSource.getNewsHeadline(country)
            if (response.isSuccessful && response.body() != null) {
                emit(ResourceState.Success(response.body()!!))
            } else {
                emit(ResourceState.Error("Error fetching data"))
            }
        }.catch { e ->
            emit(ResourceState.Error("Some error: ${e.localizedMessage}"))
        }
    }




}