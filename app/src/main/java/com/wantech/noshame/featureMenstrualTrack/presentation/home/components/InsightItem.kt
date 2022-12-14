package com.wantech.noshame.featureMenstrualTrack.presentation.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Woman
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun InSightsItem(
    modifier: Modifier,
    inSightsItemModel: InSightsItemModel,
    onInsightItemClick: (InSightsItemModel) -> Unit
) {
    Column(
        modifier = modifier
            .size(100.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(
                color = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.5f), shape = RoundedCornerShape(20.dp)
            )
            .clickable { onInsightItemClick(inSightsItemModel) },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            imageVector = inSightsItemModel.itemIcon,
            contentDescription = inSightsItemModel.itemName
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = inSightsItemModel.itemName,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Preview(showBackground = true, )
@Composable
fun InsightItemPreview() {
    InSightsItem(modifier = Modifier,
        inSightsItemModel = InSightsItemModel(itemIcon = Icons.Default.Woman, itemName = "woman"),
        onInsightItemClick = {})
}


