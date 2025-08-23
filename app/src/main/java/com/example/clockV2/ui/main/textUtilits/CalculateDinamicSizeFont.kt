package com.example.clockV2.ui.main.textUtilits


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

@Composable
fun calculateDynamicSizeFont(
    text: String,
    maxTextSize: TextUnit = 200.sp,
    colorText: Color = Color.Black,
    maxLine: Int = 1,
    style: TextStyle = TextStyle.Default
): TextUnit {
    var currentSize by remember { mutableStateOf(maxTextSize) }
    var readyForDraw by remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = text,
            fontSize = currentSize,
            color = colorText,
            maxLines = maxLine,
            style = style,
            textAlign = TextAlign.Center,
            softWrap = false,
            onTextLayout = { textLayoutResult ->
                if (textLayoutResult.didOverflowWidth) {
                    // Уменьшаем размер шрифта если текст не помещается
                    val newSize = currentSize * 0.9
                    if (newSize > 1.sp) {
                        currentSize = newSize
                    }
                } else {
                    readyForDraw = true
                }
            }
        )
    }
    return currentSize
}





