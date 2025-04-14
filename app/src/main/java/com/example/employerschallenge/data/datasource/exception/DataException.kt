package com.example.employerschallenge.data.datasource.exception

sealed class DataException(message: String) : Exception(message) {
    data class EmployeesException(override val message: String = "Some error happened with the get Employees Data") : Exception(message)
    data class EmployeesDetailException(override val message: String = "Some error happened with the get Employee Data") : Exception(message)
}
