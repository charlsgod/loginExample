package com.charlsgod.loginexample.data.repository

import com.charlsgod.loginexample.data.model.LoggedInUser
import com.charlsgod.loginexample.data.server.Result
import com.charlsgod.loginexample.domain.entity.User

interface LoginRepository {

    suspend fun login(user: User): Result<LoggedInUser>

    fun setLoggedInUser(loggedInUser: LoggedInUser)

}
