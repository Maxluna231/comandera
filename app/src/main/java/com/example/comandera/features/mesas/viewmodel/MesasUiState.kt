package com.example.comandera.features.mesas.viewmodel

import com.example.comandera.features.mesas.model.MesasCatalogoResponse

data class MesasUiState(
    val isLoading: Boolean = false,
    val data: MesasCatalogoResponse? = null,
    val errorMessage: String? = null
)
