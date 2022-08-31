package com.charlsgod.loginexample.presentation.ui.login

import com.charlsgod.loginexample.data.model.LoggedInUser
import com.charlsgod.loginexample.data.repository.LoginRepository
import com.charlsgod.loginexample.data.server.Result
import com.charlsgod.loginexample.domain.entity.User
import java.io.IOException

class FakeLoginRepository : LoginRepository {

    override suspend fun login(user: User): Result<LoggedInUser> {

        val fakeUser = User("carlos1987@gmail.com", "12345")

        return if (user.username == fakeUser.username && user.password == fakeUser.password) {
            // TODO: handle loggedInUser authentication
            val userResponse = LoggedInUser(java.util.UUID.randomUUID().toString(), "Carlos Rios")
            Result.Success(userResponse)
        } else {
            Result.Error(IOException("Error logging in", Throwable("Error")))
        }
    }

    override fun setLoggedInUser(loggedInUser: LoggedInUser) {
        TODO("Not yet implemented")
    }
}