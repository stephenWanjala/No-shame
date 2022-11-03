package com.wantech.noshame.featureMenstrualTrack.presentation.home.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeTopBar(
    modifier: Modifier = Modifier,
    tittle: String,
    navIcon: ImageVector
) {

    TopAppBar( modifier= Modifier.fillMaxWidth(),
       scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior(),
       title = {

       },
        colors = TopAppBarDefaults.smallTopAppBarColors()

    )

}