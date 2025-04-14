package com.example.employerschallenge.domain

import com.example.employerschallenge.data.EmployeesRepository
import javax.inject.Inject

class GetEmployeeDetailUseCase @Inject constructor(private val employeesRepository: EmployeesRepository) {

    fun fetchEmployeeDetail(employeeId: Int) = employeesRepository.fetchEmployeeDetail(employeeId)
}
