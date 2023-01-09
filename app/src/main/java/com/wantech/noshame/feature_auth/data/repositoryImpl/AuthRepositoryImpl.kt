package com.wantech.noshame.feature_auth.data.repositoryImpl

import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.wantech.noshame.core.util.Resource
import com.wantech.noshame.core.util.UiText
import com.wantech.noshame.feature_auth.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject


class AuthRepositoryImpl @Inject constructor(private val auth: FirebaseAuth) : AuthRepository {
    override suspend fun signInUserWithEmailAndPassword(
        email: String, password: String
    ): Flow<Resource<AuthResult>> {
        return flow {
            emit(Resource.Loading())

            emit(
                Resource.Success(
                    data = auth.signInWithEmailAndPassword(
                        email.trim(), password.trim()
                    ).await()
                )
            )

        }.catch {
            emit(Resource.Error(uiText = UiText.DynamicString(it.message ?: "Unknown Error")))
        }
    }

    override suspend fun createUserWithEmailAndPassword(
        email: String, password: String, userName: String
    ): Flow<Resource<AuthResult>> = flow {
        emit(Resource.Loading())
        emit(Resource.Success(auth.createUserWithEmailAndPassword(email, password).await()))
    }.catch {
        emit(Resource.Error(UiText.DynamicString(it.message ?: "Unknown Error")))
    }

    override fun isCurrentUserExist(): Flow<Boolean> = flow { emit(auth.currentUser != null) }

    override suspend fun logoutUser() = auth.signOut()
    override suspend fun getUserId(): Flow<String> =
        flow { auth.currentUser?.uid?.let { emit(it) } }
}