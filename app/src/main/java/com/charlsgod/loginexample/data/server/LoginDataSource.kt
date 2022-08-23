package com.charlsgod.loginexample.data.server

import com.charlsgod.loginexample.data.model.LoggedInUser
import com.charlsgod.loginexample.data.repository.RemoteDataSource
import com.charlsgod.loginexample.data.server.model.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LoginDataSource @Inject constructor(
    private val loginApi: LoginApi
) : RemoteDataSource {

    override fun login(user: User): Result<LoggedInUser> = loginApi.login(user)
}