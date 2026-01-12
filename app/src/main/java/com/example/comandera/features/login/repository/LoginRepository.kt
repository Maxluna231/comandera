package com.example.comandera.features.login.repository

import com.example.comandera.features.login.model.LoginResult

interface LoginRepository {
    fun login(numeroEmpleado: String, clave: String, callback: (LoginResult) -> Unit)
}
