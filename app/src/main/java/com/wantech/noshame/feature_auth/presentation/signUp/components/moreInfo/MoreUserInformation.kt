package com.wantech.noshame.feature_auth.presentation.signUp.components.moreInfo

import com.wantech.noshame.core.util.UiText
import com.wantech.noshame.feature_auth.domain.model.response.AuthResponse
import com.wantech.noshame.feature_auth.presentation.signUp.components.AuthDetails
import java.time.LocalDate



data class MoreInfoState(
    val authDetails: AuthDetails? = null,
    val isFinishBtnEnabled: Boolean = false,
    val periodLengthEnabled: Boolean = false,
    val cycleLengthEnabled: Boolean = false,
    val dayOneOfPreviousCycle: LocalDate?=null,
    val cycleLength: Int=0,
    val periodLength: Int=0,
    val isLoading: Boolean = false,
    val signUp: AuthResponse? = null,
    val error: UiText? = null

)


sealed class MoreInfoEvent {
    data class PreviousCycleDate(val date: LocalDate) : MoreInfoEvent()
    data class PeriodLength(val length: Int) : MoreInfoEvent()
    data class CycleLength(val cycle: Int) : MoreInfoEvent()
    data class AuthDetailsUpdate(val authDetails: AuthDetails) : MoreInfoEvent()
    object FinishSignUp : MoreInfoEvent()

}
