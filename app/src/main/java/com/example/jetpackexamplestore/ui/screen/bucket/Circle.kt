package com.example.jetpackexamplestore.ui.screen.bucket

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun Circle(
    size: Dp,
    borderWidth: Dp,
    color: Color,
    backgroundColor: Color,
    content: @Composable RowScope.() -> Unit = {}
) {
    OutlinedButton(
        onClick = {},
        modifier = Modifier.size(size),
        shape = CircleShape,
        border = BorderStroke(borderWidth, color),
        contentPadding = PaddingValues(0.dp),
        colors = ButtonDefaults.outlinedButtonColors(backgroundColor),
        content = content
    )
}