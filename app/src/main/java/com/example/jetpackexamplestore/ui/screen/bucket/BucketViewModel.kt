package com.example.jetpackexamplestore.ui.screen.bucket

import android.os.Handler
import androidx.compose.runtime.*
import com.example.jetpackexamplestore.roundTo
import com.example.jetpackexamplestore.Observer
import com.example.jetpackexamplestore.app.App
import com.example.jetpackexamplestore.model.Product

class BucketViewModel: Observer {

    init {
        App.bucket.addObserver(this)
    }

    private val _products = mutableStateOf(App.bucket.products.toMap())
    private val _totalPrice = mutableStateOf(App.bucket.getTotalPriceOfAllProducts())
    private val _shouldShowOrderPopup = mutableStateOf(false)
    private val _shouldShowSuccessOrderPopup = mutableStateOf(false)
    val products: State<Map<Product, Int>> = _products
    val totalPrice: State<Float> = _totalPrice
    val shouldShowOrderPopup: State<Boolean> = _shouldShowOrderPopup
    val shouldShowSuccessOrderPopup: State<Boolean> = _shouldShowSuccessOrderPopup

    override fun update() {
        _products.value = App.bucket.products.toMap()
        _totalPrice.value = App.bucket.getTotalPriceOfAllProducts().roundTo(2)
    }

    fun showOrderPopup() {
        _shouldShowSuccessOrderPopup.value = false
        _shouldShowOrderPopup.value = true
    }

    fun showSuccessOrderPopup() {
        _shouldShowOrderPopup.value = false
        _shouldShowSuccessOrderPopup.value = true
        Handler().postDelayed({
            _shouldShowSuccessOrderPopup.value = false
            App.bucket.clear()
        }, 1500)
    }

}