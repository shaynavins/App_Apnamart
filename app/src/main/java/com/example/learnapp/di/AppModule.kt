package com.example.learnapp.di

import com.example.learnapp.data.AppConstants
import com.example.learnapp.data.api.ApiService
import com.example.learnapp.data.datasource.NewsDataSource
import com.example.learnapp.data.datasource.NewsDataSourceImpl
import com.example.learnapp.ui.repository.LearnRepository
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

//dagger module to define third party dependencies
//all deps. created in this module should be accessible throughout project

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun providesRetrofit() : Retrofit {

        val httpLoggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BASIC
        }
        val httpClient = OkHttpClient().newBuilder().apply {
            addInterceptor(httpLoggingInterceptor)
        }

        httpClient.apply {
            readTimeout(60,TimeUnit.SECONDS)
        }

        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory()).build()
        //create httploggininterceptor to log api calls
        //create httpclient, create timeout
        //created a retrofit object that we can inject

        //inside datasource --> LearnDataSourceImpl class which calls @Inject method @Inject coonstructor(private val apiservice: ApiService)
        return Retrofit.Builder()
            .baseUrl(AppConstants.APP_BASE_URL)
            .client(httpClient.build())
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }

    @Singleton
    @Provides
    fun providesApiService(retrofit: Retrofit) :ApiService{
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideNewsDataSource(apiService: ApiService) : NewsDataSource {
        return NewsDataSourceImpl(apiService)
    }
    @Provides
    @Singleton
    fun providesLearnRepository(newsDataSource: NewsDataSource) : LearnRepository {
        return LearnRepository(newsDataSource)
    }


}