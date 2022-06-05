package com.example.jetpackexamplestore.store

import com.example.jetpackexamplestore.store.entities.*

interface Backend {

    fun downloadCustomerProfile(
        onSuccess: (Customer) -> Unit,
        onFailure: () -> Unit
    )

    fun updateCustomerProfile(
        customer: Customer,
        onSuccess: () -> Unit,
        onFailure: () -> Unit
    )

    fun createOrder(
        order: Order,
        onSuccess: () -> Unit,
        onFailure: () -> Unit
    )

    fun getSellerProducts(
        sellerId: String,
        onSuccess: (List<Product>) -> Unit,
        onFailure: () -> Unit
    )

    fun downloadImage(relativeUrl: String, onSuccess: (ByteArray) -> Unit, onFailure: () -> Unit)

    fun getAllSellersList(onSuccess: (List<Seller>) -> Unit, onFailure: () -> Unit)

}