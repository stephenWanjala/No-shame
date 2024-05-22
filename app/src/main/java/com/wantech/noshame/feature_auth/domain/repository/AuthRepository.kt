package com.wantech.noshame.feature_auth.domain.repository

import com.wantech.noshame.core.util.Resource
import com.wantech.noshame.featureMenstrualTrack.domain.model.CycleDetails
import com.wantech.noshame.feature_auth.domain.model.response.AuthResponse
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    suspend fun signInUserWithEmailAndPassword(
        email: String,
        password: String
    ): Flow<Resource<AuthResponse>>

    suspend fun createUserWithEmailAndPassword(
        userName: String,
        email: String,
        password: String,
        fullName: String? = null,
        phoneNumber: String? = null,
        lastMensesDate: String,
        cycleLength: Int,
        periodLength: Int
    ): Flow<Resource<AuthResponse>>

    fun isCurrentUserExist(): Flow<Boolean>

    suspend fun logoutUser()

    suspend fun getUserId(): Flow<String>
    suspend fun authenticate(): Flow<Resource<AuthResponse>>

    suspend fun getCycleDetails(): Flow<Resource<CycleDetails>>
}


interface SaveAuthToken {
    suspend fun saveAuthToken(authResponse: AuthResponse)

    suspend fun getAuthDataPref(): AuthResponse?
}