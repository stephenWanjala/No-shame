package com.wantech.noshame.feature_auth.presentation.login

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wantech.noshame.core.util.Resource
import com.wantech.noshame.feature_auth.domain.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LogInViewModel @Inject constructor(
    private val repository: AuthRepository
) : ViewModel() {
    private val _state = mutableStateOf(LoginUiState())
    val state: State<LoginUiState> = _state
    private val _loginState = MutableStateFlow(LoginState())
    val loginState = _loginState.asSharedFlow()


    fun onEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.EnteredEmail -> {
                _state.value = state.value.copy(email = event.value)
            }
            is LoginEvent.EnteredPassword -> {
                _state.value = state.value.copy(password = event.value)
            }
            LoginEvent.TogglePasswordVisibility -> {
                _state.value = state.value.copy(isPasswordVisible = !state.value.isPasswordVisible)
            }
            LoginEvent.Login -> {
                login(email = state.value.email, password = state.value.password)
            }
        }

    }

    private fun login(email: String, password: String) {
        viewModelScope.launch {
            repository.signInUserWithEmailAndPassword(email = email, password = password)
                .onEach { resource ->
                    when (resource) {
                        is Resource.Error -> {
                            _loginState.emit(LoginState(error = resource.uiText))
                        }
                        is Resource.Loading -> {
                            _loginState.emit(LoginState(isLoading = true))
                        }
                        is Resource.Success -> {
                            _loginState.emit(LoginState(login = resource.data))
                        }
                    }

                }
        }
    }

}