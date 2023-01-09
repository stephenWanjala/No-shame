package com.wantech.noshame.feature_auth.presentation.signUp.components

import com.google.firebase.auth.AuthResult
import com.wantech.noshame.core.util.UiText

data class SignUpUIState(
    val userName: String = "",
    val email: String = "",
    val password: String = "",
    val isPasswordVisible: Boolean = false,
    val isUserNameError: UsernameError? = null,
    val isEmailError: EmailError? = null,
    val isPasswordError: PasswordError? = null,
    val isNextButtonEnabled: Boolean = false,
    val dayOneOfPreviousPeriod: Int = 0,
    val periodLength: Int = 0,
    val isFinishEnabled: Boolean = false

) {
    sealed class UsernameError {
        object FieldEmpty : UsernameError()
        object InputTooShort : UsernameError()
    }

    sealed class EmailError {
        object FieldEmpty : EmailError()
        object InvalidEmail : EmailError()
    }

    sealed class PasswordError {
        object FieldEmpty : PasswordError()
        object InvalidPassword : PasswordError()
        object InputTooShort : PasswordError()
    }


}

data class SignUpState(
    val isLoading: Boolean = false,
    val signUp: AuthResult? = null,
    val error: UiText? = null
)
