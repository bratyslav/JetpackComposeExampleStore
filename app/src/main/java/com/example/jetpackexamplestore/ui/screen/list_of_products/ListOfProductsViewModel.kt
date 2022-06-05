package com.example.jetpackexamplestore.ui.screen.list_of_products

import androidx.compose.ui.platform.LocalConfiguration
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.jetpackexamplestore.store.Observer
import com.example.jetpackexamplestore.store.Store
import com.example.jetpackexamplestore.store.entities.Product
import com.example.jetpackexamplestore.ui.screen.bucket.BucketViewModel
import com.example.jetpackexamplestore.ui.screen.content_wrapper.ContentWrapperViewModel

class ListOfProductsViewModel(sellerId: String) {

    private val _products = MutableLiveData(emptyList<Product>())
    private val _areProductsLoaded = MutableLiveData(false)
    private val _isProductPreviewShowing = MutableLiveData(false)
    val products: LiveData<List<Product>> = _products
    val areProductsLoaded: LiveData<Boolean> = _areProductsLoaded
    val isProductPreviewShowing: LiveData<Boolean> = _isProductPreviewShowing
    var showingProduct: Product? = null
        private set

    init {
        loadProducts(sellerId)
    }

    private fun loadProducts(sellerId: String) {
        Store.getSellerProducts(
            sellerId,
            onSuccess = {
                _products.value = it
                _areProductsLoaded.value = true
            },
            onFailure = {
                // TODO: implement
            }
        )
    }

    fun showProductPreview(product: Product) {
        showingProduct = product
        _isProductPreviewShowing.value = true
    }

    fun hideProductPreview() {
        _isProductPreviewShowing.value = false
    }

}