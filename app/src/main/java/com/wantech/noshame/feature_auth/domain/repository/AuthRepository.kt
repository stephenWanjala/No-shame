package com.wantech.noshame.feature_auth.domain.repository

import com.google.firebase.auth.AuthResult
import com.wantech.noshame.core.util.Resource
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    suspend fun signInUserWithEmailAndPassword(
        email: String,
        password: String
    ): Flow<Resource<AuthResult>>

    suspend fun createUserWithEmailAndPassword(
        email: String,
        password: String,
        userName: String
    ): Flow<Resource<AuthResult>>

    fun isCurrentUserExist(): Flow<Boolean>

    suspend fun logoutUser()

    suspend fun getUserId(): Flow<String>
}