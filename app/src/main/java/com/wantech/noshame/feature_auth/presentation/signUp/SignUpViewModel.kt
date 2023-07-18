package com.wantech.noshame.feature_auth.presentation.signUp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wantech.noshame.feature_auth.presentation.signUp.components.SignUpUIState
import com.wantech.noshame.feature_auth.presentation.signUp.components.SignupEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor() : ViewModel() {
    private val _state = MutableStateFlow(SignUpUIState())
    val state =
        _state.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), SignUpUIState())


    fun onEvent(event: SignupEvent) = when (event) {


        is SignupEvent.EnteredEmail -> {
            _state.update { it.copy(email = event.value) }
        }

        is SignupEvent.EnteredPassword -> {
            _state.update { it.copy(password = event.value) }
        }

        is SignupEvent.EnteredUsername -> {
            _state.update { it.copy(userName = event.value) }
        }


        SignupEvent.TogglePasswordVisibility -> {
            _state.update { it.copy(isPasswordVisible = !state.value.isPasswordVisible) }
        }

    }
}