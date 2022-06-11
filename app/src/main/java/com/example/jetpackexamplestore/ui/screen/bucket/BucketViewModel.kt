package com.example.jetpackexamplestore.ui.screen.bucket

import androidx.compose.runtime.*
import com.example.jetpackexamplestore.roundTo
import com.example.jetpackexamplestore.Observer
import com.example.jetpackexamplestore.app.App
import com.example.jetpackexamplestore.model.Product

class BucketViewModel: Observer {

    init {
        App.bucket.addObserver(this)
    }

    val _products = mutableStateOf(App.bucket.products.toMap())
    val _totalPrice = mutableStateOf(App.bucket.getTotalPriceOfAllProducts())
    val products: State<Map<Product, Int>> = _products
    val totalPrice: State<Float> = _totalPrice

    override fun update() {
        _products.value = App.bucket.products.toMap()
        _totalPrice.value = App.bucket.getTotalPriceOfAllProducts().roundTo(2)
    }

}