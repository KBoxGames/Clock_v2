
// 5 MainViewModel.kt
package com.example.clockV2.ui.main

import android.util.Log
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import com.example.clockV2.ui.main.state.ClockUiEvent
import com.example.clockV2.ui.main.state.ClockUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class MainViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(ClockUiState(
        textScreen = "24:30:00",
        "12 августа 2025",
        textSize = 200f,
        textColor = Color.Black,
        isDate = true,
        isSecond = false,

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