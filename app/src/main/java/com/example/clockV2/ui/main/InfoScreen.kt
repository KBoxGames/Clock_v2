package com.example.clockV2.ui.main

import android.widget.Space
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.savedstate.serialization.serializers.SparseArraySerializer

@Composable
fun infoScreen(onClose: ()-> Unit)
{
    var fraction : Float = 0.7f
    BoxWithConstraints (modifier = Modifier.fillMaxSize()
        .clickable(onClick = onClose),
        Alignment.Center){
        if (maxWidth>maxHeight) fraction = 0.4f else 0.7f
        Card(modifier = Modifier.fillMaxWidth(fraction)) {
            Box(modifier = Modifier.padding(16.dp)) {
                Column {
                    Text(
                        "О Приложении:",
                        fontSize = 25.sp
                    )
                    Spacer(Modifier.height(16.dp))
                    Row { Spacer(Modifier.weight(1f))
                        Text("Часы",
                            fontSize = 20.sp)
                        Spacer(Modifier.weight(1f))
                    }
                    Spacer(Modifier.height(8.dp))
                    Text("Версия 2.0")
                    Text("Константин Сергеевич С.")
                    Text("© 2025 КСС Софт")


                }
            }
        }
    }
}