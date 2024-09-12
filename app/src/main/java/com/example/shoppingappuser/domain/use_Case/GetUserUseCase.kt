package com.example.shoppingappuser.domain.use_Case
import com.example.shoppingappuser.common.ResultState
import com.example.shoppingappuser.domain.models.UserDataParent

import com.example.shoppingappuser.domain.repo.Repo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class GetUserUseCase @Inject constructor( val repo: Repo) {

    fun getuserById(uid:String) : Flow<ResultState<UserDataParent>> {
        return repo.getuserById(uid)
    }
}