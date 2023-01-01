package com.wantech.noshame.feature_auth.domain.repository

import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    suspend fun signInUserWithEmailAndPassword(email: String, password: String)
    suspend fun createUserWithEmailAndPassword(email: String, password: String, userName: String)
    fun isCurrentUserExist(): Flow<Boolean>

    suspend fun logoutUser()
}