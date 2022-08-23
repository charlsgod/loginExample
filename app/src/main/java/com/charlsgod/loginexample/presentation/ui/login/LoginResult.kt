package com.charlsgod.loginexample.presentation.ui.login

data class LoginResult(
    val success: LoggedInUserView? = null,
    val error: Int? = null
)