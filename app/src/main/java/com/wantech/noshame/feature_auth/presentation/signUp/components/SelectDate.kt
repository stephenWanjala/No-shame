package com.wantech.noshame.feature_auth.presentation.signUp.components

import androidx.compose.foundation.clickable
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.datetime.date.datepicker
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import com.wantech.noshame.R
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectDate(
    onCLicKSelectDate: (String) -> Unit,
    modifier: Modifier,
    checkSelectedDateState: (LocalDate?) -> Boolean

) {
    var selectedDate: LocalDate? by remember {
        mutableStateOf(null)
    }
    val formatter = DateTimeFormatter.ofPattern("EE, d MMM yyyy ")
    val formattedDte by remember {
        derivedStateOf {
            selectedDate?.format(formatter)
        }
    }
    val datePickerDialogState = rememberMaterialDialogState()

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),

        ) {
        OutlinedTextField(
            value = formattedDte ?: "",
            onValueChange = onCLicKSelectDate,
            trailingIcon = {
                Icon(
                    imageVector = Icons.Default.CalendarMonth,
                    contentDescription = "select date",
                    modifier = Modifier.clickable {
                        datePickerDialogState.show()
                    }
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
        MaterialDialog(dialogState = datePickerDialogState,
            buttons = {
                positiveButton(text = stringResource(R.string.ok))
                negativeButton(text = stringResource(R.string.cancel))
            }) {
            datepicker(
                initialDate = selectedDate ?: LocalDate.now(),
                title = "select Date",
                allowedDateValidator = {
                    it.isBefore(LocalDate.now()) && it.isAfter(
                        LocalDate.now().minusDays(33)
                    )
                }
            ) {
                selectedDate = it
                checkSelectedDateState(selectedDate)

            }
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewSelectDate() {
    SelectDate(onCLicKSelectDate = {}, modifier = Modifier, { true })
}