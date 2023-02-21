package com.wantech.noshame.feature_auth.presentation.signUp.components.moreInfo

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class MoreInfoViewModel @Inject constructor() : ViewModel() {

    private val _state = mutableStateOf(InfoState())
    val state: State<InfoState> = _state

    private val _uiState = MutableStateFlow(MoreInfoState())
    val uiState = _uiState.asSharedFlow()


    fun onEvent(event: MoreInfoEvent) {
        when (event) {
            is MoreInfoEvent.CycleLength -> {
                _state.value = state.value.copy(cycleLength = event.cycle)
                if (state.value.cycleLength != 0) _state.value =
                    state.value.copy(periodLengthEnabled = true)
            }
            MoreInfoEvent.FinishSignUp -> TODO()
            is MoreInfoEvent.PeriodLength -> {
                _state.value = state.value.copy(periodLength = event.length)
                if (_state.value.periodLength != 0) _state.value =
                    state.value.copy(isFinishBtnEnabled = true)
            }
            is MoreInfoEvent.PreviousCycleDate -> {
                _state.value = state.value.copy(previousCycleDate = event.date)
                if (_state.value.previousCycleDate != null) _state.value =
                    state.value.copy(cycleLengthEnabled = true)
            }
        }


    }

}


data class InfoState(
    val previousCycleDate: LocalDate? = null,
    val cycleLength: Int = 0,
    val periodLength: Int = 0,
    val isFinishBtnEnabled: Boolean = false,
    val periodLengthEnabled: Boolean = false,
    val cycleLengthEnabled: Boolean = false
)
