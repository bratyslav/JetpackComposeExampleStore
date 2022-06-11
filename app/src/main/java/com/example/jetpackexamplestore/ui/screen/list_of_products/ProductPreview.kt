package com.example.jetpackexamplestore.ui.screen.list_of_products

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.example.jetpackexamplestore.model.Product
import com.example.jetpackexamplestore.ui.ChangeCountButton
import com.example.jetpackexamplestore.ui.DownloadedImageProxy
import com.example.jetpackexamplestore.ui.theme.MAIN_BLUE

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ProductPreviewWrapper(visible: Boolean, viewModel: ListOfProductsViewModel) {
    val previewHeight = LocalConfiguration.current.screenHeightDp * 0.7
    AnimatedVisibility(
        visible = visible,
        enter = slideInVertically(initialOffsetY = { 600 }),
        exit = slideOutVertically(targetOffsetY = { 1800 })
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .wrapContentSize(align = Alignment.BottomCenter)
                .clip(RoundedCornerShape(20.dp))
        ) {
            ProductPreviewCloseButton { viewModel.hideProductPreview() }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(previewHeight.dp)
                    .verticalScroll(rememberScrollState())
                    .background(color = Color.White)
            ) {
                ProductPreview(viewModel)
            }
        }
    }
}

@Composable
fun ProductPreview(viewModel: ListOfProductsViewModel) {
    val product = viewModel.showingProduct!!

    DownloadedImageProxy(
        downloadTask = DownloadProductImageTask(product),
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1.0f)
    )
    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        Text(
            product.name,
            fontSize = 40.sp
        )
        Text(
            "${product.price} $",
            fontSize = 20.sp
        )
        Text(
            product.description,
            fontSize = 20.sp
        )
        if (viewModel.isBucketContains(product)) {
            ProductPreviewManageCountButton(product, viewModel)
        } else {
            ProductPreviewAddToBucketButton(product, viewModel)
        }
    }
}

@Composable
fun ProductPreviewCloseButton(onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentSize(align = Alignment.CenterEnd)
            .offset(y = 50.dp)
            .zIndex(999f)
            .padding(end = 16.dp)
    ) {
        IconButton(
            modifier = Modifier
                .size(30.dp)
                .background(
                    color = Color.LightGray.copy(alpha = 0.5f),
                    shape = CircleShape
                ),
            onClick = onClick
        ) {
            Icon(
                imageVector = Icons.Default.Close,
                contentDescription = "",
                tint = Color.White,
                modifier = Modifier
                    .size(25.dp)
            )
        }
    }
}

@Composable
fun ProductPreviewManageCountButton(product: Product, viewModel: ListOfProductsViewModel) {
    Button(
        content = {
            ChangeCountButton("-", size = 35.dp) {
                viewModel.removeProduct(product)
            }
            Text(
                viewModel.countOf(product).toString(),
                modifier = Modifier.padding(start = 10.dp, end = 10.dp),
                fontSize = 24.sp
            )
            ChangeCountButton("+", size = 35.dp) {
                viewModel.addProduct(product)
            }
        },
        modifier = Modifier
            .padding(top = 16.dp)
            .fillMaxWidth()
            .height(60.dp)
            .clip(RoundedCornerShape(30.dp)),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color.White,
            contentColor = Color.Black
        ),
        onClick = { viewModel.addProduct(product) }
    )
}

@Composable
fun ProductPreviewAddToBucketButton(product: Product, viewModel: ListOfProductsViewModel) {
    Button(
        content = {
            Text(
                "Add to Bucket",
                fontSize = 24.sp
            )
        },
        modifier = Modifier
            .padding(top = 16.dp)
            .fillMaxWidth()
            .height(60.dp)
            .clip(RoundedCornerShape(30.dp)),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = MAIN_BLUE,
            contentColor = Color.White
        ),
        onClick = { viewModel.addProduct(product) }
    )
}