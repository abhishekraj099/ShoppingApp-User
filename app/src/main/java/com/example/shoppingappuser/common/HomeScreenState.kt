package com.example.shoppingappuser.common

import com.example.shoppingappuser.domain.models.BannerDataModels
import com.example.shoppingappuser.domain.models.CategoryDataModels
import com.example.shoppingappuser.domain.models.ProductDataModels


data class HomeScreenState(
    val isLoading: Boolean = true,
    val errorMessage: String? = null,
    val categories: List<CategoryDataModels>? = null,
    val products: List<ProductDataModels>? = null,
    val banners: List<com.example.shoppingappuser.domain.models.BannerDataModels>? = null
)