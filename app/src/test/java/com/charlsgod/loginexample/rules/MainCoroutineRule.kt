package com.charlsgod.loginexample.rules

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.invoke
import kotlinx.coroutines.test.*
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

@ExperimentalCoroutinesApi
class MainCoroutineRule : TestRule {

    val testCoroutineDispatcher = UnconfinedTestDispatcher()
    private val testCoroutineScope = TestScope()

    override fun apply(base: Statement?, description: Description?) = object : Statement() {
        @Throws(Throwable::class)
        override fun evaluate() {
            Dispatchers.setMain(testCoroutineDispatcher)
            base?.evaluate()

            Dispatchers.resetMain() //reset main dispatcher to original Main dispatcher
        }
    }

    fun runTest(block: suspend TestScope.() -> Unit) =
        testCoroutineScope.runTest {
            block()
        }
}