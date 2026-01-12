package com.example.comandera.features.login.repository

import com.example.comandera.core.network.services.LoginService
import com.example.comandera.features.login.model.LoginRequest
import com.example.comandera.features.login.model.LoginResponse
import com.example.comandera.features.login.model.LoginResult
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginRepositoryImpl(
    private val service: LoginService
) : LoginRepository {

    override fun login(numeroEmpleado: String, clave: String, callback: (LoginResult) -> Unit) {
        val request = LoginRequest(numero_empleado = numeroEmpleado, clave = clave)

        service.login(request).enqueue(object : Callback<LoginResponse> {

            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if (!response.isSuccessful) {
                    callback(LoginResult.Error("Usuario o clave incorrectos"))
                    return
                }

                val body = response.body()
                val access = body?.accessToken

                if (access.isNullOrBlank()) {
                    callback(LoginResult.Error("Access token vacío"))
                    return
                }

                callback(
                    LoginResult.Success(
                        accessToken = access,
                        refreshToken = body.refreshToken,
                        idUsuario = body.usuario?.idUsuario,
                        idLocal = body.usuario?.idLocal,
                        idRol = body.usuario?.idRol
                    )
                )
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                callback(LoginResult.Error("Error de conexión"))
            }
        })
    }
}
