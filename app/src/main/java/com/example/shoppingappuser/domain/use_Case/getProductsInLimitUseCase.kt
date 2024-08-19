package com.example.shoppingappuser.domain.use_Case

import com.example.shoppingappuser.common.ResultState
import com.example.shoppingappuser.domain.models.ProductDataModels

import com.example.shoppingappuser.domain.repo.Repo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class getProductsInLimitUseCase @Inject constructor(private val repo: Repo) {
    fun getProductsInLimit() : Flow<ResultState<List<ProductDataModels>>> {
        return repo.getProductsInLimited()
    }

}