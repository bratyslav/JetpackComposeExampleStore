package com.example.jetpackexamplestore.store

import com.example.jetpackexamplestore.store.entities.*

object Store {

    private lateinit var cache: Cache
    private lateinit var backend: Backend

    var customer: Customer? = null
        private set

    var sellers: List<Seller>? = null
        private set

    fun initialize(backend: Backend, cache: Cache) {
        this.backend = backend
        this.cache = cache
    }

    fun getSellerProducts(
        sellerId: String,
        onSuccess: (List<Product>) -> Unit,
        onFailure: () -> Unit
    ) {
        backend.getSellerProducts(sellerId, onSuccess, onFailure)
    }

    fun isCustomerProfileExist(onResponse: (isExisting: Boolean) -> Unit) {
        backend.downloadCustomerProfile(
            onSuccess = { onResponse(true) },
            onFailure = { onResponse(false) }
        )
    }

    fun createCustomerProfile() {
        val customer = Customer("no_name", "no_surname", "0")
        updateCustomerProfile(customer)
    }

    fun loadSellers(onSuccess: () -> Unit, onFailure: () -> Unit) {
        backend.getAllSellersList(
            onSuccess = {
                sellers = it
                onSuccess()
            },
            onFailure = onFailure
        )
    }

    fun loadCustomerProfile(onSuccess: () -> Unit = {}, onFailure: () -> Unit) {
        backend.downloadCustomerProfile(
            onSuccess = { customer ->
                this.customer = customer
                onSuccess()
            },
            onFailure = onFailure
        )
    }

    fun updateCustomerProfile(customer: Customer) {
        backend.updateCustomerProfile(
            customer,
            onSuccess = { /* TODO: implement */ },
            onFailure = { /* TODO: implement */ }
        )
    }

    fun getProductImage(
        product: Product,
        imageName: String,
        onSuccess: (image: ByteArray, isFromCache: Boolean) -> Unit,
        onFailure: () -> Unit
    ) {
        val imageUrl = "${product.ownerId}/${product.id}/$imageName"
        // get it from the cache or if the cache return a null, download it and cache it
        cache.get(imageUrl)?.let {
            onSuccess(it as ByteArray, true)
        } ?: run {
            backend.downloadImage(
                imageUrl,
                onSuccess = { byteArray ->
                    cache.save(key = imageUrl, obj = byteArray)
                    onSuccess(byteArray, false)
                },
                onFailure
            )
        }
    }

    fun getSellerImage(
        sellerId: String,
        onSuccess: (image: ByteArray, isFromCache: Boolean) -> Unit,
        onFailure: () -> Unit
    ) {
        val imageUrl = "${sellerId}/main.jpg"
        // get it from the cache or if the cache return a null, download it and cache it
        cache.get(imageUrl)?.let {
            onSuccess(it as ByteArray, true)
        } ?: run {
            backend.downloadImage(
                imageUrl,
                onSuccess = { byteArray ->
                    cache.save(key = imageUrl, obj = byteArray)
                    onSuccess(byteArray, false)
                },
                onFailure
            )
        }
    }

}