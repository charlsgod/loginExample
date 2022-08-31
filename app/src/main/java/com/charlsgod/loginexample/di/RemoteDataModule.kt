package com.charlsgod.loginexample.di

import com.charlsgod.loginexample.data.repository.LoginRepository
import com.charlsgod.loginexample.data.repository.LoginRepositoryImpl
import com.charlsgod.loginexample.data.server.LoginApi
import com.charlsgod.loginexample.data.server.LoginApiImpl
import com.charlsgod.loginexample.data.server.LoginDataSource
import com.charlsgod.loginexample.data.server.RemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RemoteDataModule {

    @Singleton
    @Provides
    fun provideLoginApiService(): LoginApi = LoginApiImpl()


    @Singleton
    @Provides
    fun provideRemoteDataSource(loginApi: LoginApi): RemoteDataSource =
        LoginDataSource(loginApi)

    @Singleton
    @Provides
    fun provideLoginRepository(dataSource: RemoteDataSource): LoginRepository =
        LoginRepositoryImpl(dataSource)
}