package com.wantech.noshame.featureMenstrualTrack.presentation.home.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun InSightsItems(
    modifier: Modifier,
    insightItems: List<InSightsItemModel>,
    onInsightItemClick: (InSightsItemModel) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        LazyRow {
            itemsIndexed(items = insightItems) { index, item ->
                InSightsItem(
                    modifier = Modifier, inSightsItemModel = item,
                    onInsightItemClick = {
                        onInsightItemClick(it)
                    }

                )
                Spacer(modifier = Modifier.width(8.dp))
            }
        }
    }

}