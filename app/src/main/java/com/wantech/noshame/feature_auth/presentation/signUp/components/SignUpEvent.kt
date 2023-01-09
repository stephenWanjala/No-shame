package com.wantech.noshame.feature_auth.presentation.signUp.components

import com.wantech.noshame.feature_auth.presentation.components.SpinnerData

sealed class SignupEvent {
    data class EnteredUsername(val value: String) : SignupEvent()
    data class EnteredEmail(val value: String) : SignupEvent()
    data class EnteredPassword(val value: String) : SignupEvent()
    object TogglePasswordVisibility : SignupEvent()
    object Signup : SignupEvent()
    data class ChooseLengthCycle(val spinnerData: SpinnerData) : SignupEvent()
}