package com.example.jetpackexamplestore.bucket

import com.example.jetpackexamplestore.store.Bucket
import com.example.jetpackexamplestore.store.Observer
import com.example.jetpackexamplestore.store.entities.Product

class BucketImpl : Bucket {

    override val observers: ArrayList<Observer> = arrayListOf()
    private val _products: MutableMap<Product, Int> = mutableMapOf()

    override val products: Map<Product, Int>
        get() = _products

    override fun addProduct(product: Product) {
        _products[product] = _products[product]?.plus(1) ?: 1
        sendUpdateEvent()
    }

    override fun removeProduct(product: Product) {
        _products[product]?.let { count ->
            if (count == 1) {
                _products.remove(product)
            } else {
                _products[product] = count - 1
            }
        }
        sendUpdateEvent()
    }

    override fun countOf(product: Product): Int =
        _products[product] ?: 0

    override fun getTotalPriceOf(product: Product): Float {
        _products[product]?.let { count ->
            return product.price * count
        }
        return 0f
    }

    override fun getTotalPriceOfAllProducts(): Float {
        var sum = 0f
        _products.forEach { entry ->
            sum += entry.key.price * entry.value
        }
        return sum
    }

}