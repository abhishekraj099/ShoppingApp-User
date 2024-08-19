package com.example.shoppingappuser.navigation

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController




import com.google.firebase.auth.FirebaseAuth




import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.ShoppingCart

import androidx.compose.material3.*
import androidx.compose.runtime.*






import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.toRoute
import com.example.bottombar.AnimatedBottomBar
import com.example.bottombar.components.BottomBarItem
import com.example.bottombar.model.IndicatorDirection
import com.example.bottombar.model.IndicatorStyle
import com.example.shoppingappuser.screens.AllCategoriesScreenUi
import com.example.shoppingappuser.screens.CartScreenUi
import com.example.shoppingappuser.screens.CheckOutScreenUi
import com.example.shoppingappuser.screens.EachCategorieProductScreenUi
import com.example.shoppingappuser.screens.EachProductDetailsScreenUi
import com.example.shoppingappuser.screens.GetAllFav
import com.example.shoppingappuser.screens.GetAllProducts
import com.example.shoppingappuser.screens.HomeScreenUi
import com.example.shoppingappuser.screens.LoginScreenUi
import com.example.shoppingappuser.screens.ProfileScreenUi
import com.example.shoppingappuser.screens.SingUpScreenUi
import com.example.shoppingappuser.ui.theme.SweetPink


@Composable
fun App(firebaseAuth: FirebaseAuth ) {


    val navController = rememberNavController()

    var selectedItem by remember { mutableIntStateOf(0) }

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination?.route
    val shouldShowBottomBar = remember { mutableStateOf(false) }

    LaunchedEffect(currentDestination) {
        shouldShowBottomBar.value = when (currentDestination) {
            Routes.LoginScreen::class.qualifiedName, Routes.SingUpScreen::class.qualifiedName -> false
            else -> true
        }
    }

    val BottomNavItem = listOf(
        BottomNavItem("Home", Icons.Default.Home, unseletedIcon = Icons.Outlined.Home),
        BottomNavItem("WishList", Icons.Default.Favorite, unseletedIcon = Icons.Outlined.Favorite),
        BottomNavItem("Cart", Icons.Default.ShoppingCart, unseletedIcon = Icons.Outlined.ShoppingCart),
        BottomNavItem("Profile", Icons.Default.Person, unseletedIcon = Icons.Outlined.Person),

        )

    var startScreen = if (firebaseAuth.currentUser == null) {
        SubNavigation.LoginSingUpScreen
    } else {
        SubNavigation.MainHomeScreen
    }

    Scaffold(
        // Modifier.fillMaxSize(),
        bottomBar = {
            if (shouldShowBottomBar.value) {

                AnimatedBottomBar(
                    selectedItem = selectedItem,
                    itemSize = BottomNavItem.size,
                    modifier = Modifier.padding(bottom = 8.dp),

                    containerColor = Color.Transparent,
                    // indicatorColor = MaterialTheme.colorScheme.primaryContainer,
                    indicatorColor = SweetPink,
                    contentColor = MaterialTheme.colorScheme.onPrimaryContainer,

                    indicatorDirection = IndicatorDirection.BOTTOM,


                    indicatorStyle = IndicatorStyle.FILLED


                ) {
                    BottomNavItem.forEachIndexed { index, navigationItem ->
                        BottomBarItem(
                            selected = selectedItem == index,
                            onClick = {

                                selectedItem = index
                                when (index) {
                                    0 -> navController.navigate(Routes.HomeScreen)
                                    1 -> navController.navigate(Routes.WishListScreen)
                                    2 -> navController.navigate(Routes.CartScreen)
                                    3 -> navController.navigate(Routes.ProfileScreen)
                                }




                            },
                            imageVector = navigationItem.icon,
                            label = navigationItem.name,
                            containerColor = Color.Transparent,
                        )
                    }
                }
            }
        },
    ) { innerPadding ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = if (shouldShowBottomBar.value) innerPadding.calculateBottomPadding() else 0.dp)
        ) {

            NavHost(navController = navController, startDestination = startScreen) {


                navigation<SubNavigation.LoginSingUpScreen>(startDestination = Routes.LoginScreen) {
                    composable<Routes.LoginScreen> {
                        LoginScreenUi(
                            navController = navController
                        )
                    }

                    composable<Routes.SingUpScreen> {
                        SingUpScreenUi(
                            navController = navController
                        )
                    }
                }


                navigation<SubNavigation.MainHomeScreen>(startDestination = Routes.HomeScreen) {


                    composable<Routes.HomeScreen> {
                        HomeScreenUi(
                            navController = navController
                        )
                    }

                    composable<Routes.ProfileScreen> {
                        ProfileScreenUi(firebaseAuth = firebaseAuth, navController = navController)
                    }

                    composable<Routes.WishListScreen> {
                        GetAllFav(
                            navController = navController
                        )
                    }

                    composable<Routes.CartScreen> {
                        CartScreenUi(
                            navController = navController
                        )
                    }
                    composable<Routes.PayScreen> {
                        Text(text = "Pay")
                    }




                    composable<Routes.SeeAllProductsScreen> {
                        GetAllProducts(
                            navController = navController
                        )

                    }

                    composable<Routes.AllCategoriesScreen> {
                        AllCategoriesScreenUi(
                            navController = navController
                        )


                    }

                }







                composable<Routes.EachProductDetailsScreen> {
                    val product: Routes.EachProductDetailsScreen = it.toRoute()
                    EachProductDetailsScreenUi(
                        productID = product.productID,
                        navController = navController


                    )
                }

                composable<Routes.EachCategoryItemsScreen> {
                    val category: Routes.EachCategoryItemsScreen = it.toRoute()
                    EachCategorieProductScreenUi(
                        categoryName = category.categoryName,
                        navController = navController
                    )
                }

                composable<Routes.CheckoutScreen> {
                    val product: Routes.EachProductDetailsScreen = it.toRoute()
                    CheckOutScreenUi(
                        productID = product.productID,
                        navController = navController, )

                }


            }


        }

    }




}


data class BottomNavItem(val name: String, val icon: ImageVector, val unseletedIcon: ImageVector)
