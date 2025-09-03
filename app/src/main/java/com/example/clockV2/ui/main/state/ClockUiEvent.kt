// 1 ClockUiEvent.kt

package com.example.clockV2.ui.main.state

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.TextUnit

// События UI для управления состоянием часов
sealed interface ClockUiEvent {
    data object ToggleSecond : ClockUiEvent // Переключение отображения секунд
    data object  ToggleData: ClockUiEvent // Переключение отображения даты
    data class FontSizeChange (val newTextSize: TextUnit) : ClockUiEvent // Изменение размера шрифта
    data class FontColorChange (val newTextColor: Color) : ClockUiEvent //// Изменение цвета текста
    data class FontFamilyChange(val newFontFamily: FontFamily): ClockUiEvent  // Изменение шрифта

}

// 1 ClockUiEvent.kt