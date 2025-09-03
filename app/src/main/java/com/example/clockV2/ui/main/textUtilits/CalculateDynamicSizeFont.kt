// 3 CalculateDynamicSizeFont.kt
package com.example.clockV2.ui.main.textUtilits

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


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
    isShowDate: Boolean,
    offset : Float = 0f
) {
    var currentSize by remember(maxTextSize) { mutableStateOf(maxTextSize) }
    var timeOverflow by remember { mutableStateOf(false) }  // Переполнение времени (выходит за рамки экрана)
    var dateOverflow by remember { mutableStateOf(false) }  // Переполнение Даты

    // Сбрасываем currentSize при изменении maxTextSize
    LaunchedEffect(maxTextSize) {
        currentSize = maxTextSize
        timeOverflow = false
        dateOverflow = false
    }

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
    // Форматирование текста с разным размером для часов/минут и секунд
    val formattedText = remember(textTime, currentSize) {
        formatText(textTime, currentSize, 0.3f)
    }

    Box(modifier = Modifier.fillMaxWidth(0.95f), contentAlignment = Alignment.Center) {

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
                        timeOverflow = textLayoutResult.didOverflowWidth || textLayoutResult.didOverflowHeight  // Проверка на переполнение по ширине и высоте
                    }
                )


            if (isShowDate && textDate != null) {
                Text(
                    text = textDate,
                    color = colorText,
                    style = style.copy(fontFamily = FontFamily.SansSerif),
                    textAlign = TextAlign.Center,
                    softWrap = false,
                    fontSize = currentSize * 0.15f, // Размер шрифта даты меньше основного
                    onTextLayout = { textLayoutResult ->
                        dateOverflow = textLayoutResult.didOverflowWidth || textLayoutResult.didOverflowHeight
                    }
                )
            }

            Spacer(Modifier.height(offset.dp)) // Отступ для адаптации под ориентацию
        }
    }
}

// Форматирование текста: часы/минуты - крупно, секунды - мельче
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
// 3 CalculateDynamicSizeFont.kt
