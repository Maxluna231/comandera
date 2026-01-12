package com.example.comandera.features.mesas.model

sealed class MesasResult {
    data class Success(val data: MesasCatalogoResponse) : MesasResult()
    data class Error(val message: String) : MesasResult()
}
