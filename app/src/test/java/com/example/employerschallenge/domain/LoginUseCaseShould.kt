package com.example.employerschallenge.domain

import com.example.employerschallenge.core.assertThatEquals
import com.example.employerschallenge.fakedata.ANY_INVALID_EMPLOYEE_ID
import com.example.employerschallenge.fakedata.ANY_VALID_EMPLOYEE_ID
import kotlinx.coroutines.flow.lastOrNull
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class LoginUseCaseShould {

    private lateinit var loginUseCase: LoginUseCase

    @Before
    fun setup() {
        loginUseCase = LoginUseCase()
    }

    @Test
    fun `is true when login is called and userId is valid`() = runTest {
        val result = loginUseCase.login(ANY_VALID_EMPLOYEE_ID).lastOrNull()

        assertThatEquals(result, true)
    }

    @Test
    fun `is false when login is called and userId is invalid`() = runTest {
        val result = loginUseCase.login(ANY_INVALID_EMPLOYEE_ID).lastOrNull()

        assertThatEquals(result, false)
    }
}

