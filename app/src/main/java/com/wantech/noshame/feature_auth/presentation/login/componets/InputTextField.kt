package com.wantech.noshame.feature_auth.presentation.login.componets

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun InputTextField(
    modifier: Modifier = Modifier,
    textValue: String,
    labelText: String,
    maxLines: Int = 1,
    onValueChange: (String) -> Unit,
    keyboardOptions: KeyboardOptions = KeyboardOptions(
        imeAction = ImeAction.Next,
        keyboardType = KeyboardType.Email
    ),
    visualTransformation: VisualTransformation = VisualTransformation.None,
    onSendAction: (() -> Unit?)? = null

) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        contentAlignment = Alignment.Center
    ) {
        val keyBoardController = LocalSoftwareKeyboardController.current
        OutlinedTextField(
            value = textValue,
            onValueChange = onValueChange,
            keyboardOptions = keyboardOptions,
            label = { Text(text = labelText) },
            placeholder = {
                Text(text = labelText)
            },
            keyboardActions = KeyboardActions(
                onDone = {
                    keyBoardController?.hide()
                },
                onSend = {
                    keyBoardController?.hide()
                    onSendAction?.let { it() }
                }
            ),
            visualTransformation = visualTransformation,
            maxLines = maxLines,
            shape = RoundedCornerShape(10.dp)
        )

    }

}

