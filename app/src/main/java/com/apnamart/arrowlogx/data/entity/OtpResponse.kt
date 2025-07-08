package com.apnamart.arrowlogx.data.entity

import com.google.gson.annotations.SerializedName

data class SendOtpRequest(
    @SerializedName("client_app") val clientApp: String = "rider",
    @SerializedName("country_code") val countryCode: String,
    @SerializedName("phone") val phone: String,
    @SerializedName("device_identifier") val deviceIdentifier: String = "123"
)

data class SendOtpResponse(
    @SerializedName("token") val token: String,
    @SerializedName("account_id") val accountId: Int,
    @SerializedName("forgot_pwd") val forgotPwd: Boolean,
    @SerializedName("in_training") val inTraining: Boolean,
    @SerializedName("otp_sent") val otpSent: Boolean
)

data class VerifyOtpRequest(
    @SerializedName("account_id") val accountId: Int,
    @SerializedName("client_app") val clientApp: String = "rider",
    @SerializedName("country_code") val countryCode: String,
    @SerializedName("otp") val otp: String,
    @SerializedName("phone") val phone: String,
    @SerializedName("token") val token: String,
    @SerializedName("device_identifier") val deviceIdentifier: String
)

data class VerifyOtpResponse(
    @SerializedName("token") val token: String,
    @SerializedName("is_active") val isActive: Boolean,
    @SerializedName("tez_token") val tezToken: String,
    @SerializedName("user_data") val userData: UserData
)

data class UserData(
    @SerializedName("username") val username: String?,
    @SerializedName("name") val name: String?,
    @SerializedName("phone") val phone: String?,
    @SerializedName("user_id") val userId: String?,
    @SerializedName("whatsapp") val whatsapp: String?,
    @SerializedName("user_discovery_radius") val userDiscoveryRadius: Double?
    // Add other fields as needed with @SerializedName if needed
)
