package com.example.jetpackexamplestore.ui.screen.list_of_products

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.jetpackexamplestore.ui.screen.content_wrapper.ContentWrapper
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.*
import androidx.compose.ui.text.style.TextAlign
import com.example.jetpackexamplestore.ui.DownloadedImageProxy
import com.example.jetpackexamplestore.ui.screen.content_wrapper.ContentWrapperViewModel
import com.example.jetpackexamplestore.ui.screen.list_of_sellers.DownloadSellerImageTask

@ExperimentalAnimationApi
@ExperimentalFoundationApi
@ExperimentalUnitApi
@Composable
fun ListOfProductsView(
    navController: NavController,
    sellerId: String,
    companyName: String,
    contentWrapperViewModel: ContentWrapperViewModel,
    viewModel: ListOfProductsViewModel
) {
    val products by viewModel.products.observeAsState(emptyList())
    val areProductsLoaded by viewModel.areProductsLoaded.observeAsState(false)
    val isProductPreviewShowing by viewModel.isProductPreviewShowing.observeAsState(false)

    ContentWrapper(navController, contentWrapperViewModel) {
        Column {
            ListOfProductsHeader(sellerId, companyName)
            Row(
                modifier = Modifier
                    .clickable {
                        viewModel.hideProductPreview()
                    }
            ) {
                if (areProductsLoaded) {
                    if (products.isNotEmpty()) {
                        LazyVerticalGrid(
                            cells = GridCells.Fixed(2),
                            modifier = Modifier
                                .padding(start = 4.dp, end = 4.dp)
                                .fillMaxSize()
                        ) {
                            items(products) { product ->
                                ProductCard(product, viewModel)
                            }
                        }
                    } else {
                        NoProductsMessage()
                    }
                }
            }
        }
    }
    ProductPreviewWrapper(visible = isProductPreviewShowing, viewModel = viewModel)
}

@Composable
fun ListOfProductsHeader(sellerId: String, companyName: String) {
    Card(
        shape = RectangleShape
    ) {
        DownloadedImageProxy(
            downloadTask = DownloadSellerImageTask(sellerId),
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
        )
        Column(
            modifier = Modifier.height(100.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                companyName,
                fontSize = 50.sp,
                color = Color.White,
                modifier = Modifier
                    .padding(start = 10.dp),
                style = LocalTextStyle.current.copy(
                    shadow = Shadow(
                        color = Color.DarkGray,
                        offset = Offset(0f, 0f),
                        blurRadius = 5f,
                    )
                )
            )
        }
    }
}

@Composable
fun NoProductsMessage() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Sorry, this seller has no products available :(",
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.h4
        )
    }
}