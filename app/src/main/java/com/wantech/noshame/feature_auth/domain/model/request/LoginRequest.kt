package com.wantech.noshame.feature_auth.domain.model.request

data class LoginRequest(
    val email: String,
    val password: String
)
