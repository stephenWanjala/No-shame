package com.wantech.noshame.feature_auth.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wantech.noshame.core.util.Resource
import com.wantech.noshame.feature_auth.domain.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LogInViewModel @Inject constructor(
    private val repository: AuthRepository
) : ViewModel() {
    private val _state = MutableStateFlow(LoginUiState())
    val state = _state.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), LoginUiState())


    init {
        authenticate()
    }

    fun onEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.EnteredEmail -> {
                _state.update { it.copy(email = event.value) }
            }

            is LoginEvent.EnteredPassword -> {
                _state.update { it.copy(password = event.value) }
            }

            LoginEvent.TogglePasswordVisibility -> {
                _state.update { it.copy(isPasswordVisible = !state.value.isPasswordVisible) }
            }

            LoginEvent.Login -> {
                login(email = state.value.email, password = state.value.password)
            }
        }

    }

    private fun login(email: String, password: String) {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            repository.signInUserWithEmailAndPassword(email = email, password = password)
                .collectLatest { resource ->
                    when (resource) {
                        is Resource.Error -> {
                            _state.update { it.copy(error = resource.uiText) }
                        }

                        is Resource.Loading -> {
                            _state.update { it.copy(isLoading = true) }
                        }

                        is Resource.Success -> {
                            _state.update { it.copy(login = resource.data) }
                        }
                    }


                }
            _state.update { it.copy(isLoading = false) }
        }
    }

    private fun authenticate() {
        viewModelScope.launch {
            val response = repository.authenticate()
            response.collectLatest { resource ->
                when (resource) {
                    is Resource.Error -> _state.update { it.copy(error = resource.uiText) }
                    is Resource.Loading -> {
//                        _state.update { it.copy(isLoading = true) }
                    }

                    is Resource.Success -> {
                        resource.data?.let { authRes ->
                            _state.update { it.copy(login = authRes) }
                        }
                    }
                }
            }
        }
    }

}