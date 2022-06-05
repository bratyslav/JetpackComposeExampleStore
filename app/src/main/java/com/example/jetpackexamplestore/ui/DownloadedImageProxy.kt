package com.example.jetpackexamplestore.ui

import android.graphics.Bitmap
import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale

@Composable
fun DownloadedImageProxy(
    downloadTask: DownloadImageTask,
    modifier: Modifier
) {
    downloadTask.execute()

    val image by downloadTask.image.observeAsState(null)
    val animationDuration = if (downloadTask.isFromCache) 0 else (250)

    Crossfade(
        image,
        animationSpec = tween(animationDuration)
    ) {
        it?.let {
            Image(
                bitmap = it.asImageBitmap(),
                modifier = modifier,
                contentDescription = "",
                contentScale = ContentScale.Crop
            )
        } ?: run {
            // placeholder
            Image(
                bitmap = Bitmap.createBitmap(900, 700, Bitmap.Config.ARGB_8888).asImageBitmap(),
                modifier = modifier,
                contentDescription = "",
                contentScale = ContentScale.Crop
            )
        }
    }
}