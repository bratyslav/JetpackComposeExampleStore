package com.example.jetpackexamplestore.ui.screen.bucket

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Popup
import com.example.jetpackexamplestore.ui.theme.BACKGROUND_GRAY
import com.google.protobuf.Empty

@Composable
fun OrderPopup(width: Dp) {
    Popup(alignment = Alignment.Center) {
        Box {
            Column(
                Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color.DarkGray)
            ) {
                OrderPopupOption("Payment by card", width)
                OrderPopupOption("Cash on delivery", width)
            }
        }
    }
}

@Composable
fun OrderPopupOption(option: String, width: Dp) {
    Row(
        Modifier
            .width(width)
            .border(1.dp, Color.White)
            .padding(32.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        EmptyCircle(
            size = 24.dp,
            borderWidth = 2.dp,
            color = Color.White,
            backgroundColor = Color.DarkGray
        )
        Text(
            option,
            fontSize = 24.sp,
            color = Color.White,
            modifier = Modifier.padding(start = 16.dp)
        )
    }
}

@Composable
fun EmptyCircle(size: Dp, borderWidth: Dp, color: Color, backgroundColor: Color) {
    OutlinedButton(
        onClick = {},
        modifier = Modifier.size(size),
        shape = CircleShape,
        border = BorderStroke(borderWidth, color),
        contentPadding = PaddingValues(0.dp),
        colors = ButtonDefaults.outlinedButtonColors(backgroundColor)
    ) {}
}