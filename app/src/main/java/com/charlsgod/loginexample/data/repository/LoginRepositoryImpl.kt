package com.charlsgod.loginexample.data.repository

import com.charlsgod.loginexample.data.model.LoggedInUser
import com.charlsgod.loginexample.data.server.RemoteDataSource
import com.charlsgod.loginexample.data.server.Result
import com.charlsgod.loginexample.data.server.model.toServer
import com.charlsgod.loginexample.domain.entity.User
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val dataSource: RemoteDataSource
): LoginRepository {

    var user: LoggedInUser? = null
        private set

    val isLoggedIn: Boolean
        get() = user != null

    init {
        // If user credentials will be cached in local storage, it is recommended it be encrypted
        user = null
    }

    override suspend fun login(user: User): Result<LoggedInUser> {

        val result = dataSource.login(user.toServer())
        if (result is Result.Success) {
            setLoggedInUser(result.data)
        }

        return result
    }

    override fun setLoggedInUser(loggedInUser: LoggedInUser) {
        this.user = loggedInUser
        // If user credentials will be cached in local storage, it is recommended it be encrypted
    }
}
