package com.wantech.noshame.featureMenstrualTrack.presentation.home.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CleanHands
import androidx.compose.material.icons.filled.QueryStats
import androidx.compose.material.icons.filled.Woman2
import androidx.compose.ui.graphics.vector.ImageVector
import com.wantech.noshame.destinations.Destination
import com.wantech.noshame.destinations.FAQSScreenDestination
import com.wantech.noshame.destinations.HygieneTipsScreenDestination
import com.wantech.noshame.destinations.MenstrualMythsScreenDestination

data class InSightsItemModel(
    val itemName: String,
    val itemIcon: ImageVector,
    val typedDestination: Destination
) {

    companion object {
        val InsightItems = listOf(
            InSightsItemModel(
                itemName = "Hygiene tips",
                Icons.Default.CleanHands,
                typedDestination = HygieneTipsScreenDestination
            ),
            InSightsItemModel(
                itemName = "Menstrual myths",
                Icons.Default.Woman2,
                typedDestination = MenstrualMythsScreenDestination
            ),
            InSightsItemModel(
                itemName = "FAQs", Icons.Default.QueryStats,
                typedDestination = FAQSScreenDestination
            ),


            )
    }
}
