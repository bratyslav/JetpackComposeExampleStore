package com.example.jetpackexamplestore.store

import com.example.jetpackexamplestore.store.entities.Product

interface Bucket: Observable {

    val products: Map<Product, Int>

    fun addProduct(product: Product)

    fun removeProduct(product: Product)

    fun countOf(product: Product): Int

    fun getTotalPriceOf(product: Product): Float

    fun getTotalPriceOfAllProducts(): Float

}