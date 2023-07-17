package com.wantech.noshame.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.wantech.noshame.feature_auth.data.network.AuthApi
import com.wantech.noshame.feature_auth.data.repositoryImpl.AuthRepositoryImpl
import com.wantech.noshame.feature_auth.data.repositoryImpl.SaveAuthTokenInSharedPref
import com.wantech.noshame.feature_auth.domain.repository.AuthRepository
import com.wantech.noshame.feature_auth.domain.repository.SaveAuthToken
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    private const val BaseUrl = "http://192.168.1.148:8080/"

    @Singleton
    @Provides
    fun providesHttpLoggingInterceptor() = HttpLoggingInterceptor()
        .apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

    @Singleton
    @Provides
    fun providesOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient
            .Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BaseUrl)
        .client(okHttpClient)
        .build()

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): AuthApi = retrofit.create(AuthApi::class.java)

    @Provides
    @Singleton
    fun provideAuthRepository(
        api: AuthApi,
        saveAuthToken: SaveAuthToken
    ): AuthRepository = AuthRepositoryImpl(api = api, saveToken = saveAuthToken)

    @Provides
    @Singleton
    fun provideSharedPreferences( app: Application): SharedPreferences =
        app.getSharedPreferences("sharedPref", Context.MODE_PRIVATE)

    @Provides
    @Singleton
    fun provideSaveAuthToken(sharedPreferences: SharedPreferences): SaveAuthToken =
        SaveAuthTokenInSharedPref(pref = sharedPreferences)
}