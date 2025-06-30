package com.example.learnapp.data.entity


data class SendOtpRequest(
    val client_app: String = "rider",
    val country_code: String,
    val phone: String,
    val device_identifier: String = "123",
)
data class SendOtpResponse(
    val token: String,
    val account_id: Int,
    val forgot_pwd: Boolean,
    val in_training: Boolean,
    val otp_sent: Boolean
)
data class VerifyOtpRequest(
    val account_id: Int,
    val client_app: String = "rider",
    val country_code: String,
    val otp: String,
    val phone: String,
    val token: String
)

data class VerifyOtpResponse(
    val token: String,
    val is_active: Boolean,
    val tez_token: String,
    val user_data: UserData
)

data class UserData(
    val username: String?,
    val name: String?,
    val phone: String?,
    val user_id: String?,
    val whatsapp: String?,
    val user_discovery_radius: Double?,
    // Add other fields as needed
)
