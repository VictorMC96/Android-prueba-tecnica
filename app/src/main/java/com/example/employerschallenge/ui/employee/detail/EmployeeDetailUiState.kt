package com.example.employerschallenge.ui.employee.detail

import com.example.employerschallenge.domain.model.Employee

data class EmployeeDetailUiState(
    val isLoading: Boolean = false,
    val employee: Employee? = null,
    val error: Throwable? = null
)
