package com.example.comandera.core.session

import android.content.Context

class SessionManager(context: Context) {
    private val prefs = context.getSharedPreferences("session", Context.MODE_PRIVATE)

    fun saveToken(token: String) {
        prefs.edit().putString("access_token", token).apply()
    }

    fun getAccessToken(): String? = prefs.getString("access_token", null)

    fun saveRefreshToken(token: String) {
        prefs.edit().putString("refresh_token", token).apply()
    }

    fun getRefreshToken(): String? = prefs.getString("refresh_token", null)

    fun saveIdUsuario(id: Int) {
        prefs.edit().putInt("id_usuario", id).apply()
    }

    fun getIdUsuario(): Int = prefs.getInt("id_usuario", -1)

    fun saveIdLocal(id: Int) {
        prefs.edit().putInt("id_local", id).apply()
    }

    fun getIdLocal(): Int = prefs.getInt("id_local", -1)

    fun saveIdRol(id: Int) {
        prefs.edit().putInt("id_rol", id).apply()
    }

    fun getIdRol(): Int = prefs.getInt("id_rol", -1)

    fun clear() {
        prefs.edit().clear().apply()
    }
}
