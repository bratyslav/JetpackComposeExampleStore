package com.example.jetpackexamplestore.ui.screen.list_of_products

import android.util.Log
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpackexamplestore.store.entities.Product
import com.example.jetpackexamplestore.ui.ChangeCountButton
import com.example.jetpackexamplestore.ui.DownloadedImageProxy
import com.example.jetpackexamplestore.ui.screen.bucket.BUCKET_FONT_SIZE
import com.example.jetpackexamplestore.ui.screen.bucket.BucketViewModel
import com.example.jetpackexamplestore.ui.theme.BACKGROUND_GRAY

@ExperimentalAnimationApi
@ExperimentalUnitApi
@ExperimentalFoundationApi
@Composable
fun ProductCard(product: Product, viewModel: ListOfProductsViewModel) {
    Log.d("Download recompile product card", "Recompile ${product.name}")
    Row(
        modifier = Modifier
            .padding(4.dp)
            .clip(RoundedCornerShape(24.dp))
            .background(BACKGROUND_GRAY)
            .clickable {
                if (viewModel.isProductPreviewShowing.value == true) {
                    viewModel.hideProductPreview()
                } else {
                    viewModel.showProductPreview(product)
                }
            }
    ) {
        Column(
            modifier = Modifier.padding(4.dp)
        ) {
            DownloadedImageProxy(
                downloadTask = DownloadProductImageTask(product),
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1.0f)
                    .clip(RoundedCornerShape(20.dp))
            )
            Text(
                product.name,
                fontSize = 25.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            ) //, fontFamily = Ubuntu
            Text(
                product.price.toString() + " $",
                fontSize = 14.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                product.description,
                fontSize = 15.sp,
                maxLines = 2,
                modifier = Modifier.height(45.dp),
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.weight(1f))
            if (BucketViewModel.products.contains(product)) {
                ProductCardManageCountButton(product)
            } else {
                ProductCardAddToBucketButton(product)
            }
        }
    }
}

@Composable
fun ProductCardManageCountButton(product: Product) {
    Button(
        content = {
            ChangeCountButton("-") {
                BucketViewModel.decreaseProductCount(product)
            }
            Text(
                BucketViewModel.countOf(product).toString(),
                modifier = Modifier.padding(start = 10.dp, end = 10.dp),
                fontSize = BUCKET_FONT_SIZE
            )
            ChangeCountButton("+") {
                BucketViewModel.increaseProductCount(product)
            }
        },
        modifier = Modifier
            .fillMaxWidth()
            .height(40.dp)
            .clip(RoundedCornerShape(20.dp)),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color.White,
            contentColor = Color.Black
        ),
        onClick = {
            BucketViewModel.increaseProductCount(product)
        }
    )
}

@Composable
fun ProductCardAddToBucketButton(product: Product) {
    Button(
        content = {
            Text(
                "Add to Bucket",
                fontSize = 14.sp
            )
        },
        modifier = Modifier
            .fillMaxWidth()
            .height(40.dp)
            .clip(RoundedCornerShape(20.dp)),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color.White,
            contentColor = Color.Black
        ),
        onClick = { BucketViewModel.addProduct(product) }
    )
}