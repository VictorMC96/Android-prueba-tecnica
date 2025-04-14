package com.example.employerschallenge.ui.employees

import com.example.employerschallenge.domain.model.Employee

fun givenEmployees() = buildList {
    for (i in 1..20 step 2) {
        add(givenEmployee(i))
        add(givenEmployeeSecond(i + 1))
    }
}

fun givenEmployee(id: Int = 1) = Employee(
    id = id,
    name = "Tiger Nixon",
    salary = 320800,
    age = 61,
    profileImage = ""
)

fun givenEmployeeSecond(id: Int = 2) = Employee(
    id = id,
    name = "Garrett Winters",
    salary = 170750,
    age = 63,
    profileImage = ""
)
