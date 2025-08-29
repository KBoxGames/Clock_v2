
// 4 MainScreen.kt
package com.example.clockV2.ui.main

import android.annotation.SuppressLint
import android.net.http.HeaderBlock
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.clockV2.ui.main.state.ClockUiState
import com.example.clockV2.ui.main.textUtilits.DynamicText



@SuppressLint("UnusedBoxWithConstraintsScope")
@Composable
fun MainScreen ( viewModel: MainViewModel ){

    val uiState by viewModel.uiState.collectAsState()

    BoxWithConstraints (modifier = Modifier.fillMaxSize().padding(16.dp).background(Color.Black)
         .clickable {  }, contentAlignment = Alignment.Center,

         )
    {
        val isPortrait = maxHeight > maxWidth
        var offset = 0f

        if (!isPortrait)
        {
            offset = 0f
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                ButtonBlockLandscape()
            }

        }
        else{
            offset = 300f
            Column(modifier = Modifier.fillMaxSize()) { ButtonBlockPortrait() }
            }

        ClockDisplay(uiState = uiState,offset)
    }
}


@Preview(showBackground = true, heightDp = 2000, widthDp = 2000)
@Composable
fun PreviewMainScreen()
{
    MainScreen(viewModel = MainViewModel())
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
        isShowDate = uiState.isDate,
        offset = offset
    )
}
@Preview
@Composable
fun ButtonBlockPortrait()
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