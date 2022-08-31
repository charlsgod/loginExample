package com.charlsgod.loginexample.presentation.ui.login

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.charlsgod.loginexample.R
import com.charlsgod.loginexample.data.model.LoggedInUser
import com.charlsgod.loginexample.domain.usecases.DoLoginUseCase
import com.charlsgod.loginexample.rules.MainCoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

@OptIn(ExperimentalCoroutinesApi::class)
class LoginViewModelTest {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var fakeLoginRepository: FakeLoginRepository
    private lateinit var doLoginUsesCase: DoLoginUseCase
    private lateinit var viewModel: LoginViewModel

    @Before
    fun setup() {
        fakeLoginRepository = FakeLoginRepository()
        doLoginUsesCase = DoLoginUseCase(fakeLoginRepository)
        viewModel = LoginViewModel(doLoginUsesCase)
    }

    @Test
    fun `execute successful login`() = runTest {
        val mockResult = LoggedInUser("0", "Carlos Rios")

        viewModel.login("carlos1987@gmail.com", "12345")
        advanceUntilIdle()

        assert(viewModel.loginResult.value?.success?.displayName == mockResult.displayName)
    }


    @Test
    fun `execute failure login`() = runTest {
        val errorMessage = R.string.login_failed

        viewModel.login("carlos198@gmail.com", "123456")
        advanceUntilIdle()

        assert(errorMessage == viewModel.loginResult.value?.error)
    }
}