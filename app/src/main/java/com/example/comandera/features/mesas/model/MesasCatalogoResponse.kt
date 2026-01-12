package com.example.comandera.features.mesas.model

import com.google.gson.annotations.SerializedName

data class MesasCatalogoResponse(
    @SerializedName("restaurante") val restaurante: RestauranteInfo,
    @SerializedName("usuario") val usuario: UsuarioInfo,
    @SerializedName("mesas") val mesas: List<Mesa>
)

data class RestauranteInfo(
    @SerializedName("id_local") val idLocal: Int,
    @SerializedName("numero_unidad") val numeroUnidad: String?,
    @SerializedName("numero_legal") val numeroLegal: String?, // ojo: en tu JSON viene "numero_legal" pero suena a "nombre_legal"
    @SerializedName("nombre_comercial") val nombreComercial: String?,
    @SerializedName("ciudad") val ciudad: String?
)

data class UsuarioInfo(
    @SerializedName("id_usuario") val idUsuario: Int,
    @SerializedName("nombre") val nombre: String,
    @SerializedName("numero_empleado") val numeroEmpleado: String?,
    @SerializedName("puesto") val puesto: String?,
    @SerializedName("rol") val rol: String?
)

data class Mesa(
    @SerializedName("id_mesa") val idMesa: Int,
    @SerializedName("nombre") val nombre: String,
    @SerializedName("zona") val zona: String?,
    @SerializedName("orden_visual") val ordenVisual: Int?,
    @SerializedName("activa") val activa: Boolean,
    @SerializedName("estado_mesa") val estadoMesa: String
)
