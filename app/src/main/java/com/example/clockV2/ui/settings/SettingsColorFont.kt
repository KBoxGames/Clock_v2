// 7 SettingsColorFont.kt
package com.example.clockV2.ui.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.clockV2.ui.theme.B2
import com.example.clockV2.ui.theme.B3
import com.example.clockV2.ui.theme.BD
import com.example.clockV2.ui.theme.BD1
import com.example.clockV2.ui.theme.BD2
import com.example.clockV2.ui.theme.BP
import com.example.clockV2.ui.theme.BP1
import com.example.clockV2.ui.theme.BP2
import com.example.clockV2.ui.theme.BP3
import com.example.clockV2.ui.theme.Bl
import com.example.clockV2.ui.theme.GB
import com.example.clockV2.ui.theme.GB1
import com.example.clockV2.ui.theme.GB2
import com.example.clockV2.ui.theme.Gr
import com.example.clockV2.ui.theme.Gr1
import com.example.clockV2.ui.theme.Gr2
import com.example.clockV2.ui.theme.Gr3
import com.example.clockV2.ui.theme.Or
import com.example.clockV2.ui.theme.Or1
import com.example.clockV2.ui.theme.PR
import com.example.clockV2.ui.theme.PR1
import com.example.clockV2.ui.theme.PR2
import com.example.clockV2.ui.theme.PR3
import com.example.clockV2.ui.theme.Pk
import com.example.clockV2.ui.theme.Rd
import com.example.clockV2.ui.theme.Wh
import com.example.clockV2.ui.theme.YG
import com.example.clockV2.ui.theme.YG1
import com.example.clockV2.ui.theme.YG2
import com.example.clockV2.ui.theme.YL
import com.example.clockV2.ui.theme.YO
import com.example.clockV2.ui.theme.YO1


val colorFonts = listOf<Color>(
    Wh, Rd, Or,Or1,YO,YO1,YL,YG,YG2,
    YG1,Gr,Gr1,Gr2,Gr3,GB,GB1,GB2,Bl,
    B2,B3,BD,BD1,BD2,BP,BP1,BP2,BP3,
    Pk,PR,PR1,PR2,PR3)


@Composable
fun itemColor(color: Color, onSelectColor: (Color) -> Unit, onClose: () -> Unit)
{
    //Добавить выделение текущего цвета
    val colorBox = remember { mutableStateOf(Color.Black) }
    Box(modifier = Modifier.size(50.dp)
        .background(color)
        .border(4.dp,color = colorBox.value)
        .clickable(onClick = {
            colorBox.value = Color.White
            onSelectColor(color)
            onClose()

        })
    ){}
}

@Composable
fun SettingsColorFont(onClose:()->Unit, onSelectColor:(Color)->Unit) {
    Box(
        modifier = Modifier.fillMaxSize().clickable(onClick = onClose),
        contentAlignment = Alignment.TopStart
    )
    {
        Row(modifier = Modifier.fillMaxWidth()
            .offset(0.dp,30.dp)
            .border(10.dp, Color.Black),
            horizontalArrangement = Arrangement.SpaceBetween) {
            Icon(imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                contentDescription = "назад",
                modifier = Modifier.size(50.dp)
            )
            LazyRow(modifier = Modifier.weight(1f)) {
                items(colorFonts) { color ->
                    itemColor(color,onSelectColor,onClose)

                }
            }
            Icon(imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                contentDescription = "вперед",
                modifier = Modifier.size(50.dp)
            )
        }
    }
}
// 7 SettingsColorFont.kt