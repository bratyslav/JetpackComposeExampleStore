package com.example.jetpackexamplestore

import com.example.jetpackexamplestore.backend.FakeBackend
import com.example.jetpackexamplestore.cache.FakeCache
import com.example.jetpackexamplestore.store.Store
import com.example.jetpackexamplestore.store.entities.Customer
import com.example.jetpackexamplestore.store.entities.Product
import com.example.jetpackexamplestore.store.entities.Seller
import org.junit.Assert.*
import org.junit.Test

class StoreUnitTest {

    @Test
    fun isCustomerProfileExist_returnTrue_when_exist() {
        Store.initialize(FakeBackend(true), FakeCache())
        Store.isCustomerProfileExist {
            assert(it)
        }
    }

    @Test
    fun isCustomerProfileExist_returnFalse_when_doesNotExist() {
        Store.initialize(FakeBackend(false), FakeCache())
        Store.isCustomerProfileExist {
            assertFalse(it)
        }
    }

    @Test
    fun createUserProfile_noCrashes_when_running() {
        Store.initialize(FakeBackend(true), FakeCache())
        Store.createCustomerProfile()
    }

    @Test
    fun loadCustomerProfile_callOnResultAndSetCustomer_when_backendIsWorking() {
        Store.initialize(FakeBackend(true), FakeCache())
        Store.loadCustomerProfile(
            onSuccess = {
                assertNull(Store.customer)
            },
            onFailure = {
                assert(false)
            }
        )
    }

    @Test
    fun loadCustomerProfile_callOnResultAndLeaveCustomerAsNull_when_backendIsNotWorking() {
        Store.initialize(FakeBackend(false), FakeCache())
        Store.loadCustomerProfile(
            onSuccess = {
                assert(false)
            },
            onFailure = {
                assertNull(Store.customer)
            }
        )
    }

    @Test
    fun updateCustomerProfile_noCrashes_when_running() {
        Store.initialize(FakeBackend(true), FakeCache())
        val customer = Customer("", "", "")
        Store.updateCustomerProfile(customer)
    }

    @Test
    fun getProductImage_callOnSuccessWithByteArray_when_backendIsWorking() {
        Store.initialize(FakeBackend(true), FakeCache())
        val product = Product(
            "",
            "",
            0f,
            "",
            ""
        )
        val imageName = "fakeImageName"
        Store.getProductImage(
            product,
            imageName,
            onSuccess = { _, _ -> },
            onFailure = {
                assert(false)
            }
        )
    }

    @Test
    fun getProductImage_callOnFailure_when_backendIsNotWorking() {
        Store.initialize(FakeBackend(false), FakeCache())
        val product = Product(
            "",
            "",
            0f,
            "",
            ""
        )
        val imageName = "fakeImageName"
        Store.getProductImage(
            product,
            imageName,
            onSuccess = {  _, _ ->
                assert(false)
            },
            onFailure = {}
        )
    }

    @Test
    fun getSellerImage_callOnSuccessWithByteArray_when_backendIsWorking() {
        Store.initialize(FakeBackend(true), FakeCache())
        val seller = Seller(
            "",
            "",
            "",
            ""
        )
        Store.getSellerImage(
            seller.id,
            onSuccess = { _, _ -> },
            onFailure = {
                assert(false)
            }
        )
    }

    @Test
    fun getSellerImage_callOnFailure_when_backendIsNotWorking() {
        Store.initialize(FakeBackend(false), FakeCache())
        val seller = Seller(
            "",
            "",
            "",
            ""
        )
        Store.getSellerImage(
            seller.id,
            onSuccess = {  _, _ ->
                assert(false)
            },
            onFailure = {}
        )
    }

}