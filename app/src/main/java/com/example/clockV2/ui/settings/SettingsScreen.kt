package com.example.clockV2.ui.settings


import android.annotation.SuppressLint
import android.widget.GridLayout
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.clockV2.ui.theme.BorderColor



@SuppressLint("UnusedBoxWithConstraintsScope")
@Composable
fun SettingsScreen(closeSettings: ()->Unit) {

    var isSecond = remember { mutableStateOf(false) }
    var isDate = remember { mutableStateOf(false) }

    BoxWithConstraints (modifier = Modifier
        .fillMaxSize().clickable(onClick = closeSettings),
        //.padding(30.dp),
        Alignment.BottomStart
        ) {
            val isPortrait = maxHeight > maxWidth
            var fractionH = 1f
            var fractionW = 0.5f
        if (isPortrait)
            {
                fractionW = 1f
                fractionH = 0.35f
            } else
            {
                fractionW = 0.5f
                fractionH = 0.85f
            }
            Card(modifier = Modifier
                .fillMaxWidth(fractionW).fillMaxHeight(fractionH)
                .clip(RoundedCornerShape(40.dp)),
                colors = CardDefaults.cardColors(containerColor = Color(0xFF666161))
                ) {
                Button(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                border = BorderStroke(2.dp, color = BorderColor),
                colors = ButtonColors(
                    contentColor = Color.White,
                    disabledContainerColor = Color.Transparent,
                    disabledContentColor = Color.Transparent,
                    containerColor = Color.DarkGray
                ),
                onClick = {}) {
                    Box(modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.CenterEnd) {
                        Text("Настройка шрифта", fontSize = 20.sp)
                    }
            }
                Button(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                        .padding(bottom = 8.dp),
                    border = BorderStroke(2.dp, color = BorderColor),
                    colors = ButtonColors(
                        contentColor = Color.White,
                        disabledContainerColor = Color.Transparent,
                        disabledContentColor = Color.Transparent,
                        containerColor = Color.DarkGray
                    ),
                    onClick = {isSecond.value = !isSecond.value}) {



                    Row (modifier = Modifier.fillMaxWidth(),
                        Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically

                    ){
                        Checkbox(
                        checked = isSecond.value,
                        onCheckedChange = {newState ->
                            isSecond.value = newState
                        },
                    )
                        Box(modifier = Modifier.fillMaxWidth(),
                            contentAlignment = Alignment.CenterEnd
                        ) {
                            Text("Отображать секунды", fontSize = 20.sp)
                        }

                    }

                }
                Button(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth(),
                    border = BorderStroke(2.dp, color = BorderColor),
                    colors = ButtonColors(
                        contentColor = Color.White,
                        disabledContainerColor = Color.Transparent,
                        disabledContentColor = Color.Transparent,
                        containerColor = Color.DarkGray
                    ),
                    onClick = {isDate.value = !isDate.value}) {

                    Row(modifier = Modifier.fillMaxWidth(),
                        Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically) {
                        Checkbox(checked = isDate.value,
                            onCheckedChange = {newState ->
                                isDate.value = newState
                            })

                        Box(modifier = Modifier.fillMaxWidth(),
                            contentAlignment = Alignment.CenterEnd) {
                            Text(" Отображать дату", fontSize = 20.sp)
                        }

                    }


                } }
    }
}


