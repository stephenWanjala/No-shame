package com.wantech.noshame.feature_auth.domain.model.request

data class SignUpRequest(
    val userName:String,
    val email: String,
    val password: String,
    val fullName:String?=null,
    val phoneNumber:String?=null,
    val lastMensesDate: String,
    val cycleLength: Int,
    val periodLength: Int
)
