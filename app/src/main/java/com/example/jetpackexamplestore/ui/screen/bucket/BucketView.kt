package com.example.jetpackexamplestore.ui.screen.bucket

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.jetpackexamplestore.app.App
import com.example.jetpackexamplestore.model.Product
import com.example.jetpackexamplestore.ui.ChangeCountButton
import com.example.jetpackexamplestore.ui.screen.content_wrapper.ContentWrapper
import com.example.jetpackexamplestore.ui.screen.content_wrapper.ContentWrapperViewModel
import com.example.jetpackexamplestore.ui.theme.MAIN_BLUE

val BUCKET_FONT_SIZE = 18.sp

@ExperimentalUnitApi
@Composable
fun BucketView(
    navController: NavController,
    viewModel: BucketViewModel,
    contentWrapperViewModel: ContentWrapperViewModel
) {
    val products = remember { viewModel.products }
    val totalPrice = remember { viewModel.totalPrice }
    val shouldShowOrderPopup = remember { mutableStateOf(false) }

    ContentWrapper(navController, contentWrapperViewModel) {
        if (App.bucket.products.isNotEmpty()) {
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(bottom = 10.dp, start = 5.dp, end = 5.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    products.value.forEach {
                        BucketProductCard(it.key, it.value)
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .border((0.5).dp, Color(240, 240, 240), RectangleShape)
                            .padding(25.dp),
                    ) {
                        BucketProductCardCell(weight = 1f) {
                            Text("Total", fontSize = BUCKET_FONT_SIZE)
                        }
                        BucketProductCardCell(alignment = Alignment.End) {
                            Text(
                                totalPrice.value.toString() + " $",
                                fontSize = BUCKET_FONT_SIZE
                            )
                        }
                    }
                }
                OrderButton(shouldShowOrderPopup)
            }
        } else {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(25.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    "Bucket is empty",
                    fontSize = 20.sp
                )
            }
        }
        if (shouldShowOrderPopup.value) {
            OrderPopup(width = 350.dp)
        }
    }
}

@Composable
fun BucketProductCard(product: Product, count: Int) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .border((0.5).dp, Color(240, 240, 240), RectangleShape)
            .padding(25.dp),
    ) {
        BucketProductCardCell(weight = 2f) {
            Text(product.name, fontSize = BUCKET_FONT_SIZE)
        }
        BucketProductCardCell {
            ChangeCountButton("-") {
                // TODO: move to viewModel?
                App.bucket.removeProduct(product)
            }
            Text(
                count.toString(),
                modifier = Modifier.padding(start = 10.dp, end = 10.dp),
                fontSize = BUCKET_FONT_SIZE
            )
            ChangeCountButton("+") {
                // TODO: move to viewModel?
                App.bucket.addProduct(product)
            }
        }
        BucketProductCardCell(alignment = Alignment.End) {
            Text(
                App.bucket.getTotalPriceOf(product).toString() + " $",
                fontSize = BUCKET_FONT_SIZE
            )
        }
    }
}

@Composable
fun RowScope.BucketProductCardCell(
    weight: Float = 1f,
    alignment: Alignment.Horizontal = Alignment.Start,
    content: @Composable() (RowScope.() -> Unit)
) {
    Column(modifier = Modifier.weight(weight)) {
        Row(modifier = Modifier.align(alignment), content = content)
    }
}

@Composable
fun OrderButton(shouldShowOrderPopup: MutableState<Boolean>) {
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .clip(RoundedCornerShape(20.dp)),
        onClick = {
            shouldShowOrderPopup.value = true
        },
        colors = ButtonDefaults.buttonColors(
            backgroundColor = MAIN_BLUE,
            contentColor = Color.White
        ),
    ) {
        Text(
            "Order",
            fontSize = 20.sp
        )
    }
}