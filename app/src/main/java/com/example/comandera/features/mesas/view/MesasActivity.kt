package com.example.comandera.features.mesas.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.comandera.databinding.ActivityMesasBinding
import com.example.comandera.features.mesas.viewmodel.MesasViewModel
import kotlinx.coroutines.launch

class MesasActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMesasBinding

    // OJO: aquí normalmente tú ya tienes un Factory como en Login.
    // Abajo te dejo el Factory igualito al patrón.
    private val viewModel: MesasViewModel by viewModels { MesasViewModelFactory(this) }

    private val adapter = MesasAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMesasBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerMesas.adapter = adapter

        binding.swipeRefresh.setOnRefreshListener {
            viewModel.cargarMesas(soloActivas = 1)
        }

        lifecycleScope.launch {
            viewModel.uiState.collect { state ->
                binding.progress.visibility = if (state.isLoading) View.VISIBLE else View.GONE
                binding.swipeRefresh.isRefreshing = state.isLoading

                state.data?.let { data ->
                    // si quieres mostrar arriba el restaurante/usuario:
                    binding.txtSucursal.text = data.restaurante.nombreComercial ?: "-"
                    binding.txtUsuario.text = data.usuario.nombre

                    adapter.submitList(data.mesas)
                }

                state.errorMessage?.let { msg ->
                    Toast.makeText(this@MesasActivity, msg, Toast.LENGTH_SHORT).show()
                }
            }
        }

        viewModel.cargarMesas(soloActivas = 1)
    }
}
