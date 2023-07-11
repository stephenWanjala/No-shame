package com.wantech.noshame.di

import com.wantech.noshame.feature_auth.data.repositoryImpl.AuthRepositoryImpl
import com.wantech.noshame.feature_auth.domain.repository.AuthRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {



    @Provides
    @Singleton
    fun provideAuthRepository(): AuthRepository = AuthRepositoryImpl()
}