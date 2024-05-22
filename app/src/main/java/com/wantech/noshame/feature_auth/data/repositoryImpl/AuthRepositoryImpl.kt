package com.wantech.noshame.feature_auth.data.repositoryImpl

import android.content.SharedPreferences
import com.wantech.noshame.core.util.Resource
import com.wantech.noshame.core.util.UiText
import com.wantech.noshame.featureMenstrualTrack.domain.model.CycleDetails
import com.wantech.noshame.featureMenstrualTrack.domain.model.toDomain
import com.wantech.noshame.feature_auth.data.network.AuthApi
import com.wantech.noshame.feature_auth.domain.model.request.AuthInfo
import com.wantech.noshame.feature_auth.domain.model.request.LoginRequest
import com.wantech.noshame.feature_auth.domain.model.request.PreviousMensesInfo
import com.wantech.noshame.feature_auth.domain.model.request.SignUpRequest
import com.wantech.noshame.feature_auth.domain.model.response.AuthResponse
import com.wantech.noshame.feature_auth.domain.repository.AuthRepository
import com.wantech.noshame.feature_auth.domain.repository.SaveAuthToken
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
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
                saveToken.saveAuthToken(response.body()!!)
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
        lastMensesDate: String,
        cycleLength: Int,
        periodLength: Int
    ): Flow<Resource<AuthResponse>> {
        return flow {
            emit(Resource.Loading())
            try {
                val response = api.signUp(
                    request = SignUpRequest(
//                        fullName = fullName,
//                        phoneNumber = phoneNumber,
                       mensesInfo = PreviousMensesInfo(
                            lastMensesDate = lastMensesDate,
                            cycleLength = cycleLength,
                            periodLength = periodLength
                        ),
                        authInfo = AuthInfo(
                            userName = userName,
                            email = email,
                            password = password
                        )
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

    override suspend fun getCycleDetails(): Flow<Resource<CycleDetails>> {
        return flow {
            emit(Resource.Loading())
            try {
                val saveRep = saveToken.getAuthDataPref() ?: throw Exception("Token is null or empty")
                if (saveRep.token.isEmpty()) throw Exception("Token is null or empty")
                val response = api.getCycleDetails(token = "Bearer ${saveRep.token}")
                if (response.isSuccessful) {
                    emit(Resource.Success(data = response.body()!!.toDomain()))
                    println("The Response, ${response.body()}")
                } else {
                    throw HttpException(response)
                }
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

    override suspend fun authenticate(): Flow<Resource<AuthResponse>> {

        return flow {
            emit(Resource.Loading())
            try {
                val saveRep = saveToken.getAuthDataPref() ?: throw Exception("Token is null or empty")
                if (saveRep.token.isEmpty()) throw Exception("Token is null or empty")
                val response=api.authenticateUser(token = "Bearer ${saveRep.token}")
                if(response.isSuccessful){

                    emit(Resource.Success(data = saveRep))
                } else{
                    throw HttpException(response)
                }
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
    override suspend fun saveAuthToken(authResponse: AuthResponse) {
        pref.edit().putString("token", authResponse.token).apply()
        pref.edit().putString("email", authResponse.email).apply()
        pref.edit().putString("userName", authResponse.userName).apply()
        pref.edit().putString("fullName", authResponse.fullName).apply()
        pref.edit().putString("phoneNumber", authResponse.phoneNumber).apply()
        pref.edit().putString("last_menses_date", authResponse.last_menses_date).apply()
        pref.edit().putInt("cycle_length", authResponse.cycleLength).apply()
        pref.edit().putInt("period_length", authResponse.periodLength).apply()
    }

    override suspend fun getAuthDataPref(): AuthResponse? {
        return AuthResponse(
            token = pref.getString("token", "")!!,
            email = pref.getString("email", "")!!,
            userName = pref.getString("userName", "")!!,
            fullName = pref.getString("fullName", "")!!,
            phoneNumber = pref.getString("phoneNumber", "")!!,
            last_menses_date = pref.getString("last_menses_date", "")!!,
            cycleLength = pref.getInt("cycle_length", 0),
            periodLength = pref.getInt("period_length", 0)
        )
    }
}

