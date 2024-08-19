package com.example.shoppingappuser.domain.use_Case


import com.example.shoppingappuser.common.ResultState
import com.example.shoppingappuser.domain.models.CartDataModels
import com.example.shoppingappuser.domain.repo.Repo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AddtoCardUseCase @Inject constructor(private val repo: Repo)  {
    fun addtoCard(cartDataModels : CartDataModels): Flow<ResultState<String>> {
        return repo.addToCart(cartDataModels)
    }
}