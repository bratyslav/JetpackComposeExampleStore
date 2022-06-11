package com.example.jetpackexamplestore.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import com.example.jetpackexamplestore.ui.screen.bucket.BucketViewModel
import com.example.jetpackexamplestore.ui.screen.list_of_products.ListOfProductsViewModel
import com.example.jetpackexamplestore.ui.screen.list_of_sellers.ListOfSellersViewModel
import com.example.jetpackexamplestore.ui.screen.profile.ProfileViewModel

@OptIn(
    ExperimentalFoundationApi::class, androidx.compose.ui.unit.ExperimentalUnitApi::class,
    androidx.compose.animation.ExperimentalAnimationApi::class,
    coil.annotation.ExperimentalCoilApi::class
)
object AppUiState {

    private var currentSellerId: String = ""
    private var _listOfProductsViewModel: ListOfProductsViewModel? = null

    val listOfSellersViewModel = ListOfSellersViewModel()

    val bucketViewModel = BucketViewModel()

    fun listOfProductsViewModel(sellerId: String): ListOfProductsViewModel {
        return if (currentSellerId == sellerId) {
            assert(_listOfProductsViewModel != null)
            _listOfProductsViewModel ?: ListOfProductsViewModel(sellerId)
        } else {
            currentSellerId = sellerId
            _listOfProductsViewModel = ListOfProductsViewModel(sellerId)
            _listOfProductsViewModel ?: ListOfProductsViewModel(sellerId)
        }
    }

    fun profileViewModel(mainActivity: MainActivity) = ProfileViewModel(mainActivity)

}