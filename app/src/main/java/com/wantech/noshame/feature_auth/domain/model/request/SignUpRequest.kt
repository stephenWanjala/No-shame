package com.wantech.noshame.feature_auth.domain.model.request

data class SignUpRequest(
    val email: String,
    val userName: String,
    val password: String,
    val phoneNumber: String?=null,
    val fullName:String?=null,


)
