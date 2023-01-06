package com.wantech.noshame.feature_auth.presentation.signUp

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.wantech.noshame.feature_auth.presentation.signUp.components.SignUpState
import com.wantech.noshame.feature_auth.presentation.signUp.components.SignUpUIState
import com.wantech.noshame.feature_auth.presentation.signUp.components.SignupEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
//    private val repository: AuthRepository

) : ViewModel() {
    private val _state = mutableStateOf(SignUpUIState())
    val state: State<SignUpUIState> = _state

    private val _signUpUIState = MutableSharedFlow<SignUpState>()
    val signUpIState = _signUpUIState.asSharedFlow()


    fun onEvent(event: SignupEvent) {
        when (event) {
            is SignupEvent.ChooseLengthCycle -> {
                _state.value = state.value.copy(dayOneOfPreviousPeriod = event.spinnerData.id)
            }
            is SignupEvent.EnteredEmail -> {
                _state.value = state.value.copy(email = event.value)
            }
            is SignupEvent.EnteredPassword -> {
                _state.value = state.value.copy(password = event.value)
            }
            is SignupEvent.EnteredUsername -> {
                _state.value = state.value.copy(userName = event.value)
            }
            SignupEvent.Signup -> {

            }
            SignupEvent.ToSignInScreen -> {

            }
            SignupEvent.TogglePasswordVisibility -> {
                _state.value = state.value.copy(isPasswordVisible = !state.value.isPasswordVisible)
            }
        }

    }



    private fun signUp(userName: String, email: String, password: String){}
}