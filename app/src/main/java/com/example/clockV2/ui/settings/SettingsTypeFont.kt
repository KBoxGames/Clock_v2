//10 SettingsTypeFont.kt
package com.example.clockV2.ui.settings

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
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

val FontList = listOf(
    "Просто шрифт",
    "Шрифт светлый",
    "Темный шрифт",
    "Отличный шрифт",
    "Магический шрифт",
    "Правдивый шрифт",
    "Быстрый шрифт",
    "Короткий шрифт",
    "Длиный шрифт",
    "Коричневый шрифт",
    "Индукционный шрифт"

)

val hoursList = listOf(
    1,2,3,4,5,6,7,8,9,10,12,13,14,15,16,17,18,19,20,21,22,23,0
)

@Composable
fun SettingsTypeFon(onClose:()->Unit) {

    BoxWithConstraints(modifier = Modifier.fillMaxSize().clickable(onClick = onClose),
         contentAlignment = Alignment.BottomEnd) {

        var isPortrait by remember { mutableStateOf(false) }
        if (maxHeight < maxWidth) isPortrait = true

        if (isPortrait) {
            Card(modifier = Modifier.fillMaxWidth(0.8f))
            {
                Row(horizontalArrangement = Arrangement.Center)
                {
                    Spacer(Modifier.width(20.dp))
                    BlockFont()
                    Spacer(Modifier.width(8.dp))
                    BlockFontSettings()
                }
            }
        } else
        {
            Card (modifier = Modifier
                .fillMaxHeight(0.65f))
            {
            Box(modifier = Modifier.padding(8.dp)) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    BlockFont(0.8f, 0.3f)
                    Spacer(Modifier.height(8.dp))
                    BlockFontSettings(0.8f,1f)
                }

            }

            }
        }
    }
}


@Composable
fun BlockFont(fractW: Float = 0.4f, fractH: Float = 0.8f)
{
    Column(horizontalAlignment = Alignment.CenterHorizontally)
    {
        Spacer(Modifier.height(8.dp))
        Text("Шрифт", fontSize = 30.sp)
        Spacer(Modifier.height(16.dp))
        Card(
            modifier = Modifier
                .fillMaxWidth(fractW)
                .fillMaxHeight(fractH),
            colors = CardDefaults.cardColors(Color.Gray)
        ) {
            Box(modifier = Modifier.padding(8.dp))
            {
                Card(modifier = Modifier.fillMaxWidth()) {
                    Box(modifier = Modifier.padding(8.dp)) {
                        Row {
                            LazyColumn(Modifier.weight(1f)) {
                                items(FontList) { item ->
                                    Text(item, fontSize = 20.sp)
                                }
                            }
                            Box(
                                modifier = Modifier.fillMaxHeight(),
                                contentAlignment = Alignment.TopEnd
                            ) {
                                Column {
                                    Icon(
                                        imageVector = Icons.Default.KeyboardArrowUp,
                                        contentDescription = "Вверх"
                                    )
                                    Spacer(Modifier.weight(1f))
                                    Icon(
                                        imageVector = Icons.Default.KeyboardArrowDown,
                                        contentDescription = "Вниз"
                                    )
                                }

                            }
                        }

                    }
                }
            }
        }
        //Spacer(Modifier.height(20.dp))
    }
}
@Composable
fun BlockFontSettings(fractW: Float = 0.95f, fractH: Float = 0.8f)
{
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Spacer(Modifier.height(8.dp))
        Text("Настройки шрифта", fontSize = 30.sp)
        Spacer(Modifier.height(16.dp))
        Card(
            modifier = Modifier
                .fillMaxWidth(fractW)
                .fillMaxHeight(fractH),
            colors = CardDefaults.cardColors(Color.Gray)
        ) {
            Box(modifier = Modifier.padding(8.dp)) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {

                    Card() {
                        Box(modifier = Modifier.padding(8.dp)) {
                            Column {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text("Автосмена шрифта")
                                    Spacer(Modifier.weight(1f))
                                    Switch(checked = true, onCheckedChange = {})
                                }
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text("Фиксированный размер")
                                    Spacer(Modifier.weight(1f))
                                    Switch(checked = true, onCheckedChange = {})
                                }
                            }
                        }
                    }
                    Spacer(Modifier.height(8.dp))
                    Card() {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Spacer(Modifier.height(50.dp).weight(1f))
                            Text("Интервал смены шрифта")
                            Spacer(Modifier.height(50.dp).weight(1f))

                        }
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Spacer(Modifier.weight(1f))
                            Column(modifier = Modifier.fillMaxHeight()) {
                                Icon(
                                    imageVector = Icons.Default.KeyboardArrowUp,
                                    contentDescription = "Вверх"
                                )
                                Spacer(Modifier.weight(1f))
                                Icon(
                                    imageVector = Icons.Default.KeyboardArrowDown,
                                    contentDescription = "Вниз"
                                )
                            }
                            LazyColumn(
                                modifier = Modifier.size(40.dp),

                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                items(hoursList) { item ->
                                    Text(item.toString(), fontSize = 30.sp)
                                }
                            }
                            Text(":", fontSize = 30.sp)
                            LazyColumn(
                                modifier = Modifier.size(40.dp),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                items(hoursList) { item ->
                                    Text(item.toString(), fontSize = 30.sp)
                                }
                            }
                            Spacer(Modifier.weight(1f))

                        }
                        Spacer(Modifier.height(8.dp))

                    }
                    Spacer(Modifier.height(8.dp))


                }
            }
        }
        Spacer(Modifier.height(20.dp))
    }
}
//10 SettingsTypeFont.kt
