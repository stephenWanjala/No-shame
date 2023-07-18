package com.wantech.noshame.feature_auth.presentation.signUp.components.moreInfo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class MoreInfoViewModel @Inject constructor() : ViewModel() {


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

}
