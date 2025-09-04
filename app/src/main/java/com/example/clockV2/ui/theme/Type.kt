package com.example.clockV2.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.clockV2.R

val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
)

val art1 = FontFamily(
    Font(R.font.art1)
)

val art2 = FontFamily(
    Font(R.font.art2)
)
val art3 = FontFamily(
    Font(R.font.art3)
)
val art4 = FontFamily(
    Font(R.font.art4)
)

val led1 = FontFamily(
    Font(R.font.led1)
)
val led2 = FontFamily(
    Font(R.font.led2)
)
val led3 = FontFamily(
    Font(R.font.led3)
)
val led4 = FontFamily(
    Font(R.font.led4)
)

val availableFonts = listOf(
    FontFamily.Default to "Простой",
    FontFamily.Serif to "Шриф",
    FontFamily.Cursive to "Курсив" ,
    FontFamily.Monospace to "Моно",
    FontFamily.SansSerif to "С-Шриф",
    art1 to "АРТ 1",
    art2 to "АРТ 2",
    art3 to "АРТ 3",
    art4 to "АРТ 4",
    led1 to "ЛЭД 1",
    led2 to "ЛЭД 2",
    led3 to "ЛЭД 3",
    led4 to "ЛЭД 4"
)