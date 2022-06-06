package com.example.jetpackexamplestore.ui

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import coil.annotation.ExperimentalCoilApi
import com.example.jetpackexamplestore.ui.screen.bucket.BucketView
import com.example.jetpackexamplestore.ui.screen.content_wrapper.ContentWrapperViewModel
import com.example.jetpackexamplestore.ui.screen.list_of_products.ListOfProductsView
import com.example.jetpackexamplestore.ui.screen.list_of_sellers.ListOfSellersView
import com.example.jetpackexamplestore.ui.screen.profile.ProfileView

@ExperimentalFoundationApi
@ExperimentalUnitApi
@ExperimentalCoilApi
@ExperimentalAnimationApi
@Composable
fun StoreUiNavGraph(
    mainActivity: MainActivity,
    contentWrapperViewModel: ContentWrapperViewModel,
    navController: NavHostController,
    startDestination: String = StoreUiDestinations.LIST_OF_SELLERS,
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(StoreUiDestinations.LIST_OF_SELLERS) {
            ListOfSellersView(
                navController,
                StoreUiState.listOfSellersViewModel,
                contentWrapperViewModel
            )
        }
        composable("${StoreUiDestinations.LIST_OF_PRODUCTS}/{sellerId}/{companyName}") { backStackEntry ->
            val sellerId = backStackEntry.arguments?.getString("sellerId") ?: run {
                // TODO: handle it
                return@composable
            }
            val companyName = backStackEntry.arguments?.getString("companyName") ?: run {
                // TODO: handle it
                return@composable
            }
            ListOfProductsView(
                navController,
                sellerId,
                companyName,
                contentWrapperViewModel,
                StoreUiState.listOfProductsViewModel(sellerId)
            )
        }
        composable(StoreUiDestinations.PROFILE) {
            ProfileView(
                navController,
                contentWrapperViewModel,
                StoreUiState.profileViewModel(mainActivity).loadProfile()
            )
        }
        composable(StoreUiDestinations.BUCKET) {
            BucketView(navController, StoreUiState.bucketViewModel, contentWrapperViewModel)
        }
    }
}