// 5 MainViewModel.kt
package com.example.clockV2.ui.main

import android.util.Log
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import com.example.clockV2.ui.main.state.ClockUiEvent
import com.example.clockV2.ui.main.state.ClockUiState
import com.example.clockV2.ui.theme.availableFonts
import com.example.clockV2.ui.theme.led2
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlin.random.Random

class MainViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(ClockUiState(
        textScreen = "07:30:00",
        dataScreen = "12 августа 2025",
        textSize = 500.sp,
        textColor = Color.White,
        isDate = true,
        isSecond = true,
        fontFamily = led2,
        availableFont = availableFonts,
        isAutoChangeFont = false

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

            ClockUiEvent.ToggleACF -> {
                val newValue = !_uiState.value.isAutoChangeFont
                _uiState.update { it.copy(isAutoChangeFont = newValue) }
            }



        }

    }

    fun AutoChangeFont(){
         _uiState.update {
             it.copy(fontFamily = availableFonts[Random.nextInt(availableFonts.size)].first)
                        }
    }



}

// 5 MainViewModel.kt