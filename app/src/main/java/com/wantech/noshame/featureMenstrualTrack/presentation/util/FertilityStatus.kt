package com.wantech.noshame.featureMenstrualTrack.presentation.util

sealed class FertilityStatus(val status: String){
    data object High: FertilityStatus(status = "Peak")
    data object Medium: FertilityStatus(status = "Medium")
    data object Low: FertilityStatus(status = "Low")
}
