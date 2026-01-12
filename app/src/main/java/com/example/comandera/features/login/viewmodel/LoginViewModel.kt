package com.example.comandera.features.login.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.comandera.features.login.repository.LoginRepository
import com.example.comandera.features.login.model.LoginResult

data class LoginUiState(
    val loading: Boolean = false,
    val error: String? = null,
    val success: Boolean = false
)

class LoginViewModel(
    private val repo: LoginRepository
) : ViewModel() {

    private val _state = MutableLiveData(LoginUiState())
    val state: LiveData<LoginUiState> = _state

    private var lastSuccess: LoginResult.Success? = null
    fun getLastSuccess(): LoginResult.Success? = lastSuccess

    fun login(numeroEmpleado: String, clave: String) {
        if (numeroEmpleado.isBlank() || clave.isBlank()) {
            _state.value = LoginUiState(error = "Ingresa empleado y clave")
            return
        }

        _state.value = LoginUiState(loading = true)

        repo.login(numeroEmpleado, clave) { result ->
            when (result) {
                is LoginResult.Success -> {
                    lastSuccess = result
                    _state.postValue(LoginUiState(success = true))
                }
                is LoginResult.Error -> {
                    _state.postValue(LoginUiState(error = result.message))
                }
            }
        }
    }

    fun clearError() {
        val current = _state.value ?: LoginUiState()
        if (current.error != null) {
            _state.value = current.copy(error = null)
        }
    }
}
