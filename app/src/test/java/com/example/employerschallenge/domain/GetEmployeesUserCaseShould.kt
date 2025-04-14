package com.example.employerschallenge.domain

import com.example.employerschallenge.core.assertThatEquals
import com.example.employerschallenge.core.assertThatIsInstanceOf
import com.example.employerschallenge.data.EmployeesRepository
import com.example.employerschallenge.data.datasource.exception.DataException
import com.example.employerschallenge.domain.model.Employee
import com.example.employerschallenge.fakedata.givenEmployeesFakeData
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.lastOrNull
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class GetEmployeesUserCaseShould {

    private val employeesRepository = mock<EmployeesRepository>()
    private lateinit var getEmployeesUseCase: GetEmployeesUseCase

    @Before
    fun setup() {
        getEmployeesUseCase = GetEmployeesUseCase(employeesRepository)
    }

    @Test
    fun `Get Employees data when fetchEmployees is success`() = runTest {
        val employees = givenEmployeesFakeData()
        val resultSuccess = Result.success(employees)
        whenever(employeesRepository.fetchEmployees()).thenReturn(flowOf(resultSuccess))

        val result = getEmployeesUseCase.fetchEmployees().lastOrNull()

        verify(employeesRepository).fetchEmployees()
        assertThatEquals(result?.getOrNull(), employees)
    }

    @Test
    fun `Get EmployeesException data when fetchEmployees is failure`() = runTest {
        val resultFailure: Result<List<Employee>> = Result.failure(DataException.EmployeesException())
        whenever(employeesRepository.fetchEmployees()).thenReturn(flowOf(resultFailure))

        val result = getEmployeesUseCase.fetchEmployees().lastOrNull()

        verify(employeesRepository).fetchEmployees()
        assertThatIsInstanceOf<DataException.EmployeesException>(result?.exceptionOrNull())
    }
}
