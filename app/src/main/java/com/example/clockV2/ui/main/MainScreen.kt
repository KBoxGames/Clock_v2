package com.example.clockV2.ui.main

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.clockV2.ui.main.state.ClockUiEvent
import com.example.clockV2.ui.main.textUtilits.calculateDynamicSizeFont


@SuppressLint("UnusedBoxWithConstraintsScope")
@Composable
fun MainScreen ( viewModel: MainViewModel ){

    val uiState = viewModel.uiState.collectAsState()

    BoxWithConstraints (modifier = Modifier.fillMaxSize().padding(16.dp)
         .clickable {viewModel.onEvent(ClockUiEvent.TogleSecond)},
         )
    {
        var isLandscape = if (maxWidth <= maxHeight) true else false
         Column (modifier = Modifier.fillMaxSize(),
             horizontalAlignment = Alignment.CenterHorizontally,
             verticalArrangement =  if (isLandscape) Arrangement.Top else Arrangement.Center){

             if (isLandscape) Spacer(Modifier.height(250.dp))
             var maxSize = calculateDynamicSizeFont(
                 text = uiState.value.textScreen,
                 colorText = uiState.value.textColor,
                 maxTextSize = 600.sp,
                 maxLine = 1,
                 style = TextStyle.Default
             )
             Spacer(Modifier.height(5.dp))
             Text("maxSize $maxSize",
                 fontSize = maxSize * 0.15f)
         }

     }
}

@Preview(showBackground = true, device = "id:pixel_5")
@Composable
fun PreviewMainScreen()
{
    MainScreen(viewModel = MainViewModel())
}