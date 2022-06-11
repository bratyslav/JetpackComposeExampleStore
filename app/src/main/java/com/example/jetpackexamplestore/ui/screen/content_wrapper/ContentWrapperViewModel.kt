package com.example.jetpackexamplestore.ui.screen.content_wrapper

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.jetpackexamplestore.Observer
import com.example.jetpackexamplestore.app.App

data class ContentWrapperState(
    var bucketProductsCount: MutableState<Int>
)

class ContentWrapperViewModel: ViewModel(), Observer {

    init {
        App.bucket.addObserver(this)
    }

    val state = mutableStateOf(ContentWrapperState(mutableStateOf(0)))

//    private val _bucketProductsCount = MutableLiveData(0)
//    val bucketProductsCount: LiveData<Int> = _bucketProductsCount

    override fun update() {
        state.value.bucketProductsCount.value = App.bucket.products.size
    }
    
}