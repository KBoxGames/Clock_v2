package com.example.clockV2.ui.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Slider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun SettingsSizeFont(closeFontSize: () -> Unit) {

    var fontSize by remember { mutableFloatStateOf(100f) }
    Box(modifier = Modifier.fillMaxSize().clickable(onClick = closeFontSize),
        contentAlignment = Alignment.TopEnd) {
        Card( colors = CardDefaults.cardColors(Color.Black),
            modifier = Modifier.fillMaxWidth(0.5f)
                .offset((-25).dp,25.dp)

            ) {

                Slider(
                    fontSize,
                    onValueChange = { newState ->
                        fontSize = newState
                    },
                    valueRange = 15f..100f,

                    modifier = Modifier.fillMaxWidth(0.9f)
                )
            }

    }
}
