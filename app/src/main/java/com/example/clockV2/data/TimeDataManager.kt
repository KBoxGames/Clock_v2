// 1 TimeDateManager.kt
package com.example.clockV2.data

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TimeDateManager @Inject constructor() {

    fun getCurrentTime(format: String = "HH:mm:ss"): String {
        val sdf = SimpleDateFormat(format, Locale.getDefault())
        return sdf.format(Date())
    }

    fun getCurrentDate(format: String = "EEE, dd MMMM yyyy"): String {
        val sdf = SimpleDateFormat(format, Locale.getDefault())
        return sdf.format(Date())
    }

    fun getCurrentTimeWithSeconds(): String {
        return getCurrentTime("HH:mm:ss")
    }

    fun getCurrentTimeWithoutSeconds(): String {
        return getCurrentTime("HH:mm")
    }

    @SuppressLint("SimpleDateFormat")
    fun getCurrentTimestamp(): Long {
        return System.currentTimeMillis()
    }

    fun getCalendarInstance(): Calendar {
        return Calendar.getInstance()
    }
}
// 1 TimeDateManager.kt

