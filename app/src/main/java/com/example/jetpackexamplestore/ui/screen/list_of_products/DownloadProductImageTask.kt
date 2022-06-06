package com.example.jetpackexamplestore.ui.screen.list_of_products

import android.graphics.BitmapFactory
import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import com.example.jetpackexamplestore.store.Store
import com.example.jetpackexamplestore.store.entities.Product
import com.example.jetpackexamplestore.ui.DownloadImageTask

class DownloadProductImageTask(private val product: Product): DownloadImageTask() {

    @OptIn(
        ExperimentalFoundationApi::class, androidx.compose.ui.unit.ExperimentalUnitApi::class,
        androidx.compose.animation.ExperimentalAnimationApi::class,
        coil.annotation.ExperimentalCoilApi::class
    )
    override fun execute() {
        Log.d(this.javaClass.simpleName, "Image download: ${product.name}")
        if (!isImageLoaded) {
            isImageLoaded = true
            Store.getProductImage(
                product,
                product.imagesNames[0],
                onSuccess = { byteArray, isFromCache ->
                    image.value = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
                    this.isFromCache = isFromCache
                },
                onFailure = {
                    // TODO: implement
                }
            )
        }
    }

}