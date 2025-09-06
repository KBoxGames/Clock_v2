// 8 MainViewModel.kt
package com.example.clockV2.ui.main

import android.util.Log
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.clockV2.data.TimeDateManager
import com.example.clockV2.ui.main.state.ClockUiEvent
import com.example.clockV2.ui.main.state.ClockUiState
import com.example.clockV2.ui.theme.availableFonts
import com.example.clockV2.ui.theme.led2
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlin.random.Random

@HiltViewModel
class MainViewModel @Inject constructor(
    private val timeDateManager: TimeDateManager
) : ViewModel(){
    private val _uiState = MutableStateFlow(ClockUiState(
        textScreen = "00:00:00",
        dataScreen = " ",
        textSize = 500.sp,
        textColor = Color.White,
        isDate = true,
        isSecond = true,
        fontFamily = led2,
        availableFont = availableFonts,
        isAutoChangeFont = false,
        isMaxSizeText = false,
        intervalChangeFont = Pair(0,1)
    ))

    val uiState: StateFlow<ClockUiState> = _uiState.asStateFlow()

    private var fontChangeTimer: Job? = null
    private var timeUpdateJob: Job? = null
     var fontChangeInterval = 600000L // 10 секунд

    init {
        // Запускаем наблюдение за изменением состояния автосмены шрифта
        startFontChangeTimerObservation()

        // Запускаем обновление времени
        startTimeUpdates()
        // Обновляем дату сразу
        updateDate()
    }
    private fun startTimeUpdates() {
        // Останавливаем предыдущую job, если она существует
        timeUpdateJob?.cancel()

        timeUpdateJob = viewModelScope.launch {
            while (isActive) {
                updateTime()
                delay(1000) // Обновляем каждую секунду
            }
        }
    }

    private fun updateTime() {
        val timeFormat = if (_uiState.value.isSecond) "HH:mm:ss" else "HH:mm"
        val currentTime = timeDateManager.getCurrentTime(timeFormat)

        _uiState.update { it.copy(textScreen = currentTime) }
    }

    private fun updateDate() {
        if (_uiState.value.isDate) {
            val currentDate = timeDateManager.getCurrentDate()
            _uiState.update { it.copy(dataScreen = currentDate) }
        } else {
            _uiState.update { it.copy(dataScreen = null) }
        }
    }
    fun onEvent (event : ClockUiEvent){
        Log.d("MainViewModel", "Event: $event")

        when(event)
        {
            is ClockUiEvent.SetIntervalChangeFont -> {
                _uiState.update { it.copy( intervalChangeFont = event.newIntervalChangeFont) }
                CalculateICF()
            }

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
                updateDate()
            }
            ClockUiEvent.ToggleSecond -> {
                val newValue = !_uiState.value.isSecond
                _uiState.update { it.copy(isSecond = newValue) }
                updateTime()
            }

            ClockUiEvent.ToggleMSF -> {
                val newValue = !_uiState.value.isMaxSizeText
                _uiState.update { it.copy(isMaxSizeText = newValue) }
                allwaysMaxSizeFont()
            }

            ClockUiEvent.ToggleACF -> {
                val newValue = !_uiState.value.isAutoChangeFont
                _uiState.update { it.copy(isAutoChangeFont = newValue) }
                // Останавливаем или запускаем таймер при изменении состояния
                if (newValue) {
                    startFontChangeTimer()
                } else {
                    stopFontChangeTimer()
                }
            }

        }

    }

    fun autoChangeFont() {
        val currentFonts = availableFonts.map { it.first }
        val currentFont = _uiState.value.fontFamily
        val availableFontsWithoutCurrent = currentFonts.filter { it != currentFont }

        if (availableFontsWithoutCurrent.isNotEmpty()) {
            val newFont = availableFontsWithoutCurrent[Random.nextInt(availableFontsWithoutCurrent.size)]
            _uiState.update { it.copy(fontFamily = newFont) }
        } else if (currentFonts.isNotEmpty()) {
            // Если есть только один шрифт, все равно меняем на него
            _uiState.update { it.copy(fontFamily = currentFonts[0]) }
        }
    }

    override fun onCleared() {
        super.onCleared()
        stopFontChangeTimer()
    }

    private fun startFontChangeTimerObservation() {
        viewModelScope.launch {
            _uiState.collect { state ->
                if (state.isAutoChangeFont && fontChangeTimer == null) {
                    startFontChangeTimer()
                } else if (!state.isAutoChangeFont && fontChangeTimer != null) {
                    stopFontChangeTimer()
                }
            }
        }
    }

    private fun stopFontChangeTimer() {
        fontChangeTimer?.cancel()
        fontChangeTimer = null
    }

    private fun startFontChangeTimer() {
        stopFontChangeTimer() // Сначала останавливаем предыдущий таймер

        fontChangeTimer = viewModelScope.launch {
            while (true) {
                delay(fontChangeInterval)
                if (_uiState.value.isAutoChangeFont) {
                    autoChangeFont()
                } else {
                    break // Выходим из цикла, если автосмена отключена
                }
            }
        }
    }

   private fun allwaysMaxSizeFont()
    {
        if (_uiState.value.isMaxSizeText)
        {
            _uiState.update { it.copy(textSize = 280.sp) }
        }
    }
    private fun CalculateICF()
    {
        val hour = _uiState.value.intervalChangeFont.first
        var minute = _uiState.value.intervalChangeFont.second

        fontChangeInterval = (((hour * 60) + minute)*60 * 1000).toLong()
    }
}

// 8 MainViewModel.kt