package com.wantech.noshame.feature_auth.presentation.login

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun AButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier,
    buttonEnabled: () -> Boolean,
    leadingIcon: ImageVector? = null
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 32.dp, end = 32.dp)
            .clip(RoundedCornerShape(10.dp)),
        elevation = ButtonDefaults.buttonElevation(0.dp),
        enabled = buttonEnabled()
    ) {
        Text(
            text = text, style = MaterialTheme.typography.bodyMedium
        )
        if (leadingIcon != null) {
            Spacer(modifier = modifier.width(4.dp))
            Icon(


                imageVector = leadingIcon, contentDescription = text,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .size(15.dp)
            )
        }

    }
}


@Composable
fun ATextButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier,
    buttonEnabled: () -> Boolean,
    leadingIcon: ImageVector? = null,
    enabledColor: Color = MaterialTheme.colorScheme.surface
) {
    OutlinedButton(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 32.dp, end = 32.dp, bottom = 8.dp),
//            .clip(RoundedCornerShape(10.dp)),
        elevation = ButtonDefaults.buttonElevation(0.dp),
        enabled = buttonEnabled(),
        colors = ButtonDefaults.buttonColors(contentColor = enabledColor),
        contentPadding = PaddingValues(2.dp)
    ) {
        Text(
            text = text, style = MaterialTheme.typography.bodyMedium
        )
        if (leadingIcon != null) {
            Spacer(modifier = modifier.width(4.dp))
            Icon(
                imageVector = leadingIcon, contentDescription = text,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .size(15.dp)
            )
        }

    }
}