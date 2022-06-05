package com.example.jetpackexamplestore.ui.screen.bucket

import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.jetpackexamplestore.roundTo
import com.example.jetpackexamplestore.store.Observable
import com.example.jetpackexamplestore.store.Observer
import com.example.jetpackexamplestore.store.entities.Product

data class BucketState(
    // TODO: is this needed?
    val productsWithCount: SnapshotStateList<ProductWithCount>
)

object BucketViewModel: ViewModel(), Observable {

    override val observers: ArrayList<Observer> = arrayListOf()

    var state by mutableStateOf(BucketState(mutableStateListOf()))
    private val _shouldShowOrderPopup = MutableLiveData(false)
    val shouldShowOrderPopup: LiveData<Boolean> = _shouldShowOrderPopup

    val productsWithCount: List<ProductWithCount>
        get() = state.productsWithCount

    val products: List<Product>
        get() = productsWithCount.map { productWithCount -> productWithCount.product }

    fun addProduct(product: Product) {
        // do nothing, if that product is already in bucket,
        // otherwise put it in bucket with count 1
        state.productsWithCount
            .find {
                productWithCount -> productWithCount.product == product
            } ?.let {} ?: run {
                state.productsWithCount.add(ProductWithCount(product, 1))
                sendUpdateEvent()
            }
    }

    fun increaseProductCount(product: Product) {
        // increase count of product, if that product is already in bucket
        state.productsWithCount
            .find {
                productWithCount -> productWithCount.product == product
            } ?.let { productWithCount ->
                state.productsWithCount.set(
                    state.productsWithCount.indexOf(productWithCount),
                    productWithCount.copy(count = productWithCount.count + 1)
                )
            }
    }

    fun decreaseProductCount(product: Product) {
        // decrease count of product, if that product is already in bucket,
        // if count is already 1, delete the product from bucket
        state.productsWithCount
            .find {
                productWithCount -> productWithCount.product == product
            } ?.let { productWithCount ->
                if (productWithCount.count == 1) {
                    state.productsWithCount.remove(productWithCount)
                    sendUpdateEvent()
                } else {
                    state.productsWithCount.set(
                        state.productsWithCount.indexOf(productWithCount),
                        productWithCount.copy(count = productWithCount.count - 1)
                    )
                }
            }
    }

    fun countOf(product: Product): Int {
        state.productsWithCount
            .find {
                productWithCount -> productWithCount.product == product
            } ?.let {
                return@countOf it.count
            } ?: run {
                return@countOf 0
            }
    }

    val totalPrice: Float
        get() = productsWithCount.fold(0f) {
            acc, value -> acc + value.totalPrice
        } .roundTo(2)

    val totalCount: Int
        get() = productsWithCount.fold(0) {
            acc, value -> acc + value.count
        }

    fun createOrder() {
        _shouldShowOrderPopup.value = true
    }

}