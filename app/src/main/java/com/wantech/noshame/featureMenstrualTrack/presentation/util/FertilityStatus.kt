package com.wantech.noshame.featureMenstrualTrack.presentation.util

sealed class FertilityStatus(val status:String){
    object High: FertilityStatus(status = "Peak")
    object Medium: FertilityStatus(status = "Medium")
    object Low: FertilityStatus(status = "Low")
}
