package com.example.comandera.features.mesas.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.comandera.core.network.RetrofitProvider
import com.example.comandera.core.session.SessionManager
import com.example.comandera.features.mesas.repository.MesasServiceApi
import com.example.comandera.features.mesas.repository.MesasRepositoryImpl

class MesasViewModelFactory(
    private val session: SessionManager
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val retrofit = RetrofitProvider(session)
        val service = retrofit.create(MesasServiceApi::class.java)
        val repo = MesasRepositoryImpl(service)

        @Suppress("UNCHECKED_CAST")
        return MesasViewModel(repo) as T
    }
}

