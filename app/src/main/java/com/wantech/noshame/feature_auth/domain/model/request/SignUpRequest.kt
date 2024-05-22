package com.wantech.noshame.feature_auth.domain.model.request

import com.google.gson.annotations.SerializedName
import java.time.LocalDate

data class SignUpRequest(
    val authInfo: AuthInfo,
    val fullName:String?=null,
    val phoneNumber:String?=null,
    val mensesInfo: PreviousMensesInfo
){
    init {
        require(authInfo.email.isNotEmpty()){"Email cannot be empty"}
        require(authInfo.password.isNotEmpty()){"Password cannot be empty"}
        require(authInfo.userName.isNotEmpty()){"Username cannot be empty"}
        require(mensesInfo.lastMensesDate.toString().isNotEmpty()){"Last menses date cannot be empty"}
        require(mensesInfo.cycleLength.toString().isNotEmpty()){"Cycle length cannot be empty"}
        require(mensesInfo.periodLength.toString().isNotEmpty()){"Period length cannot be empty"}
    }
}


data class PreviousMensesInfo(
    @SerializedName("last_menses_date")
    val lastMensesDate: String,
    val cycleLength: Int,
    val periodLength: Int
) {
    init {
        require(cycleLength in 21..30) { "Cycle length must be between 21 and 30 days" }
        require(periodLength in 3..7) { "Length of period must be between 3 and 7 days" }
    }
}

data class AuthInfo(
    val userName:String,
    val email: String,
    val password: String,
)