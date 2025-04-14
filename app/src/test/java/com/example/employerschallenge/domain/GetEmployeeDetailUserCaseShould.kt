package com.example.employerschallenge.domain

import com.example.employerschallenge.core.assertThatEquals
import com.example.employerschallenge.core.assertThatIsInstanceOf
import com.example.employerschallenge.data.EmployeesRepository
import com.example.employerschallenge.data.datasource.exception.DataException
import com.example.employerschallenge.domain.model.Employee
import com.example.employerschallenge.fakedata.ANY_EMPLOYEE_DETAIL_ID
import com.example.employerschallenge.fakedata.givenEmployeeFakeData
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.lastOrNull
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class GetEmployeeDetailUserCaseShould {

    private val employeesRepository = mock<EmployeesRepository>()
    private lateinit var getEmployeeDetailUseCase: GetEmployeeDetailUseCase

    @Before
    fun setup() {
        getEmployeeDetailUseCase = GetEmployeeDetailUseCase(employeesRepository)
    }

    @Test
    fun `Get Employee data when fetchEmployeeDetail is success`() = runTest {
        val employee = givenEmployeeFakeData()
        val resultSuccess = Result.success(employee)
        whenever(employeesRepository.fetchEmployeeDetail(ANY_EMPLOYEE_DETAIL_ID)).thenReturn(flowOf(resultSuccess))

        val result = getEmployeeDetailUseCase.fetchEmployeeDetail(ANY_EMPLOYEE_DETAIL_ID).lastOrNull()

        verify(employeesRepository).fetchEmployeeDetail(ANY_EMPLOYEE_DETAIL_ID)
        assertThatEquals(result?.getOrNull(), employee)
    }

    @Test
    fun `Get EmployeesDetailException data when fetchEmployeeDetail is failure`() = runTest {
        val resultFailure: Result<Employee> = Result.failure(DataException.EmployeesDetailException())
        whenever(employeesRepository.fetchEmployeeDetail(ANY_EMPLOYEE_DETAIL_ID)).thenReturn(flowOf(resultFailure))

        val result = getEmployeeDetailUseCase.fetchEmployeeDetail(ANY_EMPLOYEE_DETAIL_ID).lastOrNull()

        verify(employeesRepository).fetchEmployeeDetail(ANY_EMPLOYEE_DETAIL_ID)
        assertThatIsInstanceOf<DataException.EmployeesDetailException>(result?.exceptionOrNull())
    }
}
