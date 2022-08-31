package com.charlsgod.loginexample.data.server

import com.charlsgod.loginexample.data.model.LoggedInUser
import com.charlsgod.loginexample.data.server.model.User

interface RemoteDataSource {
    suspend fun login(user: User): Result<LoggedInUser>
}