
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
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.clockV2.ui.main.state.ClockUiEvent
import com.example.clockV2.ui.main.textUtilits.DynamicText
import com.example.clockV2.ui.settings.SettingsScreen
import com.example.clockV2.ui.theme.art1
import com.example.clockV2.ui.theme.art2
import com.example.clockV2.ui.theme.art3
import com.example.clockV2.ui.theme.art4
import com.example.clockV2.ui.theme.led1
import com.example.clockV2.ui.theme.led2
import com.example.clockV2.ui.theme.led3
import com.example.clockV2.ui.theme.led4


@SuppressLint("UnusedBoxWithConstraintsScope")
@Composable
fun MainScreen ( viewModel: MainViewModel ){

    val uiState = viewModel.uiState.collectAsState()

    BoxWithConstraints (modifier = Modifier.fillMaxSize().padding(16.dp).background(Color.Black)
         .clickable {  },
         )
    {
        val isPortrait = maxHeight > maxWidth
        if (!isPortrait)
        {
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                ButtonBlockLandscape()

            }

        }
         Column (modifier = Modifier.fillMaxSize(),
             horizontalAlignment = Alignment.CenterHorizontally,
             verticalArrangement =  if (isPortrait) Arrangement.Top else Arrangement.Center){

             if (isPortrait) {
                 ButtonBlockPortrait()
                 Spacer(Modifier.height(150.dp))
             }
             DynamicText(
                 textTime = uiState.value.textScreen,
                 colorText = uiState.value.textColor,
                 maxTextSize = uiState.value.textSize,// регулировать размер шрифта
                 maxLine = 1,
                 style = TextStyle(fontFamily = uiState.value.fontFamily),
                 textDate = uiState.value.dataScreen,
                 isShowDate = uiState.value.isDate
             )
         }

     }
}

@Preview(showBackground = true, heightDp = 2000, widthDp = 2000)
@Composable
fun PreviewMainScreen()
{
    MainScreen(viewModel = MainViewModel())
}
// 4 MainScreen.kt
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