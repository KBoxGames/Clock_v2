package com.example.clockV2.ui.main

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.clockV2.ui.main.state.ClockUiEvent
import com.example.clockV2.ui.main.textUtilits.calculateDinamicSizeFont


@SuppressLint("UnusedBoxWithConstraintsScope")
@Composable
fun MainScreen ( viewModel: MainViewModel ){

    val uiState = viewModel.uiState.collectAsState()


    BoxWithConstraints (modifier = Modifier.fillMaxSize().padding(16.dp)
         .clickable {viewModel.onEvent(ClockUiEvent.TogleSecond)},
         )
    {

         val dinamicFontSize = calculateDinamicSizeFont(
             maxW = maxWidth,
             maxH = maxHeight,
             text = uiState.value.textScreen,
             fontFamity = uiState.value.fontFamily
         )

         Column (modifier = Modifier.fillMaxSize(),
             horizontalAlignment = Alignment.CenterHorizontally,
             verticalArrangement = Arrangement.Center){

             Text(
                 text = uiState.value.textScreen,
                 fontFamily = uiState.value.fontFamily,
                 color = uiState.value.textColor,
                 fontSize = dinamicFontSize.sp
                 )
         }

     }
}

@Preview(showBackground = true, device = "id:pixel_5")
@Composable
fun PreviewMainScreen()
{
    MainScreen(viewModel = MainViewModel())
}