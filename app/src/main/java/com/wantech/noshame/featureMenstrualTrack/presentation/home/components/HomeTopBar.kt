package com.wantech.noshame.featureMenstrualTrack.presentation.home.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun HomeTopBar(
    modifier: Modifier = Modifier, tittle: String, navIcon: ImageVector
) {


    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp),
    ) {
        Text(
            text = tittle,
            modifier = Modifier.align(Alignment.TopStart)
        )

        Icon(
            imageVector = navIcon, contentDescription = "Nav icon",
            modifier = Modifier.align(Alignment.TopEnd)
        )
    }


}