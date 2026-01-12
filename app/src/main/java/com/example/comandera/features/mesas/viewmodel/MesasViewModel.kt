package com.example.comandera.features.mesas.viewmodel

import androidx.lifecycle.ViewModel
import com.example.comandera.features.mesas.model.MesasResult
import com.example.comandera.features.mesas.repository.MesasRepository

class MesasViewModel(
    private val repository: MesasRepository
) : ViewModel() {

    fun cargarMesas(
        soloActivas: Int = 1,
        onResult: (MesasResult) -> Unit
    ) {
        repository.getMesas(soloActivas, onResult)
    }
}
