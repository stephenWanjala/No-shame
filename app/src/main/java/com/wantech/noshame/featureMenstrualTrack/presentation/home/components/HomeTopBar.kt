package com.wantech.noshame.featureMenstrualTrack.presentation.home.components

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeTopBar(
    modifier: Modifier = Modifier, tittle: String, navIcon: ImageVector,
    onclickNavIcon: () -> Unit
) {
    TopAppBar(
        modifier = modifier,
        title = {
            Text(
                text = tittle,
                textAlign = androidx.compose.ui.text.style.TextAlign.Center
            )
        },
        actions = {
            IconButton(
                onClick = { onclickNavIcon() },
            ) {
                Icon(
                    imageVector = navIcon, contentDescription = "Nav icon",

                    )
            }

        })
}