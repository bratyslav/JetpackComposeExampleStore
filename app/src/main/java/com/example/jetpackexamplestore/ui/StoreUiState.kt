package com.example.jetpackexamplestore.ui

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.MaterialTheme
import androidx.navigation.compose.rememberNavController
import com.example.jetpackexamplestore.backend.FirebaseBackend
import com.example.jetpackexamplestore.cache.RamCache
import com.example.jetpackexamplestore.store.Store
import com.example.jetpackexamplestore.ui.screen.content_wrapper.ContentWrapperViewModel
import com.example.jetpackexamplestore.ui.screen.list_of_products.ListOfProductsViewModel
import com.example.jetpackexamplestore.ui.screen.list_of_sellers.ListOfSellersViewModel
import com.example.jetpackexamplestore.ui.screen.profile.ProfileViewModel
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult

@OptIn(
    ExperimentalFoundationApi::class, androidx.compose.ui.unit.ExperimentalUnitApi::class,
    androidx.compose.animation.ExperimentalAnimationApi::class,
    coil.annotation.ExperimentalCoilApi::class
)
object StoreUiState {

    private var currentSellerId: String = ""
    private var _listOfProductsViewModel: ListOfProductsViewModel? = null

    val listOfSellersViewModel = ListOfSellersViewModel()

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

//    fun startApp(mainActivity: MainActivity) {
//        Store.initialize(FirebaseBackend(), RamCache())
//
//        // TODO: sign in
//
//        Store.loadSellers(
//            onSuccess = {
//                Store.isCustomerProfileExist { isExist ->
//                    val startDestination =
//                        if (isExist) StoreUiDestinations.LIST_OF_SELLERS else StoreUiDestinations.PROFILE
//
//                    mainActivity.setContent {
//                        val navController = rememberNavController()
//                        MaterialTheme {
//                            StoreUiNavGraph(
//                                mainActivity,
//                                ContentWrapperViewModel(),
//                                navController,
//                                startDestination
//                            )
//                        }
//                    }
//                }
//            },
//            onFailure = { }
//        )
//
//        val providers = arrayListOf(
//            AuthUI.IdpConfig.EmailBuilder().build()
//        )
//
//        val signInIntent = AuthUI.getInstance()
//            .createSignInIntentBuilder()
//            .setAvailableProviders(providers)
//            .build()
//
//        mainActivity.signInLauncher.launch(signInIntent) // continue in onSignInResult()
//    }
//
//    fun onSignInResult(result: FirebaseAuthUIAuthenticationResult) {
//        val response = result.idpResponse ?: run {
//            showMsg("Sign in failed: no response")
//            return
//        }
//        if (result.resultCode == ComponentActivity.RESULT_OK) {
//            Store.isCustomerProfileExist { isExist ->
//                showMsg("Successfully signed in")
//
//                if (isExist) {
//                    onUserExist()
//                } else {
//                    onNewUser()
//                }
//            }
//        } else {
//            showMsg("Sign in failed: ${response.error?.localizedMessage}")
//        }
//    }

}