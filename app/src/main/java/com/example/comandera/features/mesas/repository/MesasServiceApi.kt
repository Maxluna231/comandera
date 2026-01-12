package com.example.comandera.features.mesas.repository

import com.example.comandera.features.mesas.model.MesasCatalogoResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MesasServiceApi {

    @GET("api/mesas/catalogo/")
    fun getMesasCatalogo(
        @Query("solo_activas") soloActivas: Int = 1
    ): Call<MesasCatalogoResponse>
}
