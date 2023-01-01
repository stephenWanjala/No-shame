package com.wantech.noshame.feature_auth.presentation.signUp

import androidx.lifecycle.ViewModel
import com.wantech.noshame.feature_auth.domain.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val repository: AuthRepository

) : ViewModel()