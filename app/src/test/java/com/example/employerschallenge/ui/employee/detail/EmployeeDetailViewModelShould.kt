package com.example.employerschallenge.ui.employee.detail

import com.example.employerschallenge.core.TestDispatcherRule
import com.example.employerschallenge.core.assertThatEquals
import com.example.employerschallenge.core.assertThatIsInstanceOf
import com.example.employerschallenge.data.datasource.exception.DataException
import com.example.employerschallenge.domain.GetEmployeeDetailUseCase
import com.example.employerschallenge.fakedata.ANY_EMPLOYEE_DETAIL_ID
import com.example.employerschallenge.fakedata.givenEmployeeFakeData
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.kotlin.whenever

class EmployeeDetailViewModelShould {

    @get:Rule
    var testDispatcherRule = TestDispatcherRule()

    private val getEmployeeDetailUseCase = mock<GetEmployeeDetailUseCase>()
    private lateinit var employeeDetailViewModel: EmployeeDetailViewModel

    @Before
    fun setup() {
        employeeDetailViewModel = EmployeeDetailViewModel(getEmployeeDetailUseCase, testDispatcherRule.coroutinesDispatchers)
    }

    @Test
    fun `Get Employee data when fetchEmployee is success`() = runTest {
        val employee = givenEmployeeFakeData()
        whenever(getEmployeeDetailUseCase.fetchEmployeeDetail(ANY_EMPLOYEE_DETAIL_ID)).thenReturn(flowOf(Result.success(employee)))

        employeeDetailViewModel.getEmployeeDetail(ANY_EMPLOYEE_DETAIL_ID)

        val result = employeeDetailViewModel.employeeDetailUiState.firstOrNull()

        verify(getEmployeeDetailUseCase).fetchEmployeeDetail(ANY_EMPLOYEE_DETAIL_ID)
        assertThatEquals(result?.employee, employee)
    }

    @Test
    fun `Get EmployeeDetailException data when fetchEmployee is failure`() = runTest {
        whenever(getEmployeeDetailUseCase.fetchEmployeeDetail(ANY_EMPLOYEE_DETAIL_ID))
            .thenReturn(flowOf(Result.failure(DataException.EmployeesDetailException())))

        employeeDetailViewModel.getEmployeeDetail(ANY_EMPLOYEE_DETAIL_ID)

        val result = employeeDetailViewModel.employeeDetailUiState.firstOrNull()

        verify(getEmployeeDetailUseCase).fetchEmployeeDetail(ANY_EMPLOYEE_DETAIL_ID)
        assertThatIsInstanceOf<DataException.EmployeesDetailException>(result?.error)
    }
}
