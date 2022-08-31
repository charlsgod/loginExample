package com.charlsgod.loginexample.data.server

import com.charlsgod.loginexample.data.model.LoggedInUser
import com.charlsgod.loginexample.data.server.model.User
import javax.inject.Inject

class LoginDataSource @Inject constructor(
    private val loginApi: LoginApi
) : RemoteDataSource {

    override suspend fun login(user: User): Result<LoggedInUser> = loginApi.login(user)
}