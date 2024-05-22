package com.wantech.noshame.feature_auth.presentation.login

import com.wantech.noshame.core.util.UiText
import com.wantech.noshame.feature_auth.domain.model.response.AuthResponse

data class LoginUiState(
    val email: String = "",
    val password: String = "",
    val isPasswordVisible: Boolean = false,
    val isEmailError: EmailError? = null,
    val isPasswordError: PasswordError? = null,
    val isLoginButtonEnabled: Boolean = false,
    val isLoading: Boolean = false,
    val login: AuthResponse? = null,
    val error: UiText? = null
) {
    sealed class EmailError {
        data object FieldEmpty : EmailError()
        data object InvalidEmail : EmailError()
    }

    sealed class PasswordError {
        data object FieldEmpty : PasswordError()
        data object InvalidPassword : PasswordError()
        data object InputTooShort : PasswordError()
    }
}

data class LoginState(
    val isLoading: Boolean = false,
    val login: AuthResponse? = null,
    val error: UiText? = null
)
