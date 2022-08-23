package com.charlsgod.loginexample.di

import com.charlsgod.loginexample.data.repository.RemoteDataSource
import com.charlsgod.loginexample.data.server.LoginApi
import com.charlsgod.loginexample.data.server.LoginApiImpl
import com.charlsgod.loginexample.data.server.LoginDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RemoteDataModule {

    @Provides
    @Singleton
    fun provideLoginApiService(): LoginApi = LoginApiImpl()


    @Provides
    fun provideRemoteDataSource(loginApi: LoginApi): RemoteDataSource =
        LoginDataSource(loginApi)
}