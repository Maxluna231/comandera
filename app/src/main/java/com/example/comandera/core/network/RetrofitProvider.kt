package com.example.comandera.core.network

import com.example.comandera.core.network.interceptors.AuthInterceptor
import com.example.comandera.core.session.SessionManager
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitProvider(session: SessionManager) {

    private val okHttp = OkHttpClient.Builder()
        .addInterceptor(AuthInterceptor(session))
        .build()

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(URLApiConfig.BASE_URL)
        .client(okHttp)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun <T> create(service: Class<T>): T = retrofit.create(service)
}
