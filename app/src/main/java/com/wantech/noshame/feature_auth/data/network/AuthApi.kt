package com.wantech.noshame.feature_auth.data.network

import com.wantech.noshame.feature_auth.domain.model.request.LoginRequest
import com.wantech.noshame.feature_auth.domain.model.request.SignUpRequest
import com.wantech.noshame.feature_auth.domain.model.response.AuthResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface AuthApi {
    @POST("auth/signup")
    suspend fun signUp(@Body request: SignUpRequest): Response<AuthResponse>

    @POST("auth/login")
    suspend fun signIn(@Body loginRequest: LoginRequest): Response<AuthResponse>

    @GET("auth/authenticate")
   suspend fun authenticateUser(@Header("authorization") token:String)
}