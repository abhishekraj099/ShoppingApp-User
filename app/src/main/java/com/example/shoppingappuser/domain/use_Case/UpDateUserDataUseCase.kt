package com.example.shoppingappuser.domain.use_Case

import com.example.shoppingappuser.common.ResultState
import com.example.shoppingappuser.domain.models.UserDataParent

import com.example.shoppingappuser.domain.repo.Repo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UpDateUserDataUseCase @Inject constructor(private val repo: Repo)  {
    fun upDateUserData(userDataParent: UserDataParent) : Flow<ResultState<String>> {
        return repo.upDateUserData(userDataParent)

    }

}