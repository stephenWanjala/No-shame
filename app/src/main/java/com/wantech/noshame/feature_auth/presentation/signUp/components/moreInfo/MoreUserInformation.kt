package com.wantech.noshame.feature_auth.presentation.signUp.components.moreInfo

import com.wantech.noshame.feature_auth.presentation.signUp.components.AuthDetails
import java.time.LocalDate

data class MoreUserInformation(
    val dayOneOfPreviousCycle: LocalDate,
    val cycleLength: Int,
    val periodLength: Int,
)

data class MoreInfoState(
    val info: MoreUserInformation? = null,
    val authDetails: AuthDetails? = null,
    val isFinishBtnEnabled: Boolean = false,
    val periodLengthEnabled: Boolean = false,
    val cycleLengthEnabled: Boolean = false
)


sealed class MoreInfoEvent {
    data class PreviousCycleDate(val date: LocalDate) : MoreInfoEvent()
    data class PeriodLength(val length: Int) : MoreInfoEvent()
    data class CycleLength(val cycle: Int) : MoreInfoEvent()
    data class AuthDetailsUpdate(val authDetails: AuthDetails) : MoreInfoEvent()
    object FinishSignUp : MoreInfoEvent()

}
