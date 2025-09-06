// 7 MainScreen.kt
package com.example.clockV2.ui.main

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.example.clockV2.ui.main.state.ClockUiEvent
import com.example.clockV2.ui.main.state.ClockUiState
import com.example.clockV2.ui.main.textUtilits.DynamicText
import com.example.clockV2.ui.settings.SettingsColorFont
import com.example.clockV2.ui.settings.SettingsFonts
import com.example.clockV2.ui.settings.SettingsScreen
import com.example.clockV2.ui.settings.SettingsSizeFont
import com.example.clockV2.ui.settings.SettingsTypeFon
import kotlinx.coroutines.delay


// Перечисление для управления состоянием экранов
enum class SettingsScreenState {
    HIDDEN, MAIN, FONTS, FONT_SIZE, FONT_COLOR, FONT_TYPE
}

@SuppressLint("UnusedBoxWithConstraintsScope", "CoroutineCreationDuringComposition")
@Composable
fun MainScreen(viewModel: MainViewModel) {
    val uiState by viewModel.uiState.collectAsState()
    var isShowButton by remember { mutableStateOf(false) } // Показать/скрыть кнопки
    var settingsScreenState by remember { mutableStateOf(SettingsScreenState.HIDDEN) }

    LaunchedEffect(isShowButton) {
        if (isShowButton) {
            // Запускаем таймер только если кнопки видны
            delay(6000)
            isShowButton = false
        }
    }

    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .clickable {
                if (settingsScreenState != SettingsScreenState.HIDDEN) {
                    settingsScreenState = SettingsScreenState.HIDDEN
                } else {
                    isShowButton = true
                }
            },
        contentAlignment = Alignment.Center
    ) {
        val isPortrait = maxHeight > maxWidth
        val offset = if (isPortrait) 300f else 0f

        // Отображение кнопок в зависимости от ориентации
        if (!isPortrait) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                if (isShowButton) {
                    ButtonBlockLandscape {
                        settingsScreenState = SettingsScreenState.MAIN
                    }
                }
            }
        } else {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Bottom
            ) {
                if (isShowButton) {
                    ButtonBlockPortrait {
                        settingsScreenState = SettingsScreenState.MAIN
                    }
                }
            }
        }

        ClockDisplay(uiState = uiState, offset)

        // Анимированное отображение экранов настроек
        AnimatedVisibility(
            visible = settingsScreenState != SettingsScreenState.HIDDEN,
            enter = fadeIn(animationSpec = tween(300)) +
                    slideInVertically(
                        initialOffsetY = { it },
                        animationSpec = tween(durationMillis = 500)
                    ),
            exit = fadeOut(animationSpec = tween(300)) +
                    slideOutVertically(
                        targetOffsetY = { it },
                        animationSpec = tween(durationMillis = 500)
                    ),
            modifier = Modifier.fillMaxSize()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.7f))
            ) {
                when (settingsScreenState) {
                    SettingsScreenState.MAIN -> {
                        SettingsScreen(
                            viewModel,
                            onClose = { settingsScreenState = SettingsScreenState.HIDDEN },
                            onFontsClick = { settingsScreenState = SettingsScreenState.FONTS },
                            onFontSizeClick = { settingsScreenState = SettingsScreenState.FONT_SIZE }
                        )
                    }
                    SettingsScreenState.FONTS -> {
                        SettingsFonts(
                            onClose = { settingsScreenState = SettingsScreenState.MAIN },
                            onFontSizeClick = { settingsScreenState = SettingsScreenState.FONT_SIZE },
                            onFontColorClick =  {settingsScreenState = SettingsScreenState.FONT_COLOR},
                            onFontTypeClick = {settingsScreenState = SettingsScreenState.FONT_TYPE}
                        )
                    }
                    SettingsScreenState.FONT_SIZE -> {
                        SettingsSizeFont(
                            onClose = {  settingsScreenState = SettingsScreenState.MAIN },
                            viewModel = viewModel
                            )
                    }
                    SettingsScreenState.FONT_COLOR -> {
                        SettingsColorFont(
                            onClose = { settingsScreenState = SettingsScreenState.MAIN },
                            onSelectColor ={color ->
                                viewModel.onEvent(ClockUiEvent.FontColorChange(color))
                            }
                        )
                    }

                    SettingsScreenState.FONT_TYPE -> {
                        SettingsTypeFon(
                            onClose = { settingsScreenState = SettingsScreenState.MAIN },
                            onSelectFontType = { font ->
                                viewModel.onEvent(ClockUiEvent.FontFamilyChange(font))
                            },
                            currentFont = uiState.fontFamily,
                            viewModel =viewModel
                        )
                    }

                    SettingsScreenState.HIDDEN -> {
                        // Пустой Box, когда экран скрыт
                        Box(modifier = Modifier.fillMaxSize())
                    }


                }
            }
        }
    }
}

@Composable
fun ClockDisplay(uiState: ClockUiState, offset: Float) {
    DynamicText(

        textTime = uiState.textScreen,
        colorText = uiState.textColor,
        maxTextSize = uiState.textSize,
        maxLine = 1,
        style = TextStyle(fontFamily = uiState.fontFamily),
        textDate = uiState.dataScreen,
        fontFamily = uiState.fontFamily,
        isShowDate = uiState.isDate,
        offset = offset,

    )
}

@Composable
fun ButtonBlockPortrait(showSettings: () -> Unit) {
    Row(modifier = Modifier.fillMaxWidth().padding(30.dp)) {
        IconButton(onClick = {}) {
            Icon(
                imageVector = Icons.Default.Info,
                contentDescription = "Помощь",
                modifier = Modifier.size(33.dp),
                tint = Color.White
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        IconButton(onClick = showSettings) {
            Icon(
                imageVector = Icons.Default.Settings,
                contentDescription = "Настройки",
                modifier = Modifier.size(33.dp),
                tint = Color.White
            )
        }
    }
}

@Composable
fun ButtonBlockLandscape(showSettings: () -> Unit) {
    Column(modifier = Modifier.padding(30.dp)) {
        IconButton(onClick = {}) {
            Icon(
                imageVector = Icons.Default.Info,
                contentDescription = "Помощь",
                modifier = Modifier.size(33.dp),
                tint = Color.White
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        IconButton(onClick = showSettings) {
            Icon(
                imageVector = Icons.Default.Settings,
                contentDescription = "Настройки",
                modifier = Modifier.size(33.dp),
                tint = Color.White
            )
        }
    }
}
// 7 MainScreen.kt