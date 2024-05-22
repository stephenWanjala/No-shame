package com.wantech.noshame.feature_auth.presentation.login

sealed class LoginEvent {
    data class EnteredEmail(val value: String) : LoginEvent()
    data class EnteredPassword(val value: String) : LoginEvent()
    data object TogglePasswordVisibility : LoginEvent()
    data object Login : LoginEvent()
}