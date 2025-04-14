package com.example.employerschallenge.ui.employees

import com.example.employerschallenge.domain.model.Employee

data class EmployeesUiState(
    val isLoading: Boolean = false,
    val employees: List<Employee>? = null,
    val error: Throwable? = null
)
