package com.wantech.noshame.featureMenstrualTrack.presentation.home

import android.content.pm.ActivityInfo
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Segment
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.wantech.noshame.featureMenstrualTrack.presentation.home.components.*
import com.wantech.noshame.featureMenstrualTrack.presentation.util.FertilityStatus
import com.wantech.noshame.feature_auth.presentation.util.LockScreenOrientation
import java.time.LocalDate

@OptIn(ExperimentalMaterial3Api::class)

@Composable
fun HomeScreen() {
    LockScreenOrientation(orientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.surface
    ) {
        val selectedDates: MutableList<LocalDate> = mutableListOf()
        (1..5).forEach { num ->
            selectedDates.add(LocalDate.now().minusDays(num.toLong()))
        }




        Scaffold(
            modifier = Modifier.fillMaxWidth(),

            topBar = {

                HomeTopBar(
                    tittle = "No shame", navIcon = Icons.Default.Segment,
                ) {

                }
            }
        ) {

            it.calculateBottomPadding()
            Column(
                modifier = Modifier.fillMaxWidth(),

                ) {
                StatsSection()

                LazyColumn {
                    item {
                        InSightsItems(modifier = Modifier,
                            insightItems = InSightsItemModel.InsightItems,
                            onInsightItemClick = { inSightsItemModel, index ->

                            })
                    }

                    item {
                        DayStatistics(
                            today = LocalDate.now(),
                            fertilityStatus = FertilityStatus.High
                        )
                    }

                }

            }
        }

    }

}