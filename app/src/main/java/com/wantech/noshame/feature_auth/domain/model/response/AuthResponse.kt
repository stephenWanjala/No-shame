package com.wantech.noshame.feature_auth.domain.model.response

data class AuthResponse(
    val token: String,
    val userName:String,
val email:String,
val fullName:String?,
val phoneNumber:String?,
val last_menses_date: String,
val cycleLength: Int,
val periodLength: Int
)