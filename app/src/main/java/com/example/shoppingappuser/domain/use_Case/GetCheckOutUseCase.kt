package com.example.shoppingappuser.domain.use_Case

import com.example.shoppingappuser.common.ResultState
import com.example.shoppingappuser.domain.models.ProductDataModels

import com.example.shoppingappuser.domain.repo.Repo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCheckOutUseCase @Inject constructor(private val repo: Repo)  {

    fun getCheckoutUseCse(productId: String) : Flow<ResultState<ProductDataModels>> {
        return repo.getCheckout(productId)

    }
}