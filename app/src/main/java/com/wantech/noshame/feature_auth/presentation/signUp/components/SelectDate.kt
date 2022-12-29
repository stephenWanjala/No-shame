package com.wantech.noshame.feature_auth.presentation.signUp.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectDate(
    onCLicKSelectDate: (String) -> Unit,
    modifier: Modifier

) {
    val selectedDateState by remember {
        mutableStateOf("")
    }
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),

        ) {
        OutlinedTextField(
            value = selectedDateState,
            onValueChange = onCLicKSelectDate,
            trailingIcon = {
                Icon(
                    imageVector = Icons.Default.CalendarMonth,
                    contentDescription = "select date"
                )
            },
            placeholder = {
                Text(text = "Day One of previous period")
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            ),
            colors = TextFieldDefaults.textFieldColors(
                unfocusedIndicatorColor = MaterialTheme.colorScheme.background,
                unfocusedLabelColor = MaterialTheme.colorScheme.background,
                placeholderColor = MaterialTheme.colorScheme.background,
            ),
            shape = RoundedCornerShape(10.dp)
        )
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewSelectDate() {
    SelectDate(onCLicKSelectDate = {}, modifier = Modifier)
}