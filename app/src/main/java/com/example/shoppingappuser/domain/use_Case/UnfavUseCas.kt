package com.example.shoppingappuser.domain.use_Case

import com.example.shoppingappuser.common.ResultState
import com.example.shoppingappuser.domain.repo.Repo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UnfavUseCase @Inject constructor(
    private val repo: Repo

){
    fun unfav(itemId : String) : Flow<ResultState<String>> {
        return repo.unFav(itemId)
    }}