package com.wantech.noshame.explore.presentation.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun ExploreScreenUi() {
    Surface(color = MaterialTheme.colorScheme.background, modifier = Modifier.fillMaxSize()) {


    }
}


@Preview(showBackground = true, showSystemUi = true, device = "id:pixel_3a")
@Composable
fun ExploreScreenPreview() {
    ExploreScreenUi()
}