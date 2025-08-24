
// 2 ClockUiState.kt
package com.example.clockV2.ui.main.state

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily

data class ClockUiState(
    val textScreen: String = "00:00:00",
    val dataScreen: String? = null,
    val isSecond: Boolean = true,
    val isDate: Boolean = false,
    val textSize: Float = 150f,
    val textColor: Color = Color.Green,
    val fontFamily: FontFamily = FontFamily.SansSerif,
    val availableFont: List<Pair<FontFamily, String>> = emptyList()
    )

// 2 ClockUiState.kt
