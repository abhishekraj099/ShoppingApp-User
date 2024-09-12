package com.example.shoppingappuser.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.shoppingappuser.domain.models.FavDataModel
import com.example.shoppingappuser.domain.models.ProductDataModels
import com.example.shoppingappuser.navigation.Routes
import com.example.shoppingappuser.screens.utils.AnimatedEmpty
import com.example.shoppingappuser.screens.utils.AnimatedLoading
import com.example.shoppingappuser.viewModels.ShoppingAppViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


import androidx.compose.foundation.layout.*

import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GetAllFav(
    viewModel: ShoppingAppViewModel = hiltViewModel(),
    navController: NavController,
) {
    val getAllFav = viewModel.getAllFavState.collectAsStateWithLifecycle()
    val getFavData: List<FavDataModel> = getAllFav.value.userData.orEmpty().filterNotNull()
    val unfav = viewModel.unFavState.collectAsStateWithLifecycle()
    val coroutineScope = rememberCoroutineScope()
    LaunchedEffect(
        key1 = Unit
    ) {
        coroutineScope.launch(Dispatchers.IO) {

            viewModel.getAllFav()}
    }
    LaunchedEffect(key1 = unfav.value.userData) {
        coroutineScope.launch(Dispatchers.IO) {

            if (unfav.value.userData != null) {
                viewModel.getAllFav()
            }
            viewModel.unFavState.value.userData = null}
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Wishlist" , fontWeight = FontWeight.Bold) }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {


            when {
                getAllFav.value.isLoading   -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        AnimatedLoading()
                    }
                }

                getAllFav.value.errorMessage != null  || unfav.value.errorMessage != null -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(getAllFav.value.errorMessage!!)
                    }
                }

                getFavData.isEmpty() -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        AnimatedEmpty()

                    }
                }

                else -> {
                    LazyColumn(
                        // columns = GridCells.Fixed(2),
                        contentPadding = PaddingValues(16.dp),
                        // horizontalArrangement = Arrangement.spacedBy(16.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        items(getFavData) { product ->
                            FavEachItem(product = product, onProductClick = {
                                navController.navigate(Routes.EachProductDetailsScreen(product.product.productId))
                            }, onDelete = {
                                viewModel.unFav(product.favId)
                            }
                            )
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun FavEachItem(product: FavDataModel, onProductClick: () -> Unit, onDelete: () -> Unit ){

    Card(
        modifier = Modifier.fillMaxWidth().padding(8.dp),
        onClick = onProductClick,
        shape = RoundedCornerShape(8.dp)

    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically

        ){
            AsyncImage(
                model = product.product.image,
                contentDescription = null,
                modifier = Modifier
                    .size(80.dp)
                    .clip(RoundedCornerShape(8.dp)
                    )
            )
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 16.dp)

            ) {
                Text(
                    text = product.product.name,
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Bold,
                    maxLines = 2,)

                Text(
                    text = "Rs ${product.product.finalPrice}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.primary
                )
            }

            IconButton(onClick = { onDelete() }) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Delete"
                )
            }

        }
    }
}




