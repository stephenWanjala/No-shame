package com.wantech.noshame.feature_auth.presentation.signUp.components

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.time.LocalDate

data class SignUpUIState(
    val userName: String = "",
    val email: String = "",
    val password: String = "",
    val isPasswordVisible: Boolean = false,
    val isUserNameError: UsernameError? = null,
    val isEmailError: EmailError? = null,
    val isPasswordError: PasswordError? = null,
    val isNextButtonEnabled: Boolean = false,
    val dayOneOfPreviousPeriod: LocalDate = LocalDate.now(),
    val periodLength: Int = 3,
    val cycleLength: Int = 28,
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


@Parcelize
data class AuthDetails(
    val userName: String,
    val email: String,
    val password: String
) : Parcelable