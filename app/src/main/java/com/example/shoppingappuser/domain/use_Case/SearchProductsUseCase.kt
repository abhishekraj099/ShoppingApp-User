package com.example.shoppingappuser.domain.use_Case

import com.example.shoppingappuser.domain.repo.Repo
import javax.inject.Inject

class SearchProductsUseCase @Inject constructor(private val repo: Repo) {
    fun searchProducts(query: String) = repo.searchProducts(query)
}