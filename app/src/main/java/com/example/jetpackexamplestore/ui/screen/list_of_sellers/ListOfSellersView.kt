package com.example.jetpackexamplestore.ui.screen.list_of_sellers

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.jetpackexamplestore.model.Seller
import com.example.jetpackexamplestore.ui.screen.content_wrapper.ContentWrapper
import androidx.compose.ui.unit.sp
import com.example.jetpackexamplestore.ui.DownloadedImageProxy
import com.example.jetpackexamplestore.ui.AppUiDestinations
import com.example.jetpackexamplestore.ui.screen.content_wrapper.ContentWrapperViewModel

@ExperimentalAnimationApi
@ExperimentalFoundationApi
@ExperimentalUnitApi
@Composable
fun ListOfSellersView(
    navController: NavController,
    viewModel: ListOfSellersViewModel,
    contentWrapperViewModel: ContentWrapperViewModel
) {
//    val sellers by viewModel.sellers.observeAsState(emptyList())

    ContentWrapper(navController, contentWrapperViewModel) {
        LazyColumn(
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(viewModel.sellers) { seller ->
                SellerCard(seller, navController)
            }
        }
    }
}

@ExperimentalAnimationApi
@ExperimentalUnitApi
@ExperimentalFoundationApi
@Composable
fun SellerCard(seller: Seller, navController: NavController) {

    Row {
        Column(
            modifier = Modifier
                .padding(bottom = 24.dp)
                .clickable {
                    navController.navigate(
                        "${AppUiDestinations.LIST_OF_PRODUCTS}/${seller.id}/${seller.company}"
                    )
                }
        ) {
            Row(
                modifier = Modifier.padding(bottom = 8.dp)
            ) {
                DownloadedImageProxy(
                    DownloadSellerImageTask(seller.id),
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1.5f)
                        .clip(RoundedCornerShape(20.dp))
                )
            }
            Row(
                modifier = Modifier.padding(start = 4.dp)
            ) {
                Text(
                    seller.company,
                    fontSize = 25.sp
                )
            }
        }

    }
}