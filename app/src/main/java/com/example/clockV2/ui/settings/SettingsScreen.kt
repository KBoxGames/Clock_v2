// 11 SettingsScreen.kt
package com.example.clockV2.ui.settings

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.clockV2.ui.main.MainViewModel
import com.example.clockV2.ui.main.state.ClockUiEvent
import com.example.clockV2.ui.theme.BorderColor


@Composable
fun SettingsScreen(
    viewModel: MainViewModel,
    onClose : () -> Unit,
    onFontsClick : () -> Unit
) {

    val uiState by viewModel.uiState.collectAsState()

    BoxWithConstraints (modifier = Modifier
        .fillMaxSize().clickable(onClick = onClose),
        contentAlignment = Alignment.BottomStart
    ) {
        val isPortrait = maxHeight > maxWidth
        var fractionH: Float
        var fractionW: Float
        // Определение размера карточки в зависимости от ориентации
        if (isPortrait)
        {
            fractionW = 1f
            fractionH = 0.35f
        } else
        {
            fractionW = 0.5f
            fractionH = 0.8f
        }
        Card(modifier = Modifier
            .fillMaxWidth(fractionW).fillMaxHeight(fractionH)
            .clip(RoundedCornerShape(40.dp)),
            colors = CardDefaults.cardColors(containerColor = Color(0xFF666161))
        ) {
            Button(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                border = BorderStroke(2.dp, color = BorderColor),
                colors = ButtonColors(
                    contentColor = Color.White,
                    disabledContainerColor = Color.Transparent,
                    disabledContentColor = Color.Transparent,
                    containerColor = Color.DarkGray
                ),
                onClick = onFontsClick) {
                Box(modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.CenterEnd) {
                    Text("Настройка шрифта", fontSize = 20.sp)
                }
            }
            Spacer(Modifier.height(8.dp))
            Button(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                border = BorderStroke(2.dp, color = BorderColor),
                colors = ButtonColors(
                    contentColor = Color.White,
                    disabledContainerColor = Color.Transparent,
                    disabledContentColor = Color.Transparent,
                    containerColor = Color.DarkGray
                ),
                onClick = {viewModel.onEvent(ClockUiEvent.ToggleSecond)}) {



                Row (modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically

                ){
                    Checkbox(
                        checked = uiState.isSecond,
                        onCheckedChange = {viewModel.onEvent(ClockUiEvent.ToggleSecond)
                        },
                    )
                    Box(modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.CenterEnd
                    ) {
                        Text("Отображать секунды", fontSize = 20.sp)
                    }

                }

            }
            Spacer(Modifier.height(8.dp))
            Button(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                border = BorderStroke(2.dp, color = BorderColor),
                colors = ButtonColors(
                    contentColor = Color.White,
                    disabledContainerColor = Color.Transparent,
                    disabledContentColor = Color.Transparent,
                    containerColor = Color.DarkGray
                ),
                onClick = { viewModel.onEvent(ClockUiEvent.ToggleData) }) {

                Row(modifier = Modifier.fillMaxWidth(),
                    Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically) {
                    Checkbox(checked = uiState.isDate,
                        onCheckedChange = {viewModel.onEvent(ClockUiEvent.ToggleData)

                        })

                    Box(modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.CenterEnd) {
                        Text(" Отображать дату", fontSize = 20.sp)
                    }

                }
            }
        }
    }
}
// 11 SettingsScreen.kt