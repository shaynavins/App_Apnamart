package com.apnamart.arrowlogx.di

import com.apnamart.arrowlogx.data.AppConstants
import com.apnamart.arrowlogx.data.api.OtpApiService
import com.apnamart.arrowlogx.data.api.PickupStoreApiService
import com.apnamart.arrowlogx.data.api.SelectApiService
import com.apnamart.arrowlogx.data.api.VehicleApiService

import com.apnamart.arrowlogx.data.api.WarehouseApiService

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Singleton
import retrofit2.converter.gson.GsonConverterFactory


//dagger module to define third party dependencies
//all deps. created in this module should be accessible throughout project

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideOtpApi(retrofit: Retrofit): OtpApiService {
        return retrofit.create(OtpApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor { chain ->
                val request = chain.request().newBuilder()
                    .addHeader("Content-Type", "application/json")
                    .addHeader("version", "2.15.0.0")
                    .addHeader("User-Agent", "PostmanRuntime/7.44.1")
                    .addHeader("Accept", "*/*")
                    .addHeader("Accept-Encoding", "gzip, deflate, br")
                    .addHeader("Connection", "keep-alive")
                    .build()
                chain.proceed(request)
            }
            .build()
    }

    @Provides
    @Singleton
    fun providesRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(AppConstants.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


//    @Provides
//    @Singleton
//    fun providesRetrofit() : Retrofit {
//
//        val httpLoggingInterceptor = HttpLoggingInterceptor().apply {
//            level = HttpLoggingInterceptor.Level.BASIC
//        }
//        val httpClient = OkHttpClient().newBuilder().apply {
//            addInterceptor(httpLoggingInterceptor)
//        }
//
//        httpClient.apply {
//            readTimeout(60,TimeUnit.SECONDS)
//        }
//
//        val moshi = Moshi.Builder()
//            .add(KotlinJsonAdapterFactory()).build()
//        //create httploggininterceptor to log api calls
//        //create httpclient, create timeout
//        //created a retrofit object that we can inject
//
//        //inside datasource --> LearnDataSourceImpl class which calls @Inject method @Inject coonstructor(private val apiservice: ApiService)
//        return Retrofit.Builder()
//            .baseUrl(AppConstants.APP_BASE_URL)
//            .client(httpClient.build())
//            .addConverterFactory(MoshiConverterFactory.create(moshi))
//            .build()
//    }




    @Provides
    @Singleton
    fun provideWarehouseApiService(retrofit: Retrofit): WarehouseApiService {
        return retrofit.create(WarehouseApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideSelectApiService(retrofit: Retrofit): SelectApiService {
        return retrofit.create(SelectApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideVehicleApiService(retrofit: Retrofit): VehicleApiService {
        return retrofit.create(VehicleApiService::class.java)
    }

    @Singleton
    @Provides
    fun providePickupStoreApiService(retrofit: Retrofit): PickupStoreApiService {
        return retrofit.create(PickupStoreApiService::class.java)
    }






}