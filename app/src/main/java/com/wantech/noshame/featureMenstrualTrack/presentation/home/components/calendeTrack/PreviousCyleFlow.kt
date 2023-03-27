package com.wantech.noshame.featureMenstrualTrack.presentation.home.components.calendeTrack

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.kizitonwose.calendar.compose.CalendarState
import com.kizitonwose.calendar.compose.HorizontalCalendar
import com.kizitonwose.calendar.compose.WeekCalendar
import com.kizitonwose.calendar.compose.rememberCalendarState
import com.kizitonwose.calendar.compose.weekcalendar.WeekCalendarState
import com.kizitonwose.calendar.compose.weekcalendar.rememberWeekCalendarState
import com.kizitonwose.calendar.core.*
import com.wantech.noshame.R
import com.wantech.noshame.featureMenstrualTrack.presentation.home.components.calendeTrack.PageSharedComponents.CalendarHeader
import com.wantech.noshame.featureMenstrualTrack.presentation.home.components.calendeTrack.PageSharedComponents.Day
import com.wantech.noshame.featureMenstrualTrack.presentation.home.components.calendeTrack.PageSharedComponents.MonthAndWeekCalendarTitle
import com.wantech.noshame.featureMenstrualTrack.presentation.util.displayText
import com.wantech.noshame.featureMenstrualTrack.presentation.util.rememberFirstVisibleMonthAfterScroll
import com.wantech.noshame.featureMenstrualTrack.presentation.util.rememberFirstVisibleWeekAfterScroll
import kotlinx.coroutines.launch
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.YearMonth

@Composable
fun PreviousCycleFlow(
    modifier: Modifier = Modifier,
    adjacentMonths: Long = 500,
    selections: SnapshotStateList<LocalDate>,
) {
    val currentDate = remember { selections.first()}
//    val currentMonth = remember(currentDate) { currentDate.yearMonth }
    val currentMonth =
        remember(selections.first()) { selections.first().yearMonth }
    val startMonth = remember(selections.first()) { currentMonth.minusMonths(adjacentMonths) }
    val endMonth = remember(currentDate) { currentMonth.plusMonths(adjacentMonths) }
    val daysOfWeek = remember { daysOfWeek() }

    var isWeekMode by remember { mutableStateOf(true) }


    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .background(MaterialTheme.colorScheme.background),
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
            onTittleClick = {isWeekMode=!isWeekMode}
        )
        CalendarHeader(daysOfWeek = daysOfWeek)
        val monthCalendarAlpha by animateFloatAsState(if (isWeekMode) 0f else 1f)
        val weekCalendarAlpha by animateFloatAsState(if (isWeekMode) 1f else 0f)
        var weekCalendarSize by remember { mutableStateOf(DpSize.Zero) }
        val visibleMonth = rememberFirstVisibleMonthAfterScroll(monthState)
        val weeksInVisibleMonth = visibleMonth.weekDays.count()
        val monthCalendarHeight by animateDpAsState(
            if (isWeekMode) {
                weekCalendarSize.height
            } else {
                weekCalendarSize.height * weeksInVisibleMonth
            },
            tween(durationMillis = 250),
        )
        val density = LocalDensity.current
        Box {
            HorizontalCalendar(
                modifier = Modifier
                    .height(monthCalendarHeight)
                    .alpha(monthCalendarAlpha)
                    .zIndex(if (isWeekMode) 0f else 1f),
                state = monthState,
                dayContent = { day ->
                    val isSelectable = day.position == DayPosition.MonthDate
                    Day(
                        day.date,
                        isSelected = isSelectable && selections.contains(day.date),
                        isSelectable = isSelectable,
                    ) {
                        isWeekMode = !isWeekMode
                    }
                },
            )
            WeekCalendar(
                modifier = Modifier
                    .wrapContentHeight()
                    .onSizeChanged {
                        val size = density.run { DpSize(it.width.toDp(), it.height.toDp()) }
                        if (weekCalendarSize != size) {
                            weekCalendarSize = size
                        }
                    }
                    .alpha(weekCalendarAlpha)
                    .zIndex(if (isWeekMode) 1f else 0f),
                state = weekState,
                dayContent = { day ->
                    val isSelectable = day.position == WeekDayPosition.RangeDate
                    Day(
                        day.date,
                        isSelected = isSelectable && selections.contains(day.date),
                        isSelectable = isSelectable,
                    ) {
                        isWeekMode = !isWeekMode
                    }

                },

                )

            LaunchedEffect(key1 = isWeekMode) {
                if (isWeekMode) {
                    val targetDate = monthState.firstVisibleMonth.weekDays.last().last().date
                    weekState.scrollToWeek(targetDate)
                    weekState.animateScrollToWeek(targetDate) // Trigger a layout pass for title update
                } else {
                    val targetMonth = weekState.firstVisibleWeek.days.first().date.yearMonth
                    monthState.scrollToMonth(targetMonth)
                    monthState.animateScrollToMonth(targetMonth) // Trigger a layout pass for title update
                }
            }
        }
        Spacer(modifier = Modifier.weight(1f))

    }
}

@Composable
private fun CalendarTitle(
    isWeekMode: Boolean,
    monthState: CalendarState,
    weekState: WeekCalendarState,
    onTittleClick: () -> Unit,
) {
    val visibleMonth = rememberFirstVisibleMonthAfterScroll(monthState)
    val visibleWeek = rememberFirstVisibleWeekAfterScroll(weekState)
    MonthAndWeekCalendarTitle(
        isWeekMode = isWeekMode,
        currentMonth = if (isWeekMode) visibleWeek.days.first().date.yearMonth else visibleMonth.yearMonth,
        monthState = monthState,
        weekState = weekState,
        tittleClick = onTittleClick
    )
}

object PageSharedComponents {
    @Composable
    fun MonthAndWeekCalendarTitle(
        isWeekMode: Boolean,
        currentMonth: YearMonth,
        monthState: CalendarState,
        weekState: WeekCalendarState,
        tittleClick: (() -> Unit)? =null
    ) {
        val coroutineScope = rememberCoroutineScope()
        SimpleCalendarTitle(
            modifier = Modifier.padding(vertical = 10.dp, horizontal = 8.dp),
            currentMonth = currentMonth,
            goToPrevious = {
                coroutineScope.launch {
                    if (isWeekMode) {
                        val targetDate = weekState.firstVisibleWeek.days.first().date.minusDays(1)
                        weekState.animateScrollToWeek(targetDate)
                    } else {
                        val targetMonth = monthState.firstVisibleMonth.yearMonth.previousMonth
                        monthState.animateScrollToMonth(targetMonth)
                    }
                }
            },
            goToNext = {
                coroutineScope.launch {
                    if (isWeekMode) {
                        val targetDate = weekState.firstVisibleWeek.days.last().date.plusDays(1)
                        weekState.animateScrollToWeek(targetDate)
                    } else {
                        val targetMonth = monthState.firstVisibleMonth.yearMonth.nextMonth
                        monthState.animateScrollToMonth(targetMonth)
                    }
                }
            },
            onClick = { tittleClick?.invoke() }
        )
    }

    @Composable
    fun CalendarHeader(daysOfWeek: List<DayOfWeek>) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
        ) {
            for (dayOfWeek in daysOfWeek) {
                Text(
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Center,
                    fontSize = 15.sp,
                    text = dayOfWeek.displayText(),
                    fontWeight = FontWeight.Medium,
                )
            }
        }
    }

    @Composable
    fun Day(
        day: LocalDate,
        isSelected: Boolean,
        isSelectable: Boolean,
        onClick: (LocalDate) -> Unit,
    ) {
        Box(
            modifier = Modifier
                .aspectRatio(1f) // This is important for square-sizing!
                .padding(6.dp)
                .clip(CircleShape)
                .background(color = if (isSelected) colorResource(R.color.example_1_selection_color) else Color.Transparent)
                .clickable(
                    enabled = isSelectable,
//                    showRipple = !isSelected,
                    onClick = { onClick(day) },
                ),
            contentAlignment = Alignment.Center,
        ) {
            val textColor = when {
                isSelected -> Color.White
                isSelectable -> Color.Unspecified
                else -> colorResource(R.color.inactive_text_color)
            }
            Text(
                text = day.dayOfMonth.toString(),
                color = textColor,
                fontSize = 14.sp,
            )
        }
    }
}



