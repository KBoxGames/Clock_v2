// 12 MainActivity.kt
package com.example.clockV2

import android.os.Build
import android.os.Bundle
import android.view.WindowInsets
import android.view.WindowInsetsController
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.material3.Surface
import androidx.core.view.WindowCompat
import com.example.clockV2.ui.theme.ЧасыTheme


/*


- реализовать FontFamily
 */

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            enableFullScreenMode()
            ЧасыTheme {
                Surface {
                    AppNavigation()
                }
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.R)
    private fun enableFullScreenMode() {

        WindowCompat.setDecorFitsSystemWindows(window, false)
        window.insetsController?.let {
            it.hide(WindowInsets.Type.systemBars())
            it.systemBarsBehavior = WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        }

        // Делаем статус-бар и навигационную панель прозрачными
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )

        // Обработка вырезов и динамического островка
        handleDisplayCutout()
    }

    private fun handleDisplayCutout() {
        // Разрешаем отрисовку за вырезами
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            window.attributes.layoutInDisplayCutoutMode =
                WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES
        }
    }

    // Сохраняем полноэкранный режим при возобновлении активности
    @RequiresApi(Build.VERSION_CODES.R)
    override fun onResume() {
        super.onResume()
        enableFullScreenMode()
    }

    // Сохраняем полноэкранный режим при изменении фокуса окна
    @RequiresApi(Build.VERSION_CODES.R)
    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (hasFocus) {
            enableFullScreenMode()
        }
    }
}

// 12 MainActivity.kt