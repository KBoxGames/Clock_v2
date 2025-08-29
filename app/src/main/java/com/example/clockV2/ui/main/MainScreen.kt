
// 4 MainScreen.kt
package com.example.clockV2.ui.main

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.clockV2.ui.main.state.ClockUiState
import com.example.clockV2.ui.main.textUtilits.DynamicText
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@SuppressLint("UnusedBoxWithConstraintsScope")
@Composable
fun MainScreen (viewModel: MainViewModel ){

    val uiState by viewModel.uiState.collectAsState()
    val navController: NavController? = null
    var isShowButon by remember { mutableStateOf(false) }
    var timerJob by remember { mutableStateOf<Job?>(null) }

    BoxWithConstraints (modifier = Modifier.fillMaxSize().padding(16.dp).background(Color.Black)
         .clickable { isShowButon = true
                    timerJob?.cancel()
                    timerJob = CoroutineScope(Dispatchers.Main).launch {
                        delay(6000)
                        isShowButon = false
                    }
                    }, contentAlignment = Alignment.Center,

         )
    {
        val isPortrait = maxHeight > maxWidth
        var offset = 0f

        if (!isPortrait)
        {
            offset = 0f
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
               if (isShowButon) ButtonBlockLandscape()
            }

        }
        else{
            offset = 300f
            Column(modifier = Modifier.fillMaxSize()) {
                if (isShowButon) ButtonBlockPortrait(navController)
            }
            }

        ClockDisplay(uiState = uiState,offset)
    }
}


@Composable
fun ClockDisplay(uiState: ClockUiState, offset: Float)
{
    DynamicText(
        textTime = uiState.textScreen,
        colorText = uiState.textColor,
        maxTextSize = uiState.textSize,// регулировать размер шрифта
        maxLine = 1,
        style = TextStyle(fontFamily = uiState.fontFamily),
        textDate = uiState.dataScreen,
        fontFamily = uiState.fontFamily,
        isShowDate = uiState.isDate,
        offset = offset
    )
}

@Composable
fun ButtonBlockPortrait(navController: NavController?)
{
    Row(modifier = Modifier.fillMaxWidth()) {
        IconButton(onClick = {},

        ) {
            Icon(
                imageVector = Icons.Default.Info,
                contentDescription = "Помощь" ,
                modifier = Modifier.size(33.dp),
                tint = Color.White

            )

        }

        Spacer(modifier = Modifier.weight(1f))


        IconButton(onClick = {navController?.navigate("settings")},
            ) {
            Icon(
                imageVector = Icons.Default.Settings,
                contentDescription = "Настройки",
                modifier = Modifier.size(33.dp),
                tint = Color.White
            )
        }
    }
}
@Composable
fun ButtonBlockLandscape()
{
Column() {
    IconButton(onClick = {},

        ) {
        Icon(
            imageVector = Icons.Default.Info,
            contentDescription = "Помощь" ,
            modifier = Modifier.size(33.dp),
            tint = Color.White

        )

    }

    Spacer(modifier = Modifier.weight(1f))


    IconButton(onClick = {},
    ) {
        Icon(
            imageVector = Icons.Default.Settings,
            contentDescription = "Настройки",
            modifier = Modifier.size(33.dp),
            tint = Color.White
        )
    }
}
}

// 4 MainScreen.kt