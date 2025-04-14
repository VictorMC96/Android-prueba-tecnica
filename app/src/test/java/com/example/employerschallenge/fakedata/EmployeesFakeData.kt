package com.example.employerschallenge.fakedata

import com.example.employerschallenge.data.datasource.model.EmployeeDetailResponse
import com.example.employerschallenge.data.datasource.model.EmployeeResponse
import com.example.employerschallenge.data.datasource.model.EmployeesResponse
import com.example.employerschallenge.domain.model.Employee

const val ANY_EMPLOYEES_ENDPOINT = "/employees"
const val ANY_EMPLOYEE_DETAIL_ID = 1
const val ANY_EMPLOYEE_DETAIL_ENDPOINT = "/employee/$ANY_EMPLOYEE_DETAIL_ID"
const val ANY_VALID_EMPLOYEE_ID = "1"
const val ANY_INVALID_EMPLOYEE_ID = "2"

fun givenEmployeesResponseFakeData() = EmployeesResponse(
    status = "success",
    data = listOf(givenEmployeeResponseFakeData(), givenEmployeeSecondResponseFakeData()),
    message = "Successfully! All records has been fetched."
)

fun givenEmployeeDetailResponseFakeData() = EmployeeDetailResponse(
    status = "success",
    data = givenEmployeeResponseFakeData(),
    message = "Successfully! Record has been fetched."
)

private fun givenEmployeeResponseFakeData() = EmployeeResponse(
    id = 1,
    employeeName = "Tiger Nixon",
    employeeSalary = 320800,
    employeeAge = 61,
    profileImage = ""
)

private fun givenEmployeeSecondResponseFakeData() = EmployeeResponse(
    id = 2,
    employeeName = "Garrett Winters",
    employeeSalary = 170750,
    employeeAge = 63,
    profileImage = ""
)

fun givenEmployeesFakeData() = listOf(
    givenEmployeeFakeData(),
    givenEmployeeSecondFakeData()
)

fun givenEmployeeFakeData() = Employee(
    id = 1,
    name = "Tiger Nixon",
    salary = 320800,
    age = 61,
    profileImage = ""
)

fun givenEmployeeSecondFakeData() = Employee(
    id = 2,
    name = "Garrett Winters",
    salary = 170750,
    age = 63,
    profileImage = ""
)
