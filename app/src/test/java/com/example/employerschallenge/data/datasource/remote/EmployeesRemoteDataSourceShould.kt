package com.example.employerschallenge.data.datasource.remote

import com.example.employerschallenge.core.MockWebServerRule
import com.example.employerschallenge.core.assertThatEquals
import com.example.employerschallenge.core.assertThatIsInstanceOf
import com.example.employerschallenge.data.datasource.exception.DataException
import com.example.employerschallenge.data.datasource.remote.retrofit.EmployeesServiceRetrofit
import com.example.employerschallenge.fakedata.ANY_EMPLOYEES_ENDPOINT
import com.example.employerschallenge.fakedata.ANY_EMPLOYEE_DETAIL_ENDPOINT
import com.example.employerschallenge.fakedata.ANY_EMPLOYEE_DETAIL_ID
import com.example.employerschallenge.fakedata.givenEmployeeDetailResponseFakeData
import com.example.employerschallenge.fakedata.givenEmployeesResponseFakeData
import kotlinx.coroutines.flow.lastOrNull
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class EmployeesRemoteDataSourceShould {

    @get:Rule
    val webServerRule = MockWebServerRule()

    private lateinit var employeesRemoteDataSource: EmployeesRemoteDataSource

    @Before
    fun setup() {
        employeesRemoteDataSource = EmployeesRemoteDataSource(webServerRule.mockRetrofit().create(EmployeesServiceRetrofit::class.java))
    }

    @Test
    fun `Get EmployeesResponse data when fetchEmployees is success with empty query`(): Unit = runTest {
        val employeesResponse = givenEmployeesResponseFakeData()
        webServerRule.loadMockResponse(fileName = "employeesResponse.json")

        val result = employeesRemoteDataSource.fetchEmployees().lastOrNull()

        webServerRule.assertRequestMethod(path = ANY_EMPLOYEES_ENDPOINT, method = MockWebServerRule.GET)
        assertThatEquals(result?.getOrNull(), employeesResponse)
    }

    @Test
    fun `Get EmployeesException data when fetchEmployees is failure empty query`() = runTest {
        webServerRule.loadMockResponse(responseCode = 400)

        val result = employeesRemoteDataSource.fetchEmployees().lastOrNull()

        webServerRule.assertRequestMethod(path = ANY_EMPLOYEES_ENDPOINT, method = MockWebServerRule.GET)
        assertThatIsInstanceOf<DataException.EmployeesException>(result?.exceptionOrNull())
    }

    @Test
    fun `Get EmployeeDetailResponse data when fetchEmployeeDetail is success`(): Unit = runTest {
        val employeeDetailResponse = givenEmployeeDetailResponseFakeData()
        webServerRule.loadMockResponse(fileName = "employeeDetailResponse.json")

        val result = employeesRemoteDataSource.fetchEmployeeDetail(ANY_EMPLOYEE_DETAIL_ID).lastOrNull()

        webServerRule.assertRequestMethod(path = ANY_EMPLOYEE_DETAIL_ENDPOINT, method = MockWebServerRule.GET)
        assertThatEquals(result?.getOrNull(), employeeDetailResponse)
    }

    @Test
    fun `Get EmployeeDetailException data when fetchEmployeeDetail is failure`() = runTest {
        webServerRule.loadMockResponse(responseCode = 400)

        val result = employeesRemoteDataSource.fetchEmployeeDetail(ANY_EMPLOYEE_DETAIL_ID).lastOrNull()

        webServerRule.assertRequestMethod(path = ANY_EMPLOYEE_DETAIL_ENDPOINT, method = MockWebServerRule.GET)
        assertThatIsInstanceOf<DataException.EmployeesDetailException>(result?.exceptionOrNull())
    }
}
