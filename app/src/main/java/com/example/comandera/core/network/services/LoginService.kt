package com.example.comandera.core.network.services

import com.example.comandera.features.login.model.LoginRequest
import com.example.comandera.features.login.model.LoginResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginService {
    @POST("usuarios/login/")
    fun login(@Body request: LoginRequest): Call<LoginResponse>
}
