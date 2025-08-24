package com.example.clockV2.ui.settings

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.clockV2.ui.main.MainViewModel
import com.example.clockV2.ui.main.state.ClockUiEvent

@Composable
fun SettingsScreen(viewModel: MainViewModel) {
    val uiState = viewModel.uiState.collectAsState()

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Выберите шрифт:", fontSize = 20.sp)

        LazyColumn {
            items(uiState.value.availableFont.size) { index ->
                val (fontFamily, fontName) = uiState.value.availableFont[index]
                Text(
                    text = fontName,
                    fontFamily = fontFamily,
                    fontSize = 18.sp,
                    modifier = Modifier
                        .padding(8.dp)
                        .clickable {
                            viewModel.onEvent(ClockUiEvent.FontFamilyChange(fontFamily))
                        }
                )
            }
        }
    }
}