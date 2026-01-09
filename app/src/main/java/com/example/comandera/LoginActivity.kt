package com.example.comandera
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.EditText
import android.widget.Button
import android.widget.Toast
import android.content.Intent
import android.util.Log

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val etEmpleado = findViewById<EditText>(R.id.etEmpleado)
        val etClave = findViewById<EditText>(R.id.etClave)
        val btnLogin = findViewById<Button>(R.id.btnLogin)

        btnLogin.setOnClickListener {

            val numeroEmpleado = etEmpleado.text.toString()
            val clave = etClave.text.toString()

            val request = LoginRequest(numeroEmpleado, clave)

            RetrofitClient.api.login(request)
                .enqueue(object : retrofit2.Callback<LoginResponse> {

                    override fun onResponse(
                        call: retrofit2.Call<LoginResponse>,
                        response: retrofit2.Response<LoginResponse>


                    ) {

                        Log.d("LOGIN", "Código HTTP: ${response.code()}")
                        Log.d("LOGIN", "Body: ${response.body()}")
                        Log.d("LOGIN", "ErrorBody: ${response.errorBody()?.string()}")

                        if (response.code() == 200 || response.code() == 201) {

                            val token = response.body()?.token

                            if (token != null) {
                                getSharedPreferences("session", MODE_PRIVATE)
                                    .edit()
                                    .putString("token", token)
                                    .apply()

                                Toast.makeText(this@LoginActivity, "Login correcto", Toast.LENGTH_SHORT).show()
                            } else {
                                Toast.makeText(this@LoginActivity, "Token vacío", Toast.LENGTH_SHORT).show()
                            }

                        } else {
                            Toast.makeText(this@LoginActivity, "Credenciales incorrectas", Toast.LENGTH_SHORT).show()
                        }
                        if (response.isSuccessful) {

                            val token = response.body()?.token

                            // Guardar token
                            getSharedPreferences("session", MODE_PRIVATE)
                                .edit()
                                .putString("token", token)
                                .apply()

                            Toast.makeText(
                                this@LoginActivity,
                                "Login correcto",
                                Toast.LENGTH_SHORT
                            ).show()

                            startActivity(
                                Intent(this@LoginActivity, MesasActivity::class.java)
                            )
                            finish()

                        } else {
                            Toast.makeText(
                                this@LoginActivity,
                                "Usuario o clave incorrectos",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }

                    override fun onFailure(
                        call: retrofit2.Call<LoginResponse>,
                        t: Throwable
                    ) {
                        Toast.makeText(
                            this@LoginActivity,
                            "Error de conexión",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                })
        }
    }

}