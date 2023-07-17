package com.wantech.noshame.feature_auth.data.repositoryImpl

import android.content.SharedPreferences
import com.wantech.noshame.core.util.Resource
import com.wantech.noshame.core.util.UiText
import com.wantech.noshame.feature_auth.data.network.AuthApi
import com.wantech.noshame.feature_auth.domain.model.request.LoginRequest
import com.wantech.noshame.feature_auth.domain.model.request.SignUpRequest
import com.wantech.noshame.feature_auth.domain.model.response.AuthResponse
import com.wantech.noshame.feature_auth.domain.repository.AuthRepository
import com.wantech.noshame.feature_auth.domain.repository.SaveAuthToken
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.time.LocalDate
import javax.inject.Inject


class AuthRepositoryImpl @Inject constructor(
    private val api: AuthApi,
    private val saveToken: SaveAuthToken,
) : AuthRepository {
    override suspend fun signInUserWithEmailAndPassword(
        email: String,
        password: String
    ): Flow<Resource<AuthResponse>> {

        return flow {
            emit(Resource.Loading())
            try {
                val response = api.signIn(LoginRequest(email = email, password = password))
                if (!response.isSuccessful) throw HttpException(response)
                saveToken.saveAuthToken(response.body()!!.token)
                emit(Resource.Success(response.body()!!))
            } catch (e: HttpException) {
                emit(
                    Resource.Error(
                        UiText.DynamicString(
                            e.localizedMessage ?: "UnExpected Error happened"
                        )
                    )
                )
            } catch (e: Exception) {
                emit(
                    Resource.Error(
                        UiText.DynamicString(
                            e.localizedMessage ?: "UnExpected Error happened"
                        )
                    )
                )
            }

        }
    }

    override suspend fun createUserWithEmailAndPassword(
        userName: String,
        email: String,
        password: String,
        fullName: String?,
        phoneNumber: String?,
        lastMensesDate: LocalDate,
        cycleLength: Int,
        periodLength: Int
    ): Flow<Resource<AuthResponse>> {
        return flow {
            emit(Resource.Loading())
            try {
                val response = api.signUp(
                    request = SignUpRequest(
                        userName = userName,
                        email = email,
                        password = password,
                        fullName = fullName,
                        phoneNumber = phoneNumber,
                        lastMensesDate = lastMensesDate,
                        cycleLength = cycleLength,
                        periodLength = periodLength
                    )
                )
                if (!response.isSuccessful) throw HttpException(response)
                emit(Resource.Success(response.body()!!))

            } catch (e: HttpException) {

                emit(
                    Resource.Error(
                        UiText.DynamicString(
                            e.localizedMessage ?: "UnExpected Error happened"
                        )
                    )
                )

            } catch (e: Exception) {

                emit(
                    Resource.Error(
                        UiText.DynamicString(
                            e.localizedMessage ?: "UnExpected Error happened"
                        )
                    )
                )

            }
        }
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

    override suspend fun authenticate(): Flow<Resource<AuthResponse>> {
        val token = saveToken.getToken()
        return flow {
            emit(Resource.Loading())
            try {
                if (token.isNullOrEmpty()) throw Exception("Token is null or empty")
                api.authenticateUser(token = "Bearer $token")
                emit(Resource.Success(AuthResponse(token = token)))
            } catch (e: HttpException) {
                emit(
                    Resource.Error(
                        UiText.DynamicString(
                            e.localizedMessage ?: "UnExpected Error happened"
                        )
                    )
                )
            } catch (e: Exception) {
                emit(
                    Resource.Error(
                        UiText.DynamicString(
                            e.localizedMessage ?: "UnExpected Error happened"
                        )
                    )
                )
            }

        }
    }
}

class SaveAuthTokenInSharedPref @Inject constructor(private val pref: SharedPreferences) :
    SaveAuthToken {
    override suspend fun saveAuthToken(token: String) {
        pref.edit().putString("token", token).apply()
    }

    override suspend fun getToken(): String? {
        return pref.getString("token", "")
    }
}

