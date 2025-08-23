package com.example.clockV2.ui.main.textUtilits

import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

fun calculateDinamicSizeFont(
    maxW : Dp,
    maxH: Dp,
    text: String,
    fontFamity: FontFamily
): Float {

  return  if (maxW > maxH)
    {
        maxW.value * 0.15f
    }
    else maxH.value * 0.15f

}

/*
fun Float.toTextUnitWithLimit(maxSize: Float): TextUnit {
    return this.coerceAtMost(maxSize).sp
}

 */