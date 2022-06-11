package com.example.jetpackexamplestore

import com.example.jetpackexamplestore.backend.FakeBackendImpl
import com.example.jetpackexamplestore.bucket.BucketImpl
import com.example.jetpackexamplestore.cache.FakeCacheImpl
import com.example.jetpackexamplestore.app.App
import com.example.jetpackexamplestore.model.Customer
import com.example.jetpackexamplestore.model.Product
import com.example.jetpackexamplestore.model.Seller
import org.junit.Assert.*
import org.junit.Test

class AppUnitTest {

    @Test
    fun isCustomerProfileExist_returnTrue_when_exist() {
        App.initialize(FakeBackendImpl(true), FakeCacheImpl(), BucketImpl())
        App.isCustomerProfileExist {
            assert(it)
        }
    }

    @Test
    fun isCustomerProfileExist_returnFalse_when_doesNotExist() {
        App.initialize(FakeBackendImpl(false), FakeCacheImpl(), BucketImpl())
        App.isCustomerProfileExist {
            assertFalse(it)
        }
    }

    @Test
    fun createUserProfile_noCrashes_when_running() {
        App.initialize(FakeBackendImpl(true), FakeCacheImpl(), BucketImpl())
        App.createCustomerProfile()
    }

//    @Test
//    fun loadCustomerProfile_callOnResultAndSetCustomer_when_backendIsWorking() {
//        Store.initialize(FakeBackend(true), FakeCache(), BucketImpl())
//        Store.loadCustomerProfile(
//            onSuccess = {
//                assertNull(Store.customer)
//            },
//            onFailure = {
//                assert(false)
//            }
//        )
//    }

    @Test
    fun loadCustomerProfile_callOnResultAndLeaveCustomerAsNull_when_backendIsNotWorking() {
        App.initialize(FakeBackendImpl(false), FakeCacheImpl(), BucketImpl())
        App.loadCustomerProfile(
            onSuccess = {
                assert(false)
            },
            onFailure = {
                assertNull(App.customer)
            }
        )
    }

    @Test
    fun updateCustomerProfile_noCrashes_when_running() {
        App.initialize(FakeBackendImpl(true), FakeCacheImpl(), BucketImpl())
        val customer = Customer("", "", "")
        App.updateCustomerProfile(customer)
    }

    @Test
    fun getProductImage_callOnSuccessWithByteArray_when_backendIsWorking() {
        App.initialize(FakeBackendImpl(true), FakeCacheImpl(), BucketImpl())
        val product = Product(
            "",
            "",
            0f,
            "",
            ""
        )
        val imageName = "fakeImageName"
        App.getProductImage(
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
        App.initialize(FakeBackendImpl(false), FakeCacheImpl(), BucketImpl())
        val product = Product(
            "",
            "",
            0f,
            "",
            ""
        )
        val imageName = "fakeImageName"
        App.getProductImage(
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
        App.initialize(FakeBackendImpl(true), FakeCacheImpl(), BucketImpl())
        val seller = Seller(
            "",
            "",
            "",
            ""
        )
        App.getSellerImage(
            seller.id,
            onSuccess = { _, _ -> },
            onFailure = {
                assert(false)
            }
        )
    }

    @Test
    fun getSellerImage_callOnFailure_when_backendIsNotWorking() {
        App.initialize(FakeBackendImpl(false), FakeCacheImpl(), BucketImpl())
        val seller = Seller(
            "",
            "",
            "",
            ""
        )
        App.getSellerImage(
            seller.id,
            onSuccess = {  _, _ ->
                assert(false)
            },
            onFailure = {}
        )
    }

}