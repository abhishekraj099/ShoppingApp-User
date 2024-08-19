package com.example.shoppingappuser.domain.use_Case


import com.example.shoppingappuser.common.ResultState
import com.example.shoppingappuser.domain.models.UserData
import com.example.shoppingappuser.domain.repo.Repo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CreateUserUseCase @Inject constructor(private val repo: Repo) {
    fun createUser(userData: UserData) : Flow<ResultState<String>> {
        return repo.registerUserWithEmailAndPassword(userData)

    }


}