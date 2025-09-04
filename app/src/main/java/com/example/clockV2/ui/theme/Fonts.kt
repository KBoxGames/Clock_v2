package com.example.clockV2.ui.theme

import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import com.example.clockV2.R

class Fonts {

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

    val avilableFonts = listOf(
        Pair (art1, "Художественный 1"),
        Pair (art2, "Художественный 2"),
        Pair (art3, "Художественный 3"),
        Pair (art4, "Художественный 4"),
        Pair (led1, "Цифровой 1"),
        Pair (led2, "Цифровой 2"),
        Pair (led3, "Цифровой 3"),
        Pair (led4, "Цифровой 4")

    )
}