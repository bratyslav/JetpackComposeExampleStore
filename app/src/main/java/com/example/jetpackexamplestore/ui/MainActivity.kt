package com.example.jetpackexamplestore.ui

import android.os.Bundle
import android.os.Handler
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.navigation.compose.rememberNavController
import coil.annotation.ExperimentalCoilApi
import com.example.jetpackexamplestore.backend.FirebaseBackend
import com.example.jetpackexamplestore.bucket.BucketImpl
import com.example.jetpackexamplestore.cache.RamCache
import com.example.jetpackexamplestore.store.Store
import com.example.jetpackexamplestore.ui.screen.content_wrapper.ContentWrapperViewModel
import com.example.jetpackexamplestore.ui.theme.JetpackExampleStoreTheme
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult

@ExperimentalFoundationApi
@ExperimentalUnitApi
@ExperimentalCoilApi
@ExperimentalAnimationApi
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        hideSystemUI()
        setStatusMsg("Signing in...")
        Store.initialize(FirebaseBackend(), RamCache(), BucketImpl())
        startSignInActivity()
    }

    private val signInLauncher =
        registerForActivityResult(FirebaseAuthUIActivityResultContract()) { res ->
            onSignInResult(res)
        }

    private fun startSignInActivity() {
        // Choose authentication providers
        val providers = arrayListOf(
            AuthUI.IdpConfig.EmailBuilder().build()
        )

        // Create and launch sign-in intent
        val signInIntent = AuthUI.getInstance()
            .createSignInIntentBuilder()
            .setAvailableProviders(providers)
            .build()
        signInLauncher.launch(signInIntent)
    }

    private fun onSignInResult(result: FirebaseAuthUIAuthenticationResult) {
        val response = result.idpResponse ?: run {
            setStatusMsg("Sign in failed: no response")
            return
        }
        if (result.resultCode == RESULT_OK) {
            Store.isCustomerProfileExist { isExist ->
                setStatusMsg("Successfully signed in")

                if (isExist) {
                    onUserExist()
                } else {
                    onNewUser()
                }
            }
        } else {
            setStatusMsg("Sign in failed: ${response.error?.localizedMessage}")
        }
    }

    private fun onNewUser() {
        Store.createCustomerProfile()
        startApp(isFirstStart = true)
    }

    private fun onUserExist() {
        // Load profile, show greetings and start app
        Store.loadCustomerProfile(
            onSuccess = {
                val user = Store.customer ?: run {
                    startApp(isFirstStart = false)
                    return@loadCustomerProfile
                }

                setStatusMsg("Hello, ${user.name}!")
                Handler().postDelayed({ startApp(isFirstStart = false) }, GREETINGS_DELAY_MS)
            },
            onFailure = {
                // TODO: implement
            }
        )
    }

    private fun startApp(isFirstStart: Boolean) {
        Store.loadSellers(
            onSuccess = {
                setContent {
                    val navController = rememberNavController()
                    MaterialTheme {
                        StoreUiNavGraph(
                            this,
                            ContentWrapperViewModel(),
                            navController,
                            startDestination = if (isFirstStart)
                                StoreUiDestinations.PROFILE
                            else
                                StoreUiDestinations.LIST_OF_SELLERS
                        )
                    }
                }
            },
            onFailure = {
                // TODO: implement
            }
        )
    }

    private fun hideSystemUI() {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        WindowInsetsControllerCompat(window, window.decorView).let { controller ->
            controller.hide(WindowInsetsCompat.Type.systemBars())
            controller.systemBarsBehavior =
                WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        }
    }

    private fun setStatusMsg(msg: String) {
        setContent {
            JetpackExampleStoreTheme {
                Surface(color = Color.White) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(text = msg, style = MaterialTheme.typography.h4)
                    }
                }
            }
        }
    }

    companion object {
        const val GREETINGS_DELAY_MS = 1500L
    }

}