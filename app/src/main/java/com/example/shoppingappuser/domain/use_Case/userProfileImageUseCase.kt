package com.example.shoppingappuser.domain.use_Case

import android.net.Uri
import com.example.shoppingappuser.common.ResultState

import com.example.shoppingappuser.domain.repo.Repo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class userProfileImageUseCase @Inject constructor(private val repo: Repo)  {
    fun userProfileImage(uri: Uri) : Flow<ResultState<String>> {
        return repo.userProfileImage(uri)
    }

}