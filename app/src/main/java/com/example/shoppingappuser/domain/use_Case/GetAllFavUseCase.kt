package com.example.shoppingappuser.domain.use_Case

import com.example.shoppingappuser.common.ResultState
import com.example.shoppingappuser.domain.models.FavDataModel
import com.example.shoppingappuser.domain.models.ProductDataModels

import com.example.shoppingappuser.domain.repo.Repo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllFavUseCase @Inject constructor(private val repo: Repo)  {
    fun getAllFav() : Flow<ResultState<List<FavDataModel>>> {
        return repo.getAllFav()
    }
}