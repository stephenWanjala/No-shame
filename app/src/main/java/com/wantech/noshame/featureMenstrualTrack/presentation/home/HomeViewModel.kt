package com.wantech.noshame.featureMenstrualTrack.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wantech.noshame.core.util.Resource
import com.wantech.noshame.core.util.UiText
import com.wantech.noshame.featureMenstrualTrack.domain.model.CycleDetails
import com.wantech.noshame.featureMenstrualTrack.presentation.util.FertilityStatus
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
class HomeViewModel @Inject constructor(
    private val repository: AuthRepository
) : ViewModel() {
    private val _state = MutableStateFlow(HomeUiState())
    val state = _state.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), HomeUiState())

    init {
        getCycleDetails()
    }

    private fun getCycleDetails() {
        viewModelScope.launch {
            repository.getCycleDetails().collectLatest { resource ->
                when (resource) {
                    is Resource.Error -> {
                        _state.update {
                            it.copy(error = resource.uiText, isLoading = false)
                        }
                    }

                    is Resource.Loading -> {
                        _state.update {
                            it.copy(isLoading = true)
                        }
                    }

                    is Resource.Success -> {
                        resource.data?.let { cycleDetails ->
                            _state.update {
                                it.copy(cycleDetails = cycleDetails, isLoading = false)
                            }
                        }
                    }
                }
            }
        }

    }

}

data class HomeUiState(
    val cycleDetails: CycleDetails = CycleDetails(
        ovulationDay = 0,
        fertileDays = emptyList(),
        safeDays = emptyList(),
        flowDays = emptyList(),
        daysRemainingToNextFlow = 0,
        currentDayInCycle = 0,
        fertilityStatus = emptyMap(),
        fertileDates = emptyList(),
        safeDates = emptyList(),
        cycleLength = 0,
        lastMensesDate = LocalDate.now(),
        periodLength = 0,
        fertilityStatusToday = FertilityStatus.NONE,
        currentDateInCycle = LocalDate.now()

    ),
    val isLoading: Boolean = false,
    val error: UiText? = null
)
