package com.example.jetpackexamplestore.ui.screen.content_wrapper

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.jetpackexamplestore.ui.StoreUiDestinations
import com.example.jetpackexamplestore.ui.theme.MAIN_BLUE

@ExperimentalUnitApi
@Composable
fun ContentWrapper(
    navController: NavController,
    viewModel: ContentWrapperViewModel,
    content: @Composable () -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            TopAppBar(
                modifier = Modifier.background(Color.White),
                backgroundColor = Color.White
            ) {
                NavBar(navController, viewModel)
            }
            Row(modifier = Modifier.fillMaxSize()) {
                content()
            }
        }
    }
}

@ExperimentalUnitApi
@Composable
fun NavBar(
    navController: NavController,
    viewModel: ContentWrapperViewModel,
) {
    val currentRoute = navController.currentBackStackEntry?.destination?.route
        ?: StoreUiDestinations.LIST_OF_SELLERS
    val state = remember { viewModel.state }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.White),
        horizontalArrangement = Arrangement.SpaceAround,
    ) {
        NavBarButton(
            content = {
                Icon(
                    imageVector = Icons.Default.List,
                    contentDescription = "",
                    modifier = Modifier.size(35.dp)
                )
            },
            route = StoreUiDestinations.LIST_OF_SELLERS,
            currentRoute = currentRoute
        ) {
            navController.navigate(StoreUiDestinations.LIST_OF_SELLERS)
        }
        NavBarButton(
            content = {
                Icon(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = "",
                    modifier = Modifier.size(35.dp)
                )
            },
            route = StoreUiDestinations.PROFILE,
            currentRoute = currentRoute
        ) {
            navController.navigate(StoreUiDestinations.PROFILE)
        }
        NavBarButton(
            content = {
                Icon(
                    imageVector = Icons.Default.ShoppingCart,
                    contentDescription = "",
                    modifier = Modifier.size(35.dp)
                )
                if (state.value.bucketProductsCount.value > 0) {
                    BucketCountView(state.value.bucketProductsCount.value)
                }
            },
            route = StoreUiDestinations.BUCKET,
            currentRoute = currentRoute
        ) {
            navController.navigate(StoreUiDestinations.BUCKET)
        }
    }
}

@Composable
fun BucketCountView(count: Int) {
    Box(
        modifier = Modifier
            .fillMaxHeight()
            .offset(x = (-10).dp),
        contentAlignment = Alignment.TopStart
    ) {
        Card(
            modifier = Modifier
                .size(15.dp, 15.dp)
                .clip(RoundedCornerShape(15.dp)),
            backgroundColor = MAIN_BLUE
        ) {
            Text(
                count.toString(),
                modifier = Modifier
                    .size(15.dp, 15.dp),
                fontSize = 10.sp,
                textAlign = TextAlign.Center,
                color = Color.White
            )
        }
    }
}

@ExperimentalUnitApi
@Composable
fun NavBarButton(
    content: @Composable () -> Unit,
    route: String,
    currentRoute: String,
    onClick: () -> Unit
) {
    val isSelected = route == currentRoute

    Button(
        modifier = Modifier.border(0.dp, Color.White, RectangleShape),
        content = { content() },
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color.White,
            contentColor = if (isSelected) Color.DarkGray else Color.LightGray
        ),
        elevation = ButtonDefaults.elevation(
            defaultElevation = 0.dp,
            pressedElevation = 0.dp
        ),
        onClick = onClick
    )
}