package com.example.shoppingappuser.domain.use_Case

import com.example.shoppingappuser.common.ResultState
import com.example.shoppingappuser.domain.repo.Repo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DeleteFromCartUseCase @Inject constructor(private val repo: Repo) {

    fun deleteFromCart(itemID: String) : Flow<ResultState<String>> {
        return repo.deleteFromCart(itemID)

    }
}