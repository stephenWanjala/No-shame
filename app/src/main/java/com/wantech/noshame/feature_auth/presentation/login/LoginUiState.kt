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
) {
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

data class LoginState(
    val isLoading: Boolean = false,
    val login: AuthResponse? = null,
    val error: UiText? = null
)
