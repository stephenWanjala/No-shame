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
import androidx.compose.ui.unit.dp
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.datetime.date.DatePickerDefaults
import com.vanpra.composematerialdialogs.datetime.date.datepicker
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import com.wantech.noshame.R
import com.wantech.noshame.feature_auth.presentation.signUp.components.moreInfo.MoreInfoEvent
import com.wantech.noshame.feature_auth.presentation.signUp.components.moreInfo.MoreInfoViewModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun SelectDate(
    modifier: Modifier,
    checkSelectedDateState: (LocalDate?) -> Boolean,
    viewModel: MoreInfoViewModel

) {
    var selectedDate: LocalDate? by remember {
        mutableStateOf(null)
    }
    val formatter = DateTimeFormatter.ofPattern("EE, d MMM yyyy ")
    val datePickerDialogState = rememberMaterialDialogState()

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),

        ) {
        OutlinedTextField(
            value = viewModel.state.value.dayOneOfPreviousCycle?.format(formatter) ?: "",
            onValueChange = {dateString->
                selectedDate = LocalDate.parse(dateString, formatter)
                viewModel.onEvent(MoreInfoEvent.PreviousCycleDate(LocalDate.parse(dateString, formatter)))
                checkSelectedDateState(selectedDate)
            },
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
            shape = RoundedCornerShape(10.dp),
            readOnly = true
        )
        MaterialDialog(
            dialogState = datePickerDialogState,
            buttons = {
                positiveButton(text = stringResource(R.string.ok))
                negativeButton(text = stringResource(R.string.cancel))
            },
            backgroundColor = MaterialTheme.colorScheme.surfaceVariant,
//            shape = RoundedCornerShape(20.dp),

        ) {
            datepicker(
                initialDate = selectedDate ?: LocalDate.now(),
                title = "select Date",
                allowedDateValidator = {
                    it.isBefore(LocalDate.now()) && it.isAfter(
                        LocalDate.now().minusDays(30)
                    ) || it == LocalDate.now()
                },
                colors = DatePickerDefaults.colors(
                    dateActiveBackgroundColor = MaterialTheme.colorScheme.primary,
                    calendarHeaderTextColor = MaterialTheme.colorScheme.onSurfaceVariant,
                    headerBackgroundColor = MaterialTheme.colorScheme.secondary,
                    headerTextColor = MaterialTheme.colorScheme.onSecondary,

                    )
            ) {
                selectedDate = it
                checkSelectedDateState(selectedDate)

            }
        }
    }
}

