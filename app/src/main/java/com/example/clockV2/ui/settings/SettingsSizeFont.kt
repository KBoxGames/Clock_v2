
// 9 SettingsSizeFont.kt
package com.example.clockV2.ui.settings

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Slider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import com.example.clockV2.ui.main.MainViewModel
import com.example.clockV2.ui.main.state.ClockUiEvent

@Composable
fun SettingsSizeFont(viewModel: MainViewModel,onClose: () -> Unit) {

    val uiState by viewModel.uiState.collectAsState()
    var fontSize by remember { mutableStateOf(uiState.textSize) }
    val localDensity = LocalDensity.current // Нужен для преобразования между sp и dp

    val fontSizeDp = with(localDensity) { fontSize.toDp().value }

    Box(modifier = Modifier.fillMaxSize().clickable(onClick = onClose),
        contentAlignment = Alignment.TopEnd) {
        Card( colors = CardDefaults.cardColors(Color.Black),
            modifier = Modifier.fillMaxWidth(0.5f)
                .offset((-25).dp,25.dp)

            ) {

                Slider(
                    fontSizeDp,
                    onValueChange = { newState ->
                        fontSize = with(localDensity) { newState.toSp()}
                        viewModel.onEvent(ClockUiEvent.FontSizeChange(fontSize))
                                    },
                    valueRange = 15f..100f,

                    modifier = Modifier.fillMaxWidth(0.9f)
                )
            }

    }
}
// 9 SettingsSizeFont.kt

