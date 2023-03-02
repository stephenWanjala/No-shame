package com.wantech.noshame.featureMenstrualTrack.presentation.home.components.calendeTrack

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.kizitonwose.calendar.compose.CalendarState
import com.kizitonwose.calendar.compose.HorizontalCalendar
import com.kizitonwose.calendar.compose.WeekCalendar
import com.kizitonwose.calendar.compose.rememberCalendarState
import com.kizitonwose.calendar.compose.weekcalendar.WeekCalendarState
import com.kizitonwose.calendar.compose.weekcalendar.rememberWeekCalendarState
import com.kizitonwose.calendar.core.*
import com.wantech.noshame.featureMenstrualTrack.presentation.home.components.calendeTrack.PageSharedComponents.CalendarHeader
import com.wantech.noshame.featureMenstrualTrack.presentation.home.components.calendeTrack.PageSharedComponents.Day
import com.wantech.noshame.featureMenstrualTrack.presentation.home.components.calendeTrack.PageSharedComponents.MonthAndWeekCalendarTitle
import com.wantech.noshame.featureMenstrualTrack.presentation.util.rememberFirstVisibleMonthAfterScroll
import com.wantech.noshame.featureMenstrualTrack.presentation.util.rememberFirstVisibleWeekAfterScroll
import kotlinx.coroutines.launch
import java.time.LocalDate

@Composable
fun CalenderAnimateVisibility(adjacentMonths: Long = 500) {
    val currentDate = remember { LocalDate.now() }
    val currentMonth = remember(currentDate) { currentDate.yearMonth }
    val startMonth = remember(currentDate) { currentMonth.minusMonths(adjacentMonths) }
    val endMonth = remember(currentDate) { currentMonth.plusMonths(adjacentMonths) }
    val selections = remember { mutableStateListOf<LocalDate>() }
    val daysOfWeek = remember { daysOfWeek() }

    var isWeekMode by remember { mutableStateOf(false) }
    var isAnimating by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
    ) {
        val monthState = rememberCalendarState(
            startMonth = startMonth,
            endMonth = endMonth,
            firstVisibleMonth = currentMonth,
            firstDayOfWeek = daysOfWeek.first(),
        )
        val weekState = rememberWeekCalendarState(
            startDate = startMonth.atStartOfMonth(),
            endDate = endMonth.atEndOfMonth(),
            firstVisibleWeekDate = currentDate,
            firstDayOfWeek = daysOfWeek.first(),
        )
        CalendarTitle(
            isWeekMode = isWeekMode,
            monthState = monthState,
            weekState = weekState,
            isAnimating = isAnimating,
        )
        CalendarHeader(daysOfWeek = daysOfWeek)
        AnimatedVisibility(visible = !isWeekMode) {
            HorizontalCalendar(
                state = monthState,
                dayContent = { day ->
                    val isSelectable = day.position == DayPosition.MonthDate
                    Day(
                        day.date,
                        isSelected = isSelectable && selections.contains(day.date),
                        isSelectable = isSelectable,
                    ) {
//                        if (selections.contains(clicked)) {
//                            selections.remove(clicked)
//                        } else {
//                            selections.add(clicked)
//                        }
                        isWeekMode = !isWeekMode
                    }
                },
            )
        }
        AnimatedVisibility(visible = isWeekMode) {
            WeekCalendar(
                state = weekState,
                dayContent = { day ->
                    val isSelectable = day.position == WeekDayPosition.RangeDate
                    Day(
                        day.date,
                        isSelected = isSelectable && selections.contains(day.date),
                        isSelectable = isSelectable,
                    ) {
//                        if (selections.contains(clicked)) {
//                            selections.remove(clicked)
//                        } else {
//                            selections.add(clicked)
//                        }
                        isWeekMode = !isWeekMode
                    }
                },
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        /* WeekModeToggle(
             modifier = Modifier.align(Alignment.CenterHorizontally),
             isWeekMode = isWeekMode,
         ) { weekMode ->
             isAnimating = true
             isWeekMode = weekMode
             coroutineScope.launch {
                 if (weekMode) {
                     val targetDate = monthState.firstVisibleMonth.weekDays.last().last().date
                     weekState.scrollToWeek(targetDate)
                     weekState.animateScrollToWeek(targetDate) // Trigger a layout pass for title update
                 } else {
                     val targetMonth = weekState.firstVisibleWeek.days.first().date.yearMonth
                     monthState.scrollToMonth(targetMonth)
                     monthState.animateScrollToMonth(targetMonth) // Trigger a layout pass for title update
                 }
                 isAnimating = false
             }
         }*/
        LaunchedEffect(key1 = isWeekMode) {
            isAnimating = true
//            isWeekMode = !isWeekMode
            coroutineScope.launch {
                if (isWeekMode) {
                    val targetDate = monthState.firstVisibleMonth.weekDays.last().last().date
                    weekState.scrollToWeek(targetDate)
                    weekState.animateScrollToWeek(targetDate) // Trigger a layout pass for title update
                } else {
                    val targetMonth = weekState.firstVisibleWeek.days.first().date.yearMonth
                    monthState.scrollToMonth(targetMonth)
                    monthState.animateScrollToMonth(targetMonth) // Trigger a layout pass for title update
                }
                isAnimating = false
            }
        }
    }
}

@Composable
private fun CalendarTitle(
    isWeekMode: Boolean,
    monthState: CalendarState,
    weekState: WeekCalendarState,
    isAnimating: Boolean,
) {
    val visibleMonth = rememberFirstVisibleMonthAfterScroll(monthState)
    val visibleWeek = rememberFirstVisibleWeekAfterScroll(weekState)
    val visibleWeekMonth = visibleWeek.days.first().date.yearMonth
    // Track animation state to prevent updating the title too early before
    // the correct value is available (after the animation).
    val currentMonth = if (isWeekMode) {
        if (isAnimating) visibleMonth.yearMonth else visibleWeekMonth
    } else {
        if (isAnimating) visibleWeekMonth else visibleMonth.yearMonth
    }
    MonthAndWeekCalendarTitle(
        isWeekMode = isWeekMode,
        currentMonth = currentMonth,
        monthState = monthState,
        weekState = weekState,
    )
}

@Preview
@Composable
private fun PageAnimatedVisibilityPreview() {
    CalenderAnimateVisibility()
}
