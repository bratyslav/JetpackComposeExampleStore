package com.example.jetpackexamplestore.backend

import com.example.jetpackexamplestore.store.Backend
import com.example.jetpackexamplestore.store.entities.Customer
import com.example.jetpackexamplestore.store.entities.Order
import com.example.jetpackexamplestore.store.entities.Product
import com.example.jetpackexamplestore.store.entities.Seller

// Fake Backend implementation just for test purposes
class FakeBackend(private val isSuccessful: Boolean): Backend {

    override fun downloadCustomerProfile(onSuccess: (Customer) -> Unit, onFailure: () -> Unit) {
        if (isSuccessful) {
            onSuccess(
                Customer(
                    "FakeName",
                    "FakeSurname",
                    "31415926"
                )
            )
        } else {
            onFailure()
        }
    }

    override fun updateCustomerProfile(
        customer: Customer,
        onSuccess: () -> Unit,
        onFailure: () -> Unit
    ) {}

    override fun createOrder(order: Order, onSuccess: () -> Unit, onFailure: () -> Unit) {}

    override fun getSellerProducts(
        sellerId: String,
        onSuccess: (List<Product>) -> Unit,
        onFailure: () -> Unit
    ) {
        if (isSuccessful) {
            onSuccess(
                listOf(
                    Product(
                        "FakeProduct1",
                        "Fake description 1",
                        1f,
                        "1",
                        "1",
                        mutableListOf("fakeImageName1")
                    ),
                    Product(
                        "FakeProduct12",
                        "Fake description 2",
                        2f,
                        "2",
                        "2",
                        mutableListOf("fakeImageName2")
                    ),
                    Product(
                        "FakeProduct3",
                        "Fake description 3",
                        3f,
                        "3",
                        "3",
                        mutableListOf("fakeImageName3")
                    )
                )
            )
        }
    }

    override fun downloadImage(
        relativeUrl: String,
        onSuccess: (ByteArray) -> Unit,
        onFailure: () -> Unit
    ) {
        if (isSuccessful) {
            onSuccess(ByteArray(1))
        } else {
            onFailure()
        }
    }

    override fun getAllSellersList(onSuccess: (List<Seller>) -> Unit, onFailure: () -> Unit) {
        if (isSuccessful) {
            onSuccess(
                listOf(
                    Seller(
                        "FakeName1",
                        "FakeSurname1",
                        "314159261",
                        "FakeCompany1"
                    ),
                    Seller(
                        "FakeName2",
                        "FakeSurname2",
                        "314159262",
                        "FakeCompany2"
                    )
                )
            )
        }
    }

}