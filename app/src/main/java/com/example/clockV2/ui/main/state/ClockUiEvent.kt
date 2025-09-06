// 3 ClockUiEvent.kt

package com.example.clockV2.ui.main.state

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.TextUnit

// События UI для управления состоянием часов
sealed interface ClockUiEvent {
    data object ToggleSecond : ClockUiEvent // Переключение отображения секунд
    data object  ToggleData: ClockUiEvent // Переключение отображения даты
    data object  ToggleACF: ClockUiEvent // Переключение включения автосмены шрифта
    data object  ToggleMSF: ClockUiEvent // Переключение макимального размера шрифта
    data class FontSizeChange (val newTextSize: TextUnit) : ClockUiEvent // Изменение размера шрифта
    data class FontColorChange (val newTextColor: Color) : ClockUiEvent //// Изменение цвета текста
    data class FontFamilyChange(val newFontFamily: FontFamily): ClockUiEvent  // Изменение шрифта
    data class SetIntervalChangeFont (val newIntervalChangeFont: Pair<Int,Int>): ClockUiEvent  // Изменение интервала смены шрифта

}

// 3 ClockUiEvent.kt