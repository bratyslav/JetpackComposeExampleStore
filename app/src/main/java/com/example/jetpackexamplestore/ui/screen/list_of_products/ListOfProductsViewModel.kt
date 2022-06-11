package com.example.jetpackexamplestore.ui.screen.list_of_products

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.jetpackexamplestore.Observer
import com.example.jetpackexamplestore.app.App
import com.example.jetpackexamplestore.model.Product

class ListOfProductsViewModel(sellerId: String): Observer {

    init {
        App.bucket.addObserver(this)
    }

    private val _products = MutableLiveData(emptyList<Product>())
    private val _productsInBucket = mutableStateOf(App.bucket.products.toMap())
    private val _areProductsLoaded = MutableLiveData(false)
    private val _isProductPreviewShowing = MutableLiveData(false)
    val products: LiveData<List<Product>> = _products
    val productsInBucket: State<Map<Product, Int>> = _productsInBucket
    val areProductsLoaded: LiveData<Boolean> = _areProductsLoaded
    val isProductPreviewShowing: LiveData<Boolean> = _isProductPreviewShowing
    var showingProduct: Product? = null
        private set

    init {
        loadProducts(sellerId)
    }

    private fun loadProducts(sellerId: String) {
        App.getSellerProducts(
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

    fun addProduct(product: Product) {
        App.bucket.addProduct(product)
    }

    fun removeProduct(product: Product) {
        App.bucket.removeProduct(product)
    }

    fun countOf(product: Product): Int =
        productsInBucket.value[product] ?: 0

    fun isBucketContains(product: Product): Boolean =
        productsInBucket.value[product] != null

    fun showProductPreview(product: Product) {
        showingProduct = product
        _isProductPreviewShowing.value = true
    }

    fun hideProductPreview() {
        _isProductPreviewShowing.value = false
    }

    override fun update() {
        _productsInBucket.value = App.bucket.products.toMap()
    }

}