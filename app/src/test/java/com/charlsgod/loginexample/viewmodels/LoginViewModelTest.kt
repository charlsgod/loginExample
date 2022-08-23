package com.charlsgod.loginexample.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.charlsgod.loginexample.data.model.LoggedInUser
import com.charlsgod.loginexample.data.server.Result
import com.charlsgod.loginexample.domain.entity.User
import com.charlsgod.loginexample.domain.usecases.DoLoginUseCase
import com.charlsgod.loginexample.presentation.ui.login.LoginViewModel
import com.charlsgod.loginexample.rules.MainCoroutineRule
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import java.io.IOException

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class LoginViewModelTest {
    private val doLoginUsesCase = mock<DoLoginUseCase>()

    private lateinit var viewModel: LoginViewModel

    @get:Rule
    var rule = InstantTaskExecutorRule()

    @get:Rule
    val mainTestCoroutineRule = MainCoroutineRule()

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        viewModel = LoginViewModel(doLoginUsesCase)
    }

    @Test
    fun `execute successful login`() = mainTestCoroutineRule.runTest {
        val user = User("carlos1987@gmail.com", "12345")

        val mockResult = LoggedInUser("0", "Carlos Rios")
        val result: Result<LoggedInUser> = Result.Success(mockResult)

        Mockito.`when`(doLoginUsesCase.invoke(user))
            .doReturn(result)

        viewModel.login("carlos1987@gmail.com", "12345")

        assertEquals(mockResult.displayName, viewModel.loginResult.value!!.success!!.displayName)
    }


    @Test
    fun `execute failure login`() = mainTestCoroutineRule.runTest {
        val errorMessage = "Login failed"
        val user = User("carlos1987@gmail.com", "12345")

        val result = Result.Error(IOException("Error logging in", Throwable("Error")))

        Mockito.`when`(doLoginUsesCase.invoke(user))
            .doReturn(result)

        viewModel.login("carlos1987@gmail.com", "12345")

        assertEquals(errorMessage, viewModel.loginResult.value!!.error)
    }
}