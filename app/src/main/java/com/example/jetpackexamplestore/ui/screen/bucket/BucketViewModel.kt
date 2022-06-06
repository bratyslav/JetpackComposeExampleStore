package com.example.jetpackexamplestore.ui.screen.bucket

import androidx.compose.runtime.*
import com.example.jetpackexamplestore.roundTo
import com.example.jetpackexamplestore.store.Observer
import com.example.jetpackexamplestore.store.Store
import com.example.jetpackexamplestore.store.entities.Product

class BucketViewModel: Observer {

    init {
        Store.bucket.addObserver(this)
    }

    val _products = mutableStateOf(Store.bucket.products.toMap())
    val _totalPrice = mutableStateOf(Store.bucket.getTotalPriceOfAllProducts())
    val products: State<Map<Product, Int>> = _products
    val totalPrice: State<Float> = _totalPrice

    override fun update() {
        _products.value = Store.bucket.products.toMap()
        _totalPrice.value = Store.bucket.getTotalPriceOfAllProducts().roundTo(2)
    }

}