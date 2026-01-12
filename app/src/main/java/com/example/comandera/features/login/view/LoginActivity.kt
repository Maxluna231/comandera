package com.example.comandera.features.login.view

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.comandera.R
import com.example.comandera.core.network.RetrofitProvider
import com.example.comandera.core.network.services.LoginService
import com.example.comandera.core.session.SessionManager
import com.example.comandera.features.login.repository.LoginRepositoryImpl
import com.example.comandera.features.login.viewmodel.LoginUiState
import com.example.comandera.features.login.viewmodel.LoginViewModel
import com.example.comandera.features.login.viewmodel.LoginViewModelFactory
import com.example.comandera.features.mesas.view.MesasActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var session: SessionManager

    private val viewModel: LoginViewModel by viewModels {
        session = SessionManager(this)

        // RetrofitProvider tal como tú lo tienes
        val retrofitProvider = RetrofitProvider(session)
        val loginService = retrofitProvider.create(LoginService::class.java)

        val repo = LoginRepositoryImpl(loginService)
        LoginViewModelFactory(repo)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val etEmpleado = findViewById<EditText>(R.id.etEmpleado)
        val etClave = findViewById<EditText>(R.id.etClave)
        val btnLogin = findViewById<Button>(R.id.btnLogin)

        // OBSERVAR ESTADO DEL LOGIN
        viewModel.state.observe(this) { state: LoginUiState ->

            // Bloquea botón mientras carga
            btnLogin.isEnabled = !state.loading

            // Mostrar error
            state.error?.let { msg ->
                Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
                viewModel.clearError()
            }

            // Login exitoso
            if (state.success) {
                val success = viewModel.getLastSuccess()

                if (success != null) {
                    session.saveAccessToken(success.accessToken)
                    success.refreshToken?.let { session.saveRefreshToken(it) }
                    success.idUsuario?.let { session.saveIdUsuario(it) }
                    success.idLocal?.let { session.saveIdLocal(it) }
                    success.idRol?.let { session.saveIdRol(it) }
                }

                startActivity(Intent(this, MesasActivity::class.java))
                finish()
            }
        }

        // Click Login
        btnLogin.setOnClickListener {
            val numeroEmpleado = etEmpleado.text.toString().trim()
            val clave = etClave.text.toString().trim()
            viewModel.login(numeroEmpleado, clave)
        }
    }
}
