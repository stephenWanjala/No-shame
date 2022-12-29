package com.wantech.noshame.feature_auth.presentation.signUp.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.wantech.noshame.feature_auth.presentation.components.NoShameSpinner
import com.wantech.noshame.feature_auth.presentation.components.SpinnerData
import com.wantech.noshame.feature_auth.presentation.login.ATextButton
import com.wantech.noshame.feature_auth.presentation.util.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MoreInformationScreen(navController: NavHostController) {
    Scaffold {
        val unUsedPadding = it.calculateTopPadding()

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
//            contentColor = MaterialTheme.colors.surface,
//            backgroundColor = MaterialTheme.colors.onBackground,
                shape = RoundedCornerShape(12.dp),

                ) {
                SelectDate(modifier = Modifier.padding(top = 8.dp), onCLicKSelectDate = {})
                NoShameSpinner(
                    list = listOf(
                        SpinnerData(21, "21 days"),
                        SpinnerData(28, "28 days"),
                        SpinnerData(30, "30 days"),
                    ),
                    preselected = SpinnerData(0, "Length of your cycle"),
                    onSelectionChanged = {

                    },
                    isEnabled = { false }
                )
                NoShameSpinner(
                    list = listOf(
                        SpinnerData(4, "4 days"),
                        SpinnerData(5, "5 days"),
                        SpinnerData(6, "6 days"),
                        SpinnerData(7, "7 days"),
                    ),
                    preselected = SpinnerData(0, "period length"),
                    onSelectionChanged = {

                    },
                    isEnabled = { false }
                )


                ATextButton(
                    text = "Finish",
                    onClick = {
                        navController.navigate(Screen.Home.route) {
                            popUpTo(Screen.Home.route) {
                                inclusive = true
                            }

                        }
                    },
                    modifier = Modifier,
                    buttonEnabled = {
                        false
                    },
                )

            }
        }
    }
}