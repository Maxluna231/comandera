package com.example.comandera.features.login.model
import com.google.gson.annotations.SerializedName

/* Request: Lo que mando */
data class LoginRequest(
    val numero_empleado: String,
    val clave: String
)

/* RESPONSE Lo que me responde el servidor */
data class LoginResponse(
    @SerializedName("access_token") val accessToken: String?,
    @SerializedName("refresh_token") val refreshToken: String?,
    val usuario: UsuarioResponse?
)

data class UsuarioResponse(
    @SerializedName("id_usuario") val idUsuario: Int?,
    @SerializedName("id_rol") val idRol: Int?,
    @SerializedName("id_local") val idLocal: Int?,
    val nombre: String?,
    val apellido: String?,
    @SerializedName("numero_empleado") val numeroEmpleado: String?,
    val puesto: String?,
    val status: Int?
)

/* RESULT (DOMINIO / UI)  */
sealed class LoginResult {
    data class Success(
        val accessToken: String,
        val refreshToken: String?,
        val idUsuario: Int?,
        val idLocal: Int?,
        val idRol: Int?
    ) : LoginResult()

    data class Error(val message: String) : LoginResult()
}
