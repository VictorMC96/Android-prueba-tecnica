package com.example.employerschallenge.data

import com.example.employerschallenge.data.datasource.model.toEmployee
import com.example.employerschallenge.data.datasource.model.toEmployees
import com.example.employerschallenge.data.datasource.remote.EmployeesRemoteDataSource
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class EmployeesRepository @Inject constructor(private val employeesRemoteDataSource: EmployeesRemoteDataSource) {

    fun fetchEmployees() = employeesRemoteDataSource.fetchEmployees().map { it.toEmployees() }

    fun fetchEmployeeDetail(employeeId: Int) = employeesRemoteDataSource.fetchEmployeeDetail(employeeId).map { it.toEmployee() }
}
