package com.example.employerschallenge.data.datasource.remote

import com.example.employerschallenge.data.datasource.exception.DataException
import com.example.employerschallenge.data.datasource.remote.retrofit.EmployeesServiceRetrofit
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class EmployeesRemoteDataSource @Inject constructor(private val employeesServiceRetrofit: EmployeesServiceRetrofit) {

    fun fetchEmployees() = flow {
        try {
            val employeesResponse = employeesServiceRetrofit.fetchEmployees()
            emit(Result.success(employeesResponse))
        } catch (exception: Exception) {
            exception.printStackTrace()
            emit(Result.failure(DataException.EmployeesException()))
        }
    }

    fun fetchEmployeeDetail(employeeId: Int) = flow {
        try {
            val employeeDetailResponse = employeesServiceRetrofit.fetchEmployeeDetail(employeeId)
            emit(Result.success(employeeDetailResponse))
        } catch (exception: Exception) {
            exception.printStackTrace()
            emit(Result .failure(DataException.EmployeesDetailException()))
        }
    }
}
