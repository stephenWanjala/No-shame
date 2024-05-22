package com.wantech.noshame.featureMenstrualTrack.domain.model


import com.wantech.noshame.featureMenstrualTrack.presentation.util.FertilityStatus
import java.time.LocalDate
import java.time.format.DateTimeFormatter

data class CycleDetails(
    val ovulationDay: Int,
    val fertileDays: List<Int>,
    val safeDays: List<Int>,
    val flowDays: List<LocalDate>,
    val daysRemainingToNextFlow: Int,
    val currentDayInCycle: Int,
    val fertilityStatus: Map<Int, FertilityStatus>,
    val fertileDates: List<LocalDate>,
    val safeDates: List<LocalDate>,
    val cycleLength: Int,
    val lastMensesDate: LocalDate,
    val currentDateInCycle: LocalDate,
    val periodLength: Int,
    val fertilityStatusToday: FertilityStatus
)

private val formatter: DateTimeFormatter = DateTimeFormatter.ISO_LOCAL_DATE


fun CycleDetails.toSerializable(): SerializableCycleDetails {
    return SerializableCycleDetails(
        ovulationDay = ovulationDay,
        fertileDays = fertileDays,
        safeDays = safeDays,
        flowDays = flowDays.map { it.format(formatter) },
        daysRemainingToNextFlow = daysRemainingToNextFlow,
        currentDayInCycle = currentDayInCycle,
        fertilityStatus = fertilityStatus,
        fertileDates = fertileDates.map { it.format(formatter) },
        safeDates = safeDates.map { it.format(formatter) },
        currentDateInCycle = currentDateInCycle.format(formatter),
        cycleLength = cycleLength,
        lastMensesDate = lastMensesDate.format(formatter),
        periodLength = periodLength,
        fertilityStatusToday = fertilityStatusToday
    )
}

fun SerializableCycleDetails.toDomain(): CycleDetails {
    return CycleDetails(
        ovulationDay = ovulationDay,
        fertileDays = fertileDays,
        safeDays = safeDays,
        flowDays = flowDays.map { LocalDate.parse(it, formatter) },
        daysRemainingToNextFlow = daysRemainingToNextFlow,
        currentDayInCycle = currentDayInCycle,
        fertilityStatus = fertilityStatus,
        fertileDates = fertileDates.map { LocalDate.parse(it, formatter) },
        safeDates = safeDates.map { LocalDate.parse(it, formatter) },
        cycleLength = cycleLength,
        lastMensesDate = LocalDate.parse(lastMensesDate, formatter),
        currentDateInCycle = LocalDate.parse(currentDateInCycle, formatter),
        periodLength = periodLength,
        fertilityStatusToday = fertilityStatusToday
    )
}


data class SerializableCycleDetails(
    val ovulationDay: Int,
    val fertileDays: List<Int>,
    val safeDays: List<Int>,
    val flowDays: List<String>,
    val daysRemainingToNextFlow: Int,
    val currentDayInCycle: Int,
    val fertilityStatus: Map<Int, FertilityStatus>,
    val fertileDates: List<String>,
    val safeDates: List<String>,
    val lastMensesDate: String,
    val currentDateInCycle: String,
    val periodLength: Int,
    val cycleLength: Int,
    val fertilityStatusToday: FertilityStatus
)
