package com.wantech.noshame.feature_auth.data.network

import com.wantech.noshame.featureMenstrualTrack.domain.model.SerializableCycleDetails
import com.wantech.noshame.feature_auth.domain.model.request.LoginRequest
import com.wantech.noshame.feature_auth.domain.model.request.SignUpRequest
import com.wantech.noshame.feature_auth.domain.model.response.AuthResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface AuthApi {
    @POST("signup")
    suspend fun signUp(@Body request: SignUpRequest): Response<AuthResponse>

    @POST("signin")
    suspend fun signIn(@Body loginRequest: LoginRequest): Response<AuthResponse>

    @GET("authenticate")
   suspend fun authenticateUser(@Header("authorization") token:String):Response<Unit>

   @GET("cycleDetails")
   suspend fun getCycleDetails(@Header("authorization") token:String):Response<SerializableCycleDetails>
}