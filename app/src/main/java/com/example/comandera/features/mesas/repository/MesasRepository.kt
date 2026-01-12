package com.example.comandera.features.mesas.repository

import com.example.comandera.features.mesas.model.MesasResult

interface MesasRepository {
    fun getMesas(
        soloActivas: Int = 1,
        callback: (MesasResult) -> Unit
    )
}
