package com.example.comandera.core.session

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.comandera.features.login.view.LoginActivity
import com.example.comandera.features.mesas.view.MesasActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val session = SessionManager(this)
        val token = session.getAccessToken() //

        val next = if (!token.isNullOrBlank()) {
            Intent(this, MesasActivity::class.java)
        } else {
            Intent(this, LoginActivity::class.java)
        }

        startActivity(next)
        finish()
    }
}
