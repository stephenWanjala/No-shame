package com.wantech.noshame.fAQs.presentation.components

import androidx.compose.animation.*
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun CardWithToggleableDescription(
    title: String,
    description: String,
    modifier: Modifier = Modifier
) {
    var isExpanded by remember { mutableStateOf(false) }
    val icon = if (isExpanded) Icons.Default.Remove else Icons.Default.Add

    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 4.dp)
            .animateContentSize { _, _ ->
                spring(
                    stiffness = Spring.StiffnessLow,
                    dampingRatio = Spring.DampingRatioHighBouncy,
                    visibilityThreshold = 0.001f
                )
            }
    ) {
        Column(modifier = Modifier
            .fillMaxWidth()
            .clickable { isExpanded = !isExpanded }
            .padding(horizontal = 16.dp, vertical = 8.dp)

        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = title,
                    fontWeight = FontWeight.Bold,
                    style= MaterialTheme.typography.titleMedium,
                    modifier = Modifier.weight(1f)
                )
                IconButton(
                    onClick = { isExpanded = !isExpanded }
                ) {
                    Icon(
                        icon,
                        contentDescription = if (isExpanded) "Collapse" else "Expand"
                    )
                }
            }
            AnimatedVisibility(
                visible = isExpanded,
                enter = fadeIn() + expandVertically() + slideInVertically() + expandHorizontally() + slideInHorizontally() + expandIn(),
                exit = fadeOut() + shrinkVertically() + slideOutVertically() + shrinkHorizontally() + slideOutHorizontally() + shrinkOut()
            ) {
                Text(
                    text = description,
                    fontSize = 14.sp,
                    modifier = Modifier
                        .padding(top = 8.dp)
                        .animateEnterExit(
                            enter = if (isExpanded) slideInVertically() + expandVertically() else slideInHorizontally() + expandHorizontally(),
                            exit = if (isExpanded) slideOutVertically() + shrinkVertically() else slideOutHorizontally() + shrinkHorizontally()
                        )
                )
            }

        }
    }
}