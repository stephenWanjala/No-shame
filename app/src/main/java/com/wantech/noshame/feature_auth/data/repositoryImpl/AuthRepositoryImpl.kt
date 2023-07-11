package com.wantech.noshame.feature_auth.data.repositoryImpl

import com.wantech.noshame.core.util.Resource
import com.wantech.noshame.feature_auth.domain.repository.AuthRepository
import com.wantech.noshame.feature_auth.domain.repository.AuthResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class AuthRepositoryImpl @Inject constructor(): AuthRepository {
    override suspend fun signInUserWithEmailAndPassword(
        email: String,
        password: String
    ): Flow<Resource<AuthResult>> {
        TODO("Not yet implemented")
    }

    override suspend fun createUserWithEmailAndPassword(
        email: String,
        password: String,
        userName: String
    ): Flow<Resource<AuthResult>> {
        TODO("Not yet implemented")
    }

    override fun isCurrentUserExist(): Flow<Boolean> {
        TODO("Not yet implemented")
    }

    override suspend fun logoutUser() {
        TODO("Not yet implemented")
    }

    override suspend fun getUserId(): Flow<String> {
        TODO("Not yet implemented")
    }
}