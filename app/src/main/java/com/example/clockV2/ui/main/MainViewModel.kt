
// 5 MainViewModel.kt
package com.example.clockV2.ui.main

import android.util.Log
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import com.example.clockV2.ui.main.state.ClockUiEvent
import com.example.clockV2.ui.main.state.ClockUiState
import com.example.clockV2.ui.theme.art1
import com.example.clockV2.ui.theme.art2
import com.example.clockV2.ui.theme.art3
import com.example.clockV2.ui.theme.art4
import com.example.clockV2.ui.theme.availableFonts
import com.example.clockV2.ui.theme.led2
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class MainViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(ClockUiState(
        textScreen = "07:30:54",
        dataScreen = "12 августа 2025",
        textSize = 150.sp,
        textColor = Color.White,
        isDate = true,
        isSecond = true,
        fontFamily = led2,
        availableFont = availableFonts

    ))

    val uiState: StateFlow<ClockUiState> = _uiState.asStateFlow()

    fun onEvent (event : ClockUiEvent){
        Log.d("MainViewModel", "Event: $event")

        when(event)
        {
            is ClockUiEvent.FontSizeChange -> {
                _uiState.update { it.copy(textSize = event.newTextSize) }
            }

            is ClockUiEvent.FontColorChange -> {
                _uiState.update { it.copy(textColor = event.newTextColor) }
            }

            is ClockUiEvent.FontFamilyChange -> {
                _uiState.update { it.copy(fontFamily = event.newFontFamily) }
            }

            ClockUiEvent.ToggleData -> {
                val newValue = !_uiState.value.isDate
            _uiState.update { it.copy(isDate = newValue) }
            }
            ClockUiEvent.ToggleSecond -> {
                val newValue = !_uiState.value.isSecond
               _uiState.update { it.copy(isSecond = newValue) }
            }
        }

    }

}

// 5 MainViewModel.kt