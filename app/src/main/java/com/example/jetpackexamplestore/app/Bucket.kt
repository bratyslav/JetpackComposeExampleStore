package com.example.jetpackexamplestore.app

import com.example.jetpackexamplestore.Observable
import com.example.jetpackexamplestore.model.Product

interface Bucket: Observable {

    val products: Map<Product, Int>

    fun addProduct(product: Product)

    fun removeProduct(product: Product)

    fun countOf(product: Product): Int

    fun getTotalPriceOf(product: Product): Float

    fun getTotalPriceOfAllProducts(): Float

}