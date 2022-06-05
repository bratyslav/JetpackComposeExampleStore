package com.example.jetpackexamplestore.ui

import android.graphics.Bitmap
import androidx.lifecycle.MutableLiveData

abstract class DownloadImageTask {

    var isImageLoaded = false
        protected set
    var isFromCache = false
    var image: MutableLiveData<Bitmap?> = MutableLiveData(null)

    abstract fun execute()

}