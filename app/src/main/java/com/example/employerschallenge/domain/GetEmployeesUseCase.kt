package com.example.employerschallenge.domain

import com.example.employerschallenge.data.EmployeesRepository
import javax.inject.Inject

class GetEmployeesUseCase @Inject constructor(private val employeesRepository: EmployeesRepository) {

    fun fetchEmployees() = employeesRepository.fetchEmployees()
}
