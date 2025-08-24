// 1 ClockUiEvent.kt

package com.example.clockV2.ui.main.state

import androidx.compose.ui.graphics.Color

sealed interface ClockUiEvent {
    data object ToggleSecond : ClockUiEvent
    data object  ToggleData: ClockUiEvent
    data class FontSizeChange (val newTextSize: Float) : ClockUiEvent
    data class FontColorChange (val newTextColor: Color) : ClockUiEvent
}

// 1 ClockUiEvent.kt
