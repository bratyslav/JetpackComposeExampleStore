package com.example.jetpackexamplestore.ui

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.jetpackexamplestore.ui.screen.bucket.BUCKET_FONT_SIZE

@Composable
fun ChangeCountButton(text: String, size: Dp = 25.dp, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .size(size)
            .border(1.dp, Color.LightGray)
            .clickable { onClick() }
    ) {
        Box(Modifier.wrapContentSize(Alignment.Center)) {
            Text(text, fontSize = BUCKET_FONT_SIZE)
        }
    }
}