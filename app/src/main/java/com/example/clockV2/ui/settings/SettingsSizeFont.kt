// 9 SettingsSizeFont.kt
package com.example.clockV2.ui.settings

import android.content.res.Configuration
import android.content.res.Resources
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.clockV2.ui.main.MainViewModel
import com.example.clockV2.ui.main.state.ClockUiEvent

@Composable
fun SettingsSizeFont(viewModel: MainViewModel,onClose: () -> Unit) {

    val uiState by viewModel.uiState.collectAsState()
    var fontSize = uiState.textSize.value


    Box(modifier = Modifier.fillMaxSize().clickable(onClick = onClose),
        contentAlignment = Alignment.TopEnd) {
        Card( colors = CardDefaults.cardColors(Color.Black),
            modifier = Modifier.fillMaxWidth(0.5f)
                .offset((-25).dp,25.dp)

        ) {
            Text(
                text = "Размер: ${fontSize.toInt()}sp",
                color = Color.White,
                modifier = Modifier.padding(8.dp)
            )

            Slider(
                fontSize,
                onValueChange = { newState ->
                    viewModel.onEvent(ClockUiEvent.FontSizeChange(newState.sp))
                },
                valueRange = 50f..500f,
                modifier = Modifier.fillMaxWidth(0.9f)
            )
        }

    }
}
// 9 SettingsSizeFont.kt