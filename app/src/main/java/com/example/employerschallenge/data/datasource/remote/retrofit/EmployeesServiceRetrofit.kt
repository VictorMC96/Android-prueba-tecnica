package com.example.employerschallenge.data.datasource.remote.retrofit

import com.example.employerschallenge.data.datasource.model.EmployeeDetailResponse
import com.example.employerschallenge.data.datasource.model.EmployeesResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface EmployeesServiceRetrofit {

    @GET(EMPLOYEES_ENDPOINT)
    suspend fun fetchEmployees(): EmployeesResponse

    @GET(EMPLOYEE_DETAIL_ENDPOINT)
    suspend fun fetchEmployeeDetail(@Path(EMPLOYEE_ID) employeeId: Int): EmployeeDetailResponse
}
