//13 SettingsTypeFont.kt
package com.example.clockV2.ui.settings


import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.clockV2.ui.main.MainViewModel
import com.example.clockV2.ui.main.state.ClockUiEvent
import com.example.clockV2.ui.theme.availableFonts
import kotlinx.coroutines.launch
import androidx.compose.ui.Alignment
import com.example.clockV2.ui.main.textUtilits.CircularNumberPicker


@Composable
fun SettingsTypeFon(viewModel: MainViewModel,
                    onClose:()->Unit,
                    onSelectFontType: (FontFamily) -> Unit,
                    currentFont: FontFamily
                    ) {

    BoxWithConstraints(modifier = Modifier.fillMaxSize().clickable(onClick = onClose),
        contentAlignment = Alignment.BottomEnd) {

        var isPortrait by remember { mutableStateOf(false) }
        if (maxHeight < maxWidth) isPortrait = true

        if (isPortrait) {
            Card(modifier = Modifier.fillMaxWidth(0.85f))
            {
                Row(horizontalArrangement = Arrangement.Center)
                {
                    Spacer(Modifier.width(20.dp))
                    BlockFont(
                        onClose = onClose,
                        onSelectFontType = onSelectFontType,
                        currentFont = currentFont
                    )
                    Spacer(Modifier.width(8.dp))
                    BlockFontSettings(viewModel)
                }
            }
        } else
        {
            Card (modifier = Modifier
                .fillMaxHeight(0.65f))
            {
                Box(modifier = Modifier.padding(8.dp)) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        BlockFont(
                            0.8f, 0.3f,
                            onClose =onClose,
                            onSelectFontType = onSelectFontType,
                            currentFont = currentFont
                        )
                        Spacer(Modifier.height(8.dp))
                        BlockFontSettings(viewModel ,0.8f,0.9f)
                    }

                }

            }
        }
    }
}


@Composable
fun BlockFont(fractW: Float = 0.4f, fractH: Float = 0.8f,
              onClose: () -> Unit,
              onSelectFontType: (FontFamily) -> Unit,
              currentFont: FontFamily)
{
    val currentIndex = availableFonts.indexOfFirst{it.first == currentFont}
        .takeIf { it != -1 } ?: 0
    val lazzyState = rememberLazyListState()


    LaunchedEffect(Unit) {
        if (currentIndex >= 0)
        {
            lazzyState.animateScrollToItem(currentIndex)
        }
    }

    LaunchedEffect(currentFont) {
        val newIndex = availableFonts.indexOfFirst { it.first == currentFont }
            .takeIf { it != -1 } ?: 0
        if (newIndex >= 0) {
            lazzyState.animateScrollToItem(newIndex)
        }
    }

    Column(horizontalAlignment = Alignment.CenterHorizontally)
    {
        Spacer(Modifier.height(8.dp))
        Text("Шрифт", fontSize = 30.sp)
        Spacer(Modifier.height(16.dp))
        Card(
            modifier = Modifier
                .fillMaxWidth(fractW)
                .fillMaxHeight(fractH),
            colors = CardDefaults.cardColors(Color.Gray)
        ) {
            Box(modifier = Modifier.padding(5.dp))
            {
                Card(modifier = Modifier.fillMaxWidth()) {
                    Box(modifier = Modifier.padding(5.dp)) {
                        Row {
                            LazyColumn(
                                state = lazzyState,
                                modifier = Modifier.weight(1f)
                            ) {
                                itemsIndexed(availableFonts) { index, (fontFamily, fontName) ->
                                    val isSelected = fontFamily == currentFont

                                    // Анимированные свойства
                                    val animatedCardColor by animateColorAsState(
                                        targetValue = if (isSelected) Color.Gray else Color.DarkGray,
                                        animationSpec = tween(durationMillis = 300)
                                    )

                                    val animatedPadding by animateDpAsState(
                                        targetValue = if (isSelected) 20.dp else 16.dp,
                                        animationSpec = tween(durationMillis = 300)
                                    )

                                    Card(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(vertical = 4.dp)
                                            .clickable {
                                                onSelectFontType(fontFamily)
                                                onClose()
                                            },
                                        colors = CardDefaults.cardColors(containerColor = animatedCardColor)
                                    ) {
                                        Row {
                                            Text(
                                                text = "${index + 1}. $fontName",
                                                fontSize = 18.sp,
                                                color = Color.White,
                                                modifier = Modifier.padding(animatedPadding)
                                            )

                                            Spacer(Modifier.weight(1f))

                                            Text(
                                                text = "12:00",
                                                fontSize = 18.sp,
                                                fontFamily = fontFamily,
                                                color = Color.White,
                                                modifier = Modifier.padding(animatedPadding)
                                            )
                                        }
                                    }

                                    if (index < availableFonts.size - 1) {
                                        Spacer(modifier = Modifier.height(4.dp))
                                    }
                                }
                            }
                            val scope = rememberCoroutineScope()
                            Box(

                                contentAlignment = Alignment.TopEnd
                            ) {
                                Column {
                                    Icon(
                                        imageVector = Icons.Default.KeyboardArrowUp,
                                        contentDescription = "Вверх",
                                        modifier = Modifier.size(20.dp).clickable {
                                            // Прокрутка вверх в корутине
                                            scope.launch {
                                                lazzyState.animateScrollToItem(0)
                                            }
                                        }
                                    )
                                    Spacer(Modifier.weight(1f))
                                    Icon(
                                        imageVector = Icons.Default.KeyboardArrowDown,
                                        contentDescription = "Вниз",
                                        modifier = Modifier.size(20.dp).clickable {
                                            // Прокрутка вниз в корутине
                                            scope.launch {
                                                lazzyState.animateScrollToItem(availableFonts.size - 1)
                                            }
                                        }
                                    )
                                }

                            }
                        }

                    }
                }
            }
        }
        //Spacer(Modifier.height(20.dp))
    }
}
@Composable
fun BlockFontSettings(viewModel: MainViewModel,fractW: Float = 0.95f, fractH: Float = 0.8f)
{
    val uiState = viewModel.uiState.collectAsState()
    var selectedHours = uiState.value.intervalChangeFont.first
    var selectedMinute = uiState.value.intervalChangeFont.second





    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Spacer(Modifier.height(8.dp))
        Text("Настройки шрифта", fontSize = 30.sp)
        Spacer(Modifier.height(16.dp))
         Card(
            modifier = Modifier
                .fillMaxWidth(fractW)
                .fillMaxHeight(fractH),
            colors = CardDefaults.cardColors(Color.Gray)
        ) {
            Box(modifier = Modifier.padding(5.dp)) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {

                    Card() {
                        Box(modifier = Modifier.padding(5.dp)) {
                            Column {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text("Автосмена шрифта")
                                    Spacer(Modifier.weight(1f))
                                    Switch(checked = uiState.value.isAutoChangeFont, onCheckedChange = {
                                        viewModel.onEvent(ClockUiEvent.ToggleACF) })
                                }
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text("Максимальный размер")
                                    Spacer(Modifier.weight(1f))
                                    Switch(checked = uiState.value.isMaxSizeText,
                                        onCheckedChange = {viewModel.onEvent(ClockUiEvent.ToggleMSF)})
                                }
                            }
                        }
                    }
                    Spacer(Modifier.height(5.dp))
                    Card() {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Spacer(Modifier.height(50.dp).weight(1f))
                            Text("Интервал смены шрифта")
                            Spacer(Modifier.weight(1f))

                        }
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Spacer(Modifier.weight(1f))
                            CircularNumberPicker(
                                pickerSize = 60.dp,
                                textSize = 35.sp,
                                isBorder = false,
                                isDivider = false,
                                onValueSelected = { value ->
                                    viewModel.onEvent(
                                        ClockUiEvent.SetIntervalChangeFont(
                                            Pair(value,selectedMinute)))
                                },
                                initialValue = selectedHours
                            )
                            Text(":", fontSize = 30.sp)
                            CircularNumberPicker(
                                pickerSize = 60.dp,
                                textSize = 35.sp,
                                range = (0..60),
                                isDivider = false,
                                isBorder = false,
                                onValueSelected = { value ->
                                    viewModel.onEvent(
                                        ClockUiEvent.SetIntervalChangeFont(
                                            Pair(selectedHours,value)))

                                },
                                initialValue = selectedMinute
                            )
                            //Text("Часов: $selectedHours - минут: $selectedMinute - ${viewModel.fontChangeInterval }")

                            Spacer(Modifier.weight(1f))
                        }

                        Spacer(Modifier.weight(1f))

                    }

                }
            }
        }
        Spacer(Modifier.height(20.dp))
    }
}

//13 SettingsTypeFont.kt
