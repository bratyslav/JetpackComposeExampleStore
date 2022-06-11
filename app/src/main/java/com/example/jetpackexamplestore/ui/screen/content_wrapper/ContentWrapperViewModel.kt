package com.example.jetpackexamplestore.ui.screen.content_wrapper

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.jetpackexamplestore.Observer
import com.example.jetpackexamplestore.app.App

class ContentWrapperViewModel: ViewModel(), Observer {

    init {
        App.bucket.addObserver(this)
    }

    private val _bucketProductsCount = mutableStateOf(0)
    val bucketProductsCount: State<Int> = _bucketProductsCount

    override fun update() {
        _bucketProductsCount.value = App.bucket.products.size
    }
    
}