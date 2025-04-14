package com.example.employerschallenge.ui.login

import com.example.employerschallenge.core.TestDispatcherRule
import com.example.employerschallenge.core.assertThatEquals
import com.example.employerschallenge.domain.LoginUseCase
import com.example.employerschallenge.fakedata.ANY_INVALID_EMPLOYEE_ID
import com.example.employerschallenge.fakedata.ANY_VALID_EMPLOYEE_ID
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.kotlin.whenever

class LoginViewModelShould {

    @get:Rule
    var testDispatcherRule = TestDispatcherRule()

    private val loginUseCase = mock<LoginUseCase>()
    private lateinit var loginViewModel: LoginViewModel

    @Before
    fun setup() {
        loginViewModel = LoginViewModel(loginUseCase, testDispatcherRule.coroutinesDispatchers)
    }

    @Test
    fun `is true when login is called and userId is valid`() = runTest {
        whenever(loginUseCase.login(ANY_VALID_EMPLOYEE_ID)).thenReturn(flowOf(true))

        loginViewModel.login(ANY_VALID_EMPLOYEE_ID)


        val loadingState = loginViewModel.loadingUiState.firstOrNull()
        val result = loginViewModel.validUseEvent.firstOrNull()

        verify(loginUseCase).login(ANY_VALID_EMPLOYEE_ID)
        assertThatEquals(loadingState, false)
        assertThatEquals(result, true)
    }

    @Test
    fun `is false when login is called and userId is invalid`() = runTest {
        whenever(loginUseCase.login(ANY_INVALID_EMPLOYEE_ID)).thenReturn(flowOf(false))

        loginViewModel.login(ANY_INVALID_EMPLOYEE_ID)

        val loadingState = loginViewModel.loadingUiState.firstOrNull()
        val result = loginViewModel.validUseEvent.firstOrNull()

        verify(loginUseCase).login(ANY_INVALID_EMPLOYEE_ID)
        assertThatEquals(loadingState, false)
        assertThatEquals(result, false)
    }
}
