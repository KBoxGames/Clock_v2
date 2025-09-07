package com.example.clockV2.ui.main.textUtilits

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.snapping.SnapPosition
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListItemInfo
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.abs

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CircularNumberPicker(
    pickerSize: Dp = 100.dp,
    textSize: TextUnit = 60.sp,
    range: IntRange = (0..24),
    isDivider: Boolean = true,
    isBorder: Boolean = true,
    colorBorder: Color = Color.White,
    onValueSelected: (Int) -> Unit,
    initialValue: Int = 0
) {
    val numbers = range.toList()
    val repeatedNumbers = remember { List(5) { numbers }.flatten() }

    // Вычисляем начальный индекс на основе initialValue
    val startIndex = remember(initialValue) {
        val indexInNumbers = numbers.indexOf(initialValue)
        if (indexInNumbers != -1) {
            numbers.size * 2 + indexInNumbers
        } else {
            numbers.size * 2
        }
    }

    val listState = rememberLazyListState(initialFirstVisibleItemIndex = startIndex)

    // Прокручиваем к нужному элементу при изменении initialValue
    LaunchedEffect(initialValue) {
        val targetIndex = numbers.indexOf(initialValue).let { index ->
            if (index != -1) numbers.size * 2 + index else numbers.size * 2
        }
        listState.scrollToItem(targetIndex)
    }

    val snapFlingBehavior = rememberSnapFlingBehavior(
        lazyListState = listState,
        snapPosition = SnapPosition.Center
    )

    // Вычисляем текущее выбранное значение
    val currentValue by remember {
        derivedStateOf {
            val layoutInfo = listState.layoutInfo
            val center = layoutInfo.viewportStartOffset + layoutInfo.viewportSize.height / 2

            layoutInfo.visibleItemsInfo
                .minByOrNull { item: LazyListItemInfo ->
                    abs((item.offset + item.size / 2) - center)
                }
                ?.let { repeatedNumbers[it.index % numbers.size] } ?: initialValue
        }
    }

    // Уведомляем о изменении значения
    LaunchedEffect(currentValue) {
        onValueSelected(currentValue)
    }

    val color = if (isBorder) colorBorder else Color.Transparent

    Box(
        modifier = Modifier
            .size(pickerSize)
            .border(0.dp, color)
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            if (isDivider) {
                HorizontalDivider(
                    modifier = Modifier.fillMaxWidth(),
                    thickness = DividerDefaults.Thickness,
                    color = Color.Gray
                )
            }

            LazyColumn(
                state = listState,
                horizontalAlignment = Alignment.CenterHorizontally,
                flingBehavior = snapFlingBehavior,
                modifier = Modifier.fillMaxSize()
            ) {
                itemsIndexed(repeatedNumbers) { index, number ->
                    val isCenter by remember {
                        derivedStateOf {
                            val layoutInfo = listState.layoutInfo
                            val center = layoutInfo.viewportStartOffset + layoutInfo.viewportSize.height / 2
                            val itemInfo = layoutInfo.visibleItemsInfo.firstOrNull { it.index == index }

                            itemInfo?.let {
                                abs(it.offset + it.size / 2 - center) < it.size / 4
                            } ?: false
                        }
                    }

                    Text(
                        text = if (number < 10) "0${number}" else "$number",
                        fontSize = if (isCenter) textSize else textSize * 0.8f,
                        color = if (isCenter) MaterialTheme.colorScheme.primary else Color.Gray,
                        fontWeight = if (isCenter) FontWeight.Bold else FontWeight.Normal,
                        modifier = Modifier
                            .padding(8.dp)
                            .alpha(if (isCenter) 1f else 0.6f)
                            .scale(if (isCenter) 1.2f else 1f)
                    )
                }
            }
        }
    }
}