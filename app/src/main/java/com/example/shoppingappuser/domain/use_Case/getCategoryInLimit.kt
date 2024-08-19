package com.example.shoppingappuser.domain.use_Case

import com.example.shoppingappuser.common.ResultState
import com.example.shoppingappuser.domain.models.CategoryDataModels

import com.example.shoppingappuser.domain.repo.Repo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class getCategoryInLimit @Inject constructor(private val repo: Repo) {
    fun getCategoriesInLimited() : Flow<ResultState<List<CategoryDataModels>>> {
        return repo.getCategoriesInLimited()

    }
}