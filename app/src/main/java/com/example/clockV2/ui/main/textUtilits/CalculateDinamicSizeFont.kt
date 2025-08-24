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
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp


@Composable
fun DynamicText(
    text: String,
    maxTextSize: TextUnit = 200.sp,
    colorText: Color = Color.Black,
    maxLine: Int = 1,
    style: TextStyle = TextStyle.Default
) {
    var currentSize by remember { mutableStateOf(maxTextSize) }
    val formattedText = remember(text, currentSize) {
        formatText(text, currentSize, 0.3f)
    }

    Box(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = formattedText,
            color = colorText,
            maxLines = maxLine,
            style = style,
            textAlign = TextAlign.Center,
            softWrap = false,
            onTextLayout = { textLayoutResult ->
                if (textLayoutResult.didOverflowWidth) {
                    val newSize = currentSize * 0.9f
                    if (newSize > 1.sp) {
                        currentSize = newSize
                    }
                }
            }
        )
    }
}

private fun formatText(
    text: String,
    textSize: TextUnit,
    percent: Float = 0.4f
): AnnotatedString {
    val smallSize = textSize * percent
    return buildAnnotatedString {
        if (text.length >= 6) {
            withStyle(SpanStyle(fontSize = textSize)) {
                append(text.substring(0, 5))
            }
            withStyle(SpanStyle(fontSize = smallSize)) {
                append(text.substring(5))
            }
        } else {
            withStyle(SpanStyle(fontSize = textSize)) {
                append(text)
            }
        }
    }
}




