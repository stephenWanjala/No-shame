package com.wantech.noshame.featureMenstrualTrack.presentation.home

import android.content.pm.ActivityInfo
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Segment
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.wantech.noshame.R
import com.wantech.noshame.destinations.FAQSScreenDestination
import com.wantech.noshame.destinations.MenstrualMythsScreenDestination
import com.wantech.noshame.featureMenstrualTrack.presentation.home.components.DayStatistics
import com.wantech.noshame.featureMenstrualTrack.presentation.home.components.HomeTopBar
import com.wantech.noshame.featureMenstrualTrack.presentation.home.components.InSightsItemModel
import com.wantech.noshame.featureMenstrualTrack.presentation.home.components.InSightsItems
import com.wantech.noshame.featureMenstrualTrack.presentation.home.components.StatsSection
import com.wantech.noshame.feature_auth.presentation.util.LockScreenOrientation
import com.wantech.noshame.feature_auth.presentation.util.Screen
import java.time.LocalDate

@Destination
@Composable
fun HomeScreen(
    navigator: DestinationsNavigator,
    viewModel: HomeViewModel = hiltViewModel()
) {
    LockScreenOrientation(orientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)

    val state = viewModel.state.collectAsState().value




    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.surface
    ) {


        Scaffold(
            modifier = Modifier.fillMaxWidth(),

            topBar = {

                HomeTopBar(
                    tittle = stringResource(id = R.string.app_name),
                    navIcon = Icons.AutoMirrored.Filled.Segment,
                ) {

                }
            }
        ) { paddingValues ->


            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxSize()
                    .padding(paddingValues),

                ) {

                if (state.isLoading) {
                    AnimatedVisibility(visible = state.isLoading) {
                        LinearProgressIndicator()
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = "Loading...",
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center
                        )
                    }
                } else {
                    StatsSection(
                        averageCycleLength = state.cycleDetails.cycleLength,
                        averageFlow = state.cycleDetails.flowDays.count()
                    )
                    LazyColumn {
                        item {
                            InSightsItems(modifier = Modifier,
                                insightItems = InSightsItemModel.InsightItems,
                                onInsightItemClick = { inSightsItemModel, _ ->
                                    when (inSightsItemModel.itemName) {
                                        Screen.FAQScreen.route -> {
                                            navigator.navigate(FAQSScreenDestination.route)
                                        }

                                        Screen.MythsScreen.route -> {
                                            navigator.navigate(MenstrualMythsScreenDestination.route)
                                        }
                                    }
                                })
                        }

                        item {
                            DayStatistics(
                                today = LocalDate.now(),
                                fertilityStatus = state.cycleDetails.fertilityStatusToday,
                            )
                        }

                        item {
                            Spacer(modifier = Modifier.height(16.dp))
                            Text(
                                text = "Previous Cycle", modifier = Modifier.padding(16.dp),
                                style = MaterialTheme.typography.headlineSmall,
                                textAlign = TextAlign.Center
                            )

//                        PreviousCycleFlow(selections = selections)

                        }
                    }
                }


            }
        }

    }

}