package com.example.clockV2.ui.main.textUtilits

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.example.clockV2.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DynamicText(
    textTime: String,
    maxTextSize: TextUnit = 200.sp,
    colorText: Color = Color.Black,
    maxLine: Int = 1,
    style: TextStyle = TextStyle.Default,
    fontFamily: FontFamily = FontFamily.Default,
    textDate: String? = null,
    isShowDate: Boolean
) {
    var currentSize by remember { mutableStateOf(maxTextSize) }
    var timeOverflow by remember { mutableStateOf(false) }
    var dateOverflow by remember { mutableStateOf(false) }

    // Сбрасываем переполнение даты при скрытии
    LaunchedEffect(isShowDate) {
        if (!isShowDate) dateOverflow = false
    }

    // Обрабатываем переполнение
    LaunchedEffect(timeOverflow, dateOverflow) {
        if (timeOverflow || dateOverflow) {
            val newSize = currentSize * 0.9f
            if (newSize > 1.sp) {
                currentSize = newSize
                // Сбрасываем флаги после изменения размера
                timeOverflow = false
                dateOverflow = false
            }
        }
    }

    val formattedText = remember(textTime, currentSize) {
        formatText(textTime, currentSize, 0.3f)
    }

    Box(modifier = Modifier.fillMaxWidth(0.95f), ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                    text = formattedText,
                    color = colorText,
                    maxLines = maxLine,
                    style = style.copy(fontFamily = fontFamily),
                    textAlign = TextAlign.Center,
                    softWrap = false,
                    onTextLayout = { textLayoutResult ->
                        timeOverflow = textLayoutResult.didOverflowWidth
                    }
                )


            if (isShowDate && textDate != null) {
                Text(
                    text = textDate,
                    color = colorText,
                    style = style.copy(fontFamily = fontFamily),
                    textAlign = TextAlign.Center,
                    softWrap = false,
                    fontSize = currentSize * 0.15f,
                    onTextLayout = { textLayoutResult ->
                        dateOverflow = textLayoutResult.didOverflowWidth
                    }
                )
            }
        }
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