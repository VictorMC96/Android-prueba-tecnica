package com.example.employerschallenge.data

import com.example.employerschallenge.core.assertThatEquals
import com.example.employerschallenge.core.assertThatIsInstanceOf
import com.example.employerschallenge.data.datasource.exception.DataException
import com.example.employerschallenge.data.datasource.model.EmployeeDetailResponse
import com.example.employerschallenge.data.datasource.model.EmployeesResponse
import com.example.employerschallenge.data.datasource.remote.EmployeesRemoteDataSource
import com.example.employerschallenge.fakedata.ANY_EMPLOYEE_DETAIL_ID
import com.example.employerschallenge.fakedata.givenEmployeeDetailResponseFakeData
import com.example.employerschallenge.fakedata.givenEmployeeFakeData
import com.example.employerschallenge.fakedata.givenEmployeesFakeData
import com.example.employerschallenge.fakedata.givenEmployeesResponseFakeData
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.lastOrNull
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class EmployeesRepositoryShould {

    private val employeesRemoteDataSource = mock<EmployeesRemoteDataSource>()
    private lateinit var employeesRepository: EmployeesRepository

    @Before
    fun setup() {
        employeesRepository = EmployeesRepository(employeesRemoteDataSource)
    }

    @Test
    fun `Get Employees data when fetchEmployees is success`() = runTest {
        val employeesResponse = givenEmployeesResponseFakeData()
        val employees = givenEmployeesFakeData()
        val resultSuccess = Result.success(employeesResponse)
        whenever(employeesRemoteDataSource.fetchEmployees()).thenReturn(flowOf(resultSuccess))

        val result = employeesRepository.fetchEmployees().lastOrNull()

        verify(employeesRemoteDataSource).fetchEmployees()
        assertThatEquals(result?.getOrNull(), employees)
    }

    @Test
    fun `Get EmployeesException data when fetchEmployees is failure`() = runTest {
        val resultFailure: Result<EmployeesResponse> = Result.failure(DataException.EmployeesException())
        whenever(employeesRemoteDataSource.fetchEmployees()).thenReturn(flowOf(resultFailure))

        val result = employeesRepository.fetchEmployees().lastOrNull()

        verify(employeesRemoteDataSource).fetchEmployees()
        assertThatIsInstanceOf<DataException.EmployeesException>(result?.exceptionOrNull())
    }

    @Test
    fun `Get Employee data when fetchEmployeeDetail is success`() = runTest {
        val employeeDetailResponse = givenEmployeeDetailResponseFakeData()
        val employee = givenEmployeeFakeData()
        val resultSuccess = Result.success(employeeDetailResponse)
        whenever(employeesRemoteDataSource.fetchEmployeeDetail(ANY_EMPLOYEE_DETAIL_ID)).thenReturn(flowOf(resultSuccess))

        val result = employeesRepository.fetchEmployeeDetail(ANY_EMPLOYEE_DETAIL_ID).lastOrNull()

        verify(employeesRemoteDataSource).fetchEmployeeDetail(ANY_EMPLOYEE_DETAIL_ID)
        assertThatEquals(result?.getOrNull(), employee)
    }

    @Test
    fun `Get EmployeeDetailException data when fetchEmployeeDetail is failure`() = runTest {
        val resultFailure: Result<EmployeeDetailResponse> = Result.failure(DataException.EmployeesDetailException())
        whenever(employeesRemoteDataSource.fetchEmployeeDetail(ANY_EMPLOYEE_DETAIL_ID)).thenReturn(flowOf(resultFailure))

        val result = employeesRepository.fetchEmployeeDetail(ANY_EMPLOYEE_DETAIL_ID).lastOrNull()

        verify(employeesRemoteDataSource).fetchEmployeeDetail(ANY_EMPLOYEE_DETAIL_ID)
        assertThatIsInstanceOf<DataException.EmployeesDetailException>(result?.exceptionOrNull())
    }
}
