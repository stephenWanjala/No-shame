package com.wantech.noshame.feature_auth.presentation.signUp.components.moreInfo

import android.content.pm.ActivityInfo
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.wantech.noshame.feature_auth.presentation.components.NoShameSpinner
import com.wantech.noshame.feature_auth.presentation.components.SpinnerData
import com.wantech.noshame.feature_auth.presentation.login.ATextButton
import com.wantech.noshame.feature_auth.presentation.signUp.components.SelectDate
import com.wantech.noshame.feature_auth.presentation.util.LockScreenOrientation
import com.wantech.noshame.feature_auth.presentation.util.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MoreInformationScreen(
    navController: NavHostController,
    viewModel: MoreInfoViewModel = hiltViewModel()
) {
    LockScreenOrientation(orientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
    Scaffold { paddingValues ->

        val state = viewModel.state
        var dateNotNull by remember {
            mutableStateOf(false)
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                shape = RoundedCornerShape(12.dp),

                ) {
                SelectDate(
                    modifier = Modifier.padding(top = 8.dp),
                    checkSelectedDateState = { date ->
                        dateNotNull = date != null
                        if (dateNotNull) {
                            viewModel.onEvent(MoreInfoEvent.PreviousCycleDate(date!!))
                        }
                        dateNotNull
                    },
                    viewModel = viewModel
                )
                NoShameSpinner(
                    list = listOf(
                        SpinnerData(21, "21 days"),
                        SpinnerData(22, "22 days"),
                        SpinnerData(23, "23 days"),
                        SpinnerData(24, "24 days"),
                        SpinnerData(25, "25 days"),
                        SpinnerData(28, "28 days"),
                        SpinnerData(30, "30 days"),
                    ),
                    preselected = SpinnerData(
                        state.value.cycleLength,
                        if (state.value.cycleLength == 0) "Length of your cycle" else "${state.value.cycleLength} days"
                    ),
                    onSelectionChanged = {
//                        periodLengthEnable = true
                        viewModel.onEvent(MoreInfoEvent.CycleLength(it.id))
                    },
                    isEnabled = { state.value.cycleLengthEnabled }
                )
                NoShameSpinner(
                    list = listOf(
                        SpinnerData(4, "4 days"),
                        SpinnerData(5, "5 days"),
                        SpinnerData(6, "6 days"),
                        SpinnerData(7, "7 days"),
                    ),
                    preselected = SpinnerData(
                        state.value.periodLength,
                        if (state.value.periodLength == 0) "Length of your period" else "${state.value.periodLength} days"
                    ),
                    onSelectionChanged = {
//                        enableFinishButton = true
                        viewModel.onEvent(MoreInfoEvent.PeriodLength(it.id))
                    },

                    isEnabled = { state.value.periodLengthEnabled }
                )


                ATextButton(
                    text = "Finish",
                    onClick = {
                        navController.navigate("home_nav") {
                            popUpTo(Screen.Home.route) {
                                inclusive = true
                            }

                        }
                    },
                    modifier = Modifier,
                    buttonEnabled = {
                        state.value.isFinishBtnEnabled
                    },
                )

            }
        }
    }
}