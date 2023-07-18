package com.wantech.noshame.feature_auth.presentation.signUp.components.moreInfo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wantech.noshame.core.util.Resource
import com.wantech.noshame.feature_auth.domain.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class MoreInfoViewModel @Inject constructor(
    private val repository: AuthRepository
) : ViewModel() {


    private val _state = MutableStateFlow(MoreInfoState())
    val state =
        _state.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), MoreInfoState())


    fun onEvent(event: MoreInfoEvent) {
        when (event) {
            is MoreInfoEvent.CycleLength -> {
                _state.update { it.copy(cycleLength = event.cycle) }
                if (state.value.cycleLength != 0) _state.update { it.copy(periodLengthEnabled = true) }
            }

            MoreInfoEvent.FinishSignUp -> {
                state.value.authDetails?.let { authDetails ->
                    state.value.dayOneOfPreviousCycle?.let { lastMensesDate ->
                        signUp(
                            userName = authDetails.userName,
                            email = authDetails.email,
                            password = authDetails.password,
                            lastMensesDate = lastMensesDate,
                            cycleLength = state.value.cycleLength,
                            periodLength = state.value.periodLength
                        )
                    }

                }

            }

            is MoreInfoEvent.PeriodLength -> {
                _state.value = state.value.copy(periodLength = event.length)
                if (_state.value.periodLength != 0) _state.update { it.copy(isFinishBtnEnabled = true) }
            }

            is MoreInfoEvent.PreviousCycleDate -> {
                _state.update { it.copy(dayOneOfPreviousCycle = event.date) }
                if (_state.value.dayOneOfPreviousCycle != null) _state.update {
                    it.copy(
                        cycleLengthEnabled = true
                    )
                }
            }

            is MoreInfoEvent.AuthDetailsUpdate -> {
                _state.update { it.copy(authDetails = event.authDetails) }
            }
        }


    }

    private fun signUp(
        userName: String,
        email: String,
        password: String,
        fullName: String? = null,
        phoneNumber: String? = null,
        lastMensesDate: LocalDate,
        cycleLength: Int,
        periodLength: Int
    ) {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            val response = repository.createUserWithEmailAndPassword(
                userName = userName,
                email = email,
                password = password,
                fullName = fullName,
                phoneNumber = phoneNumber,
                lastMensesDate = lastMensesDate,
                cycleLength = cycleLength,
                periodLength = periodLength
            )
            response.collectLatest { authResource ->
                when (authResource) {
                    is Resource.Error -> {
                        _state.update { it.copy(error = authResource.uiText) }
                    }

                    is Resource.Loading -> {
                        _state.update { it.copy(isLoading = true) }
                    }

                    is Resource.Success -> {
                        authResource.data?.let { authResponse ->
                            _state.update { it.copy(signUp = authResponse) }
                        }
                    }
                }

            }
        }
    }

}
