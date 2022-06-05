package com.example.jetpackexamplestore.ui.screen.list_of_sellers

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.jetpackexamplestore.store.Store
import com.example.jetpackexamplestore.store.entities.Seller
import com.example.jetpackexamplestore.ui.screen.bucket.BucketViewModel
import com.example.jetpackexamplestore.ui.screen.list_of_products.ListOfProductsViewModel

class ListOfSellersViewModel {

    val sellers: List<Seller> = Store.sellers ?: emptyList()

//    init {
//        loadSellers()
//    }
//
//    private val _sellers = MutableLiveData(emptyList<Seller>())
//    val sellers: LiveData<List<Seller>> = _sellers
//
//    private fun loadSellers() {
//        // TODO: move to store initialization
//        Store.getAllSellersList({
//            _sellers.value = it
//        }, {})
//    }

}