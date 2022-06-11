package com.example.jetpackexamplestore.ui.screen.list_of_sellers

import android.graphics.BitmapFactory
import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import com.example.jetpackexamplestore.app.App
import com.example.jetpackexamplestore.ui.DownloadImageTask

class DownloadSellerImageTask(private val sellerId: String): DownloadImageTask() {

    @OptIn(
        ExperimentalFoundationApi::class,
        androidx.compose.ui.unit.ExperimentalUnitApi::class,
        androidx.compose.animation.ExperimentalAnimationApi::class,
        coil.annotation.ExperimentalCoilApi::class
    )
    override fun execute() {
        Log.d(this.javaClass.simpleName, "Image download: $sellerId")
        if (!isImageLoaded) {
            isImageLoaded = true
            App.getSellerImage(
                sellerId,
                onSuccess = { byteArray, isFromCache ->
                    image.value = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
                    this.isFromCache = isFromCache
                },
                onFailure = {
                    // TODO: implement
                }
            )
//            Handler().postDelayed({
//                image.value = BitmapFactory.decodeResource(
//                    MainActivity.mainContext!!.resources,
//                    if (Random.nextBoolean()) R.drawable.kitty else R.drawable.dimon
//                )
//            }, 100)
        }
    }

}