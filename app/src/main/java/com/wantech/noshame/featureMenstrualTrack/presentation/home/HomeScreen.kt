package com.wantech.noshame.featureMenstrualTrack.presentation.home

import android.content.pm.ActivityInfo
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Segment
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.wantech.noshame.R
import com.wantech.noshame.featureMenstrualTrack.presentation.home.components.*
import com.wantech.noshame.featureMenstrualTrack.presentation.home.components.calendeTrack.PreviousCycleFlow
import com.wantech.noshame.featureMenstrualTrack.presentation.util.FertilityStatus
import com.wantech.noshame.feature_auth.presentation.util.LockScreenOrientation
import com.wantech.noshame.feature_auth.presentation.util.Screen
import java.time.LocalDate

@OptIn(ExperimentalMaterial3Api::class)

@Composable
fun HomeScreen(navHostController: NavHostController) {
    LockScreenOrientation(orientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.surface
    ) {
        val selections: SnapshotStateList<LocalDate> = remember { mutableStateListOf<LocalDate>() }
        val selectedDates: MutableList<LocalDate> = mutableListOf()
        (6..10).forEach { num ->
            selectedDates.add(LocalDate.now().minusDays(num.toLong()))
        }
        selections.addAll(selectedDates.asReversed())




        Scaffold(
            modifier = Modifier.fillMaxWidth(),

            topBar = {

                HomeTopBar(
                    tittle = stringResource(id = R.string.app_name),
                    navIcon = Icons.Default.Segment,
                ) {

                }
            }
        ) { paddingValues ->


            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(paddingValues),

                ) {
                StatsSection()

                LazyColumn {
                    item {
                        InSightsItems(modifier = Modifier,
                            insightItems = InSightsItemModel.InsightItems,
                            onInsightItemClick = { inSightsItemModel, index ->
                                when (inSightsItemModel.itemName) {
                                    Screen.FAQScreen.route -> {
                                        navHostController.navigate(Screen.FAQScreen.route)
                                    }
                                }
                            })
                    }

                    item {
                        DayStatistics(
                            today = LocalDate.now(),
                            fertilityStatus = FertilityStatus.High
                        )
                    }

                    item {
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = "Previous Cycle", modifier = Modifier.padding(16.dp),
                            style = MaterialTheme.typography.headlineSmall,
                            textAlign = TextAlign.Center
                        )

                        PreviousCycleFlow(selections = selections)

                    }
                }


            }
        }

    }

}