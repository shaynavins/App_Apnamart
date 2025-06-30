package com.example.learnapp.data.api

import com.example.learnapp.data.entity.SendOtpRequest
import com.example.learnapp.data.entity.SendOtpResponse
import com.example.learnapp.data.entity.VerifyOtpResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface OtpApiService {
    @Headers("version: 2.15.0.0")
    @POST("send_phone_otp/")
    suspend fun sendOtp(@Body body: SendOtpRequest): Response<SendOtpResponse>

    @Headers("version: 2.15.0.0")
    @POST("verify_otp/")
    suspend fun verifyOtp(@Body body: Map<String, Any>): Response<VerifyOtpResponse>
}
