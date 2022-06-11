package com.example.jetpackexamplestore.ui.screen.bucket

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Popup
import com.example.jetpackexamplestore.ui.theme.SUCCESS_GREEN

@Composable
fun SuccessOrderPopup() {
    Popup(alignment = Alignment.Center) {
        Box {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row {
                    Icon(
                        imageVector = Icons.Default.CheckCircle,
                        tint = SUCCESS_GREEN,
                        contentDescription = "",
                        modifier = Modifier
                            .size(128.dp)
                    )
                }
                Row {
                    Text(
                        "Order created successfully!",
                        fontSize = 24.sp,
                        textAlign = TextAlign.Center
                    )
                }
            }

        }
    }
}