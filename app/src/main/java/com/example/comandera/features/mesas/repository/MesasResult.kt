package com.example.comandera.features.mesas.repository

sealed class MesasResult<out T> {
    data class Success<T>(val data: T) : MesasResult<T>()
    data class Error(val message: String, val code: Int? = null) : MesasResult<Nothing>()
}
