package com.example.comandera.features.mesas.repository

import android.util.Log
import com.example.comandera.features.mesas.model.MesasCatalogoResponse
import com.example.comandera.features.mesas.model.MesasResult
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MesasRepositoryImpl(
    private val service: MesasServiceApi
) : MesasRepository {

    override fun getMesas(
        soloActivas: Int,
        callback: (MesasResult) -> Unit
    ) {
        Log.d("Mesas Request", "solo_activas=$soloActivas")

        service.getMesasCatalogo(soloActivas)
            .enqueue(object : Callback<MesasCatalogoResponse> {

                override fun onResponse(
                    call: Call<MesasCatalogoResponse>,
                    response: Response<MesasCatalogoResponse>
                ) {
                    Log.d("Mesas Response", "Response: ${response.body()}")

                    if (!response.isSuccessful) {
                        val msg = try {
                            val raw = response.errorBody()?.string()
                            Log.d("Mesas Error", "Error: $raw")

                            if (raw.isNullOrBlank()) "Error al cargar mesas"
                            else JSONObject(raw).optString("detail", "Error al cargar mesas")
                        } catch (_: Exception) {
                            "Error al cargar mesas"
                        }

                        callback(MesasResult.Error(msg))
                        return
                    }

                    val body = response.body()
                    if (body == null) {
                        callback(MesasResult.Error("Respuesta vacía del servidor"))
                        return
                    }

                    callback(MesasResult.Success(body))
                }

                override fun onFailure(call: Call<MesasCatalogoResponse>, t: Throwable) {
                    callback(MesasResult.Error("Error de conexión"))
                }
            })
    }
}
