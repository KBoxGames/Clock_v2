package com.example.clockV2.ui.main.state

import androidx.compose.ui.graphics.Color

sealed interface ClockUiEvent {
    data object TogleSecond : ClockUiEvent
    data object  TogleData: ClockUiEvent
    data class FontSizeChange (val newTextSize: Float) : ClockUiEvent
    data class FontColorChange (val newTextColor: Color) : ClockUiEvent
}