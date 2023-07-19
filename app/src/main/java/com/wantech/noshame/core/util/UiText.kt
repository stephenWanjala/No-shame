package com.wantech.noshame.core.util

import android.content.Context
import androidx.annotation.StringRes
import com.wantech.noshame.R

sealed class UiText {
    data class DynamicString(val value: String): UiText()
    data class StringResource(@StringRes val id: Int): UiText()

    companion object {
        fun unknownError(): UiText {
            return UiText.StringResource(R.string.error_unknown)
        }
    }

    fun asString(context:Context): String {
        return when(this){
            is DynamicString -> this.value
            is StringResource -> context.getString(this.id)
        }
    }
}
