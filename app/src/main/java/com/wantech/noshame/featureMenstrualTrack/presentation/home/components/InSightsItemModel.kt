package com.wantech.noshame.featureMenstrualTrack.presentation.home.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CleanHands
import androidx.compose.material.icons.filled.QueryStats
import androidx.compose.material.icons.filled.Woman2
import androidx.compose.ui.graphics.vector.ImageVector

data class InSightsItemModel(
    val itemName: String,
    val itemIcon: ImageVector,
) {

    companion object {
        val InsightItems = listOf(
            InSightsItemModel(
                itemName = "Hygiene tips",
                Icons.Default.CleanHands
            ),
            InSightsItemModel(
                itemName = "Menstrual myths",
                Icons.Default.Woman2
            ),
            InSightsItemModel(itemName = "FAQs", Icons.Default.QueryStats),


            )
    }
}
