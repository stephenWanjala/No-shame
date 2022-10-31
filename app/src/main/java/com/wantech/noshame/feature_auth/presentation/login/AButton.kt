package com.wantech.noshame.feature_auth.presentation.login

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp

@Composable
fun AButton(
    text:String,
    onClick:()->Unit,
    modifier: Modifier,
    buttonEnabled:()->Boolean
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 32.dp, end = 32.dp)
            .clip(RoundedCornerShape(10.dp)),
        elevation = ButtonDefaults.elevation(0.dp),
        enabled = buttonEnabled()
    ) {
        Text(text = text,
            style = MaterialTheme.typography.button)

    }
}