package com.wantech.noshame.featureMenstrualTrack.presentation.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Segment

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.wantech.noshame.featureMenstrualTrack.presentation.home.components.HomeTopBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavHostController) {

//    val scaffoldState = rememberScaffoldState()
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.surface
    ) {
        Scaffold(
            modifier = Modifier.fillMaxWidth(),

            topBar = {
                HomeTopBar(tittle = "No shame", navIcon = Icons.Default.Segment)
            }
        ) {

            it.calculateBottomPadding()
        }

    }

}