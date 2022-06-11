package com.example.jetpackexamplestore.ui.screen.bucket

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Popup

@Composable
fun OrderPopup(width: Dp, viewModel: BucketViewModel) {
    Popup(alignment = Alignment.Center) {
        Box {
            Column(
                Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color.DarkGray)
            ) {
                OrderPopupOption("Payment by card", width, viewModel)
                OrderPopupOption("Cash on delivery", width, viewModel)
            }
        }
    }
}

@Composable
fun OrderPopupOption(option: String, width: Dp, viewModel: BucketViewModel) {
    Row(
        Modifier
            .width(width)
            .border(1.dp, Color.White)
            .padding(32.dp)
            .clickable { viewModel.showSuccessOrderPopup() },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Circle(
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