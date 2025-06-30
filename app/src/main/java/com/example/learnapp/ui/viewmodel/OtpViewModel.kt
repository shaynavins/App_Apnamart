package com.example.learnapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.learnapp.data.api.OtpApiService
import com.example.learnapp.data.entity.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OtpViewModel @Inject constructor(
    private val api: OtpApiService
) : ViewModel() {

    private val _message = MutableStateFlow("")
    val message = _message.asStateFlow()

    private var accountId: Int = -1
    private var token: String = ""

    fun sendOtp(phone: String) {
        viewModelScope.launch {
            try {
                val request = SendOtpRequest(
                    country_code = "+91",
                    phone = phone
                )
                val response = api.sendOtp(request)
                if (response.isSuccessful) {
                    val body = response.body()
                    if (body != null && body.otp_sent) {
                        accountId = body.account_id
                        token = body.token
                        _message.value = "OTP sent successfully"
                    } else {
                        _message.value = "Failed to send OTP"
                    }
                } else {
                    _message.value = "Error: ${response.code()}"
                }
            } catch (e: Exception) {
                _message.value = "Exception: ${e.message}"
            }
        }
    }

    fun verifyOtp(phone: String,
                  otp: String,
                  deviceId: String,
                  onResult: (Boolean, String?) -> Unit
    ) {
        viewModelScope.launch {
            try {
                val request = mapOf(
                    "account_id" to accountId,
                    "client_app" to "rider",
                    "country_code" to "+91",
                    "otp" to otp,
                    "phone" to phone,
                    "device_identifier" to deviceId
                )

                val response = api.verifyOtp(request)

                if (response.isSuccessful) {
                    val body = response.body()
                    val token = body?.token
                    if (!token.isNullOrEmpty()) {
                        onResult(true, token)
                    } else {
                        onResult(false, null)
                    }
                } else {
                    onResult(false, null)
                }

            } catch (e: Exception) {
                onResult(false, null)
            }
        }
    }

}
