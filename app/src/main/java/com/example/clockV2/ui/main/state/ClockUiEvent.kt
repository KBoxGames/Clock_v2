// 1 ClockUiEvent.kt

package com.example.clockV2.ui.main.state

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.TextUnit

sealed interface ClockUiEvent {
    data object ToggleSecond : ClockUiEvent
    data object  ToggleData: ClockUiEvent
    data class FontSizeChange (val newTextSize: TextUnit) : ClockUiEvent
    data class FontColorChange (val newTextColor: Color) : ClockUiEvent
    data class FontFamilyChange (val newFontFamily: FontFamily): ClockUiEvent
}

// 1 ClockUiEvent.kt
