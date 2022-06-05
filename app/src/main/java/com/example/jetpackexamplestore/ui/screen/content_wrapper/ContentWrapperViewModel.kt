package com.example.jetpackexamplestore.ui.screen.content_wrapper

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.jetpackexamplestore.store.Observer
import com.example.jetpackexamplestore.ui.screen.bucket.BucketViewModel

data class ContentWrapperState(
    var bucketProductsCount: MutableState<Int>
)

class ContentWrapperViewModel: ViewModel(), Observer {

    init {
        BucketViewModel.addObserver(this)
    }

    val state = mutableStateOf(ContentWrapperState(mutableStateOf(0)))

//    private val _bucketProductsCount = MutableLiveData(0)
//    val bucketProductsCount: LiveData<Int> = _bucketProductsCount

    override fun update() {
        state.value.bucketProductsCount.value = BucketViewModel.productsWithCount.size
    }
    
}