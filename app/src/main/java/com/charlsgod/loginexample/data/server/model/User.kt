package com.charlsgod.loginexample.data.server.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import com.charlsgod.loginexample.domain.entity.User as UserDomain

@Parcelize
data class User(
    val username: String,
    val password: String
) : Parcelable

fun UserDomain.toServer() =
    User(
        username = username,
        password = password
    )