package com.example.employerschallenge.data.datasource.model

import com.example.employerschallenge.core.extensions.orDefault
import com.example.employerschallenge.domain.model.Employee
import com.google.gson.annotations.SerializedName

data class EmployeesResponse(
    @SerializedName("status") val status: String?,
    @SerializedName("data") val data: List<EmployeeResponse>?,
    @SerializedName("message") val message: String?
)

data class EmployeeDetailResponse(
    @SerializedName("status") val status: String?,
    @SerializedName("data") val data: EmployeeResponse?,
    @SerializedName("message") val message: String?
)

data class EmployeeResponse(
    @SerializedName("id") val id: Int?,
    @SerializedName("employee_name") val employeeName: String?,
    @SerializedName("employee_salary") val employeeSalary: Int?,
    @SerializedName("employee_age") val employeeAge: Int?,
    @SerializedName("profile_image") val profileImage: String?
)

fun Result<EmployeesResponse>.toEmployees() = map { it.toEmployees() }

private fun EmployeesResponse.toEmployees() = this.data?.map { it.toEmployee() }.orEmpty()

fun Result<EmployeeDetailResponse>.toEmployee() = map { it.toEmployees() }

private fun EmployeeDetailResponse.toEmployees() = this.data.toEmployee()

private fun EmployeeResponse?.toEmployee() = Employee(
    id = this?.id.orDefault(),
    name = this?.employeeName.orEmpty(),
    salary = this?.employeeSalary.orDefault(),
    age = this?.employeeAge.orDefault(),
    profileImage = this?.profileImage.orEmpty()
)

