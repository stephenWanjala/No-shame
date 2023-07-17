package com.wantech.noshame.feature_auth.presentation.signUp.components

sealed class SignupEvent {
    data class EnteredUsername(val value: String) : SignupEvent()
    data class EnteredEmail(val value: String) : SignupEvent()
    data class EnteredPassword(val value: String) : SignupEvent()
    object TogglePasswordVisibility : SignupEvent()
    object Next : SignupEvent()
}