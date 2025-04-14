package com.example.employerschallenge.ui.employees

import com.example.employerschallenge.core.TestDispatcherRule
import com.example.employerschallenge.core.assertThatEquals
import com.example.employerschallenge.core.assertThatIsInstanceOf
import com.example.employerschallenge.data.datasource.exception.DataException
import com.example.employerschallenge.domain.GetEmployeesUseCase
import com.example.employerschallenge.fakedata.ANY_EMPLOYEE_DETAIL_ID
import com.example.employerschallenge.fakedata.givenEmployeesFakeData
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.kotlin.whenever

class EmployeesViewModelShould {

    @get:Rule
    var testDispatcherRule = TestDispatcherRule()

    private val getEmployeesUseCase = mock<GetEmployeesUseCase>()
    private lateinit var employeesViewModel: EmployeesViewModel

    @Before
    fun setup() {
        employeesViewModel = EmployeesViewModel(getEmployeesUseCase, testDispatcherRule.coroutinesDispatchers)
    }

    @Test
    fun `Get Employees data when initGetEmployees is called and fetchEmployees is success`() = runTest {
        val employees = givenEmployeesFakeData()
        whenever(getEmployeesUseCase.fetchEmployees()).thenReturn(flowOf(Result.success(employees)))

        employeesViewModel.getEmployees()

        val result = employeesViewModel.employeesUiState.firstOrNull()

        verify(getEmployeesUseCase).fetchEmployees()
        assertThatEquals(result?.employees, employees)
    }

    @Test
    fun `Get EmployeesException data when initGetEmployees is called and fetchEmployees is failure`() = runTest {
        whenever(getEmployeesUseCase.fetchEmployees()).thenReturn(flowOf(Result.failure(DataException.EmployeesException())))

        employeesViewModel.getEmployees()

        val result = employeesViewModel.employeesUiState.firstOrNull()

        verify(getEmployeesUseCase).fetchEmployees()
        assertThatIsInstanceOf<DataException.EmployeesException>(result?.error)
    }

    @Test
    fun `Navigate to EmployeeDetail when openEmployeeDetail is called`() = runTest {
        employeesViewModel.openEmployeeDetail(ANY_EMPLOYEE_DETAIL_ID)

        val result = employeesViewModel.navigateToEmployeeDetailEvent.firstOrNull()

        assertThatEquals(result, ANY_EMPLOYEE_DETAIL_ID)
    }
}
