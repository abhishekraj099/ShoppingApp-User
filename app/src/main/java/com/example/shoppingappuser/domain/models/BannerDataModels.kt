package com.example.shoppingappuser.domain.models

data class BannerDataModels(
    val name : String = "",
    val image : String = "",
    val date : Long = System.currentTimeMillis(),
)
