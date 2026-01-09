package com.example.comandera
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST


interface ApiService {
    @POST("usuarios/login/")
    fun login(
        @Body request: LoginRequest
    ): Call<LoginResponse>

}