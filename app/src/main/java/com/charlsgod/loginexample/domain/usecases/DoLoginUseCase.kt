package com.charlsgod.loginexample.domain.usecases

import com.charlsgod.loginexample.data.model.LoggedInUser
import com.charlsgod.loginexample.data.repository.LoginRepository
import com.charlsgod.loginexample.data.server.Result
import com.charlsgod.loginexample.domain.entity.User
import javax.inject.Inject

class DoLoginUseCase @Inject constructor(private val repository: LoginRepository) {

    suspend operator fun invoke(user: User): Result<LoggedInUser> =
        repository.login(user)

}