package com.wantech.noshame.feature_auth.presentation.signUp.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun IConWithText(
    modifier: Modifier,
    onClick: () -> Unit,
    icon: ImageVector = Icons.Default.ArrowBack,
    text: String = "Create Account"
) {
    Row(
        modifier = modifier
            .fillMaxWidth(),
//            .align(Alignment.TopCenter),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        IconButton(
            onClick = onClick,
            modifier = Modifier
                .padding(bottom = 8.dp)
                .align(Alignment.CenterVertically)
        ) {
            Icon(
                imageVector = icon, contentDescription = "Back",
                modifier = Modifier.align(Alignment.Top)
            )
        }
        Text(
            text = text,
            style = MaterialTheme.typography.headlineSmall,
            fontFamily = FontFamily.Monospace,
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.background)
                .padding(start = 32.dp, bottom = 16.dp, end = 32.dp)
        )

    }
}

@Composable
fun Header(headerText: String, headerEndText: String, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = headerText,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.headlineSmall,
        )
        Text(
            text = headerEndText, textAlign = TextAlign.Center,
            style = MaterialTheme.typography.labelMedium,
        )
    }
}