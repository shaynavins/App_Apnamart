package com.apnamart.arrowlogx.data.api

import com.apnamart.arrowlogx.data.entity.SendOtpRequest
import com.apnamart.arrowlogx.data.entity.SendOtpResponse
import com.apnamart.arrowlogx.data.entity.VerifyOtpRequest
import com.apnamart.arrowlogx.data.entity.VerifyOtpResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface OtpApiService {
    @Headers("version: 2.15.0.0")
    @POST("profile/send_phone_otp/")
    suspend fun sendOtp(@Body body: SendOtpRequest): Response<SendOtpResponse>

    @Headers("version: 2.15.0.0")
    @POST("profile/verify_otp/")
    suspend fun verifyOtp(@Body body: VerifyOtpRequest): Response<VerifyOtpResponse>

}
