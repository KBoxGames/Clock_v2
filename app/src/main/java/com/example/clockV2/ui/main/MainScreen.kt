package com.example.clockV2.ui.main

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
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



@Composable
fun MainScreen ( viewModel: MainViewModel ){

    val uiState = viewModel.uiState.collectAsState()


    Box(modifier = Modifier.fillMaxSize().padding(16.dp)
        .clickable {viewModel.onEvent(ClockUiEvent.TogleSecond)},

        Alignment.Center){

        Column (modifier = Modifier.fillMaxSize()){

            Text(
                text = uiState.value.textScreen,
                fontFamily = uiState.value.fontFamily,
                color = uiState.value.textColor,
                fontSize = 100.sp
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