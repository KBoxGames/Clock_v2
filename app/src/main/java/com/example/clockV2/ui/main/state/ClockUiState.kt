// 2 ClockUiState.kt
package com.example.clockV2.ui.main.state

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

data class ClockUiState(
    val textScreen: String = "00:00:00",
    val dataScreen: String? = null,
    val isSecond: Boolean = true,
    val isDate: Boolean = false,
    val textSize: TextUnit = 150.sp,
    val textColor: Color = Color.Green,
    val fontFamily: FontFamily = FontFamily.SansSerif,
    val availableFont: List<Pair<FontFamily, String>> = emptyList(),
    val isAutoChangeFont: Boolean = false,
    val isMaxSizeText: Boolean = false,
    val intervalChangeFont: Pair<Int,Int>

)

// 2 ClockUiState.kt