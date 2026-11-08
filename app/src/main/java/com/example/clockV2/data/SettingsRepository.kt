package com.example.clockV2.data

import android.content.Context
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import com.example.clockV2.ui.theme.art1
import com.example.clockV2.ui.theme.art2
import com.example.clockV2.ui.theme.art3
import com.example.clockV2.ui.theme.art4
import com.example.clockV2.ui.theme.led1
import com.example.clockV2.ui.theme.led2
import com.example.clockV2.ui.theme.led3
import com.example.clockV2.ui.theme.led4
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

// Создаем расширение для DataStore
private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

@Singleton
class SettingsRepository @Inject constructor(@ApplicationContext private val context: Context) {

    // Ключи для хранения настроек
    companion object {
        val IS_SECOND = booleanPreferencesKey("is_second")
        val IS_ECO_MODE = booleanPreferencesKey("is_eco_mode")
        val IS_DATE = booleanPreferencesKey("is_date")
        val TEXT_SIZE = floatPreferencesKey("text_size")
        val TEXT_COLOR_ALPHA = floatPreferencesKey("text_color_alpha")
        val TEXT_COLOR_RED = floatPreferencesKey("text_color_red")
        val TEXT_COLOR_GREEN = floatPreferencesKey("text_color_green")
        val TEXT_COLOR_BLUE = floatPreferencesKey("text_color_blue")
        val FONT_FAMILY = stringPreferencesKey("font_family")
        val IS_AUTO_CHANGE_FONT = booleanPreferencesKey("is_auto_change_font")
        val IS_MAX_SIZE_TEXT = booleanPreferencesKey("is_max_size_text")
        val INTERVAL_HOUR = intPreferencesKey("interval_hour")
        val INTERVAL_MINUTE = intPreferencesKey("interval_minute")
    }

    // Получение потока с настройками
    val settingsFlow: Flow<ClockSettings> = context.dataStore.data
        .map { preferences ->
            ClockSettings(
                isSecond = preferences[IS_SECOND] ?: true,
                isEcoMode = preferences[IS_ECO_MODE] ?: false,
                isDate = preferences[IS_DATE] ?: true,
                textSize = preferences[TEXT_SIZE]?.sp ?: 200.sp,
                textColor = getColorFromPreferences(preferences),fontFamily = preferences[FONT_FAMILY]?.let { getFontFamilyByName(it) } ?: FontFamily.SansSerif,
                isAutoChangeFont = preferences[IS_AUTO_CHANGE_FONT] ?: false,
                isMaxSizeText = preferences[IS_MAX_SIZE_TEXT] ?: false,
                intervalChangeFont = Pair(
                    preferences[INTERVAL_HOUR] ?: 1,
                    preferences[INTERVAL_MINUTE] ?: 0
                )
            )
        }



    // Сохранение настроек
    suspend fun updateSettings(settings: ClockSettings) {
        context.dataStore.edit { preferences ->
            preferences[IS_SECOND] = settings.isSecond
            preferences[IS_ECO_MODE] = settings.isEcoMode
            preferences[IS_DATE] = settings.isDate
            preferences[TEXT_SIZE] = settings.textSize.value
            saveColorToPreferences(preferences, settings.textColor)
            preferences[FONT_FAMILY] = getFontFamilyName(settings.fontFamily)
            preferences[IS_AUTO_CHANGE_FONT] = settings.isAutoChangeFont
            preferences[IS_MAX_SIZE_TEXT] = settings.isMaxSizeText
            preferences[INTERVAL_HOUR] = settings.intervalChangeFont.first
            preferences[INTERVAL_MINUTE] = settings.intervalChangeFont.second
        }
    }

    private fun saveColorToPreferences(preferences: MutablePreferences, color: Color) {
        preferences[TEXT_COLOR_ALPHA] = color.alpha
        preferences[TEXT_COLOR_RED] = color.red
        preferences[TEXT_COLOR_GREEN] = color.green
        preferences[TEXT_COLOR_BLUE] = color.blue
    }

    private fun getColorFromPreferences(preferences: Preferences): Color {
        val alpha = preferences[TEXT_COLOR_ALPHA] ?: 1f
        val red = preferences[TEXT_COLOR_RED] ?: 1f
        val green = preferences[TEXT_COLOR_GREEN] ?: 1f
        val blue = preferences[TEXT_COLOR_BLUE] ?: 1f

        return Color(red, green, blue, alpha)
    }

    // Вспомогательные функции для преобразования шрифтов
    private fun getFontFamilyByName(name: String): FontFamily {
        return when (name) {
            "SansSerif" -> FontFamily.SansSerif
            "Serif" -> FontFamily.Serif
            "Monospace" -> FontFamily.Monospace
            "Cursive" -> FontFamily.Cursive
            "АРТ 1" -> art1
            "АРТ 2" -> art2
            "АРТ 3" -> art3
            "АРТ 4" -> art4
            "ЛЭД 1" -> led1
            "ЛЭД 2" -> led2
            "ЛЭД 3" -> led3
            "ЛЭД 4" -> led4
            else -> FontFamily.SansSerif
        }
    }

    private fun getFontFamilyName(fontFamily: FontFamily): String {
        return when (fontFamily) {
            FontFamily.SansSerif -> "SansSerif"
            FontFamily.Serif -> "Serif"
            FontFamily.Monospace -> "Monospace"
            FontFamily.Cursive -> "Cursive"
            art1 -> "АРТ 1"
            art2 -> "АРТ 2"
            art3 -> "АРТ 3"
            art4 -> "АРТ 4"
            led1 -> "ЛЭД 1"
            led2 -> "ЛЭД 2"
            led3 -> "ЛЭД 3"
            led4 -> "ЛЭД 4"
            else -> "SansSerif"
        }
    }


}

// Модель данных для настроек
data class ClockSettings(
    val isSecond: Boolean,
    val isEcoMode: Boolean,
    val isDate: Boolean,
    val textSize: TextUnit,
    val textColor: Color,
    val fontFamily: FontFamily,
    val isAutoChangeFont: Boolean,
    val isMaxSizeText: Boolean,
    val intervalChangeFont: Pair<Int, Int>
)