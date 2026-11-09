// 3 ClockUiEvent.kt

package com.example.clockV2.ui.main.state

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.TextUnit

// События UI для управления состоянием часов
sealed interface ClockUiEvent {
    data object ToggleSecond : ClockUiEvent
    data object ToggleData : ClockUiEvent
    data object ToggleEcoMode : ClockUiEvent
    data class EcoBrightnessChange(val brightness: Int) : ClockUiEvent
    data object ToggleACF : ClockUiEvent
    data object ToggleMSF : ClockUiEvent
    data class FontSizeChange(val newTextSize: TextUnit) : ClockUiEvent
    data class FontColorChange(val newTextColor: Color) : ClockUiEvent
    data class FontFamilyChange(val newFontFamily: FontFamily) : ClockUiEvent
    data class SetIntervalChangeFont(val newIntervalChangeFont: Pair<Int,Int>) : ClockUiEvent
}

// 3 ClockUiEvent.kt
