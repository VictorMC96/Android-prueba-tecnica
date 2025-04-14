package com.example.employerschallenge.ui.home.navigation

import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.example.employerschallenge.core.extensions.DEFAULT_VALUE

sealed class HomeRoute(val route: String) {
    data object SplashScreen : HomeRoute("splashScreen")
    data object Employees : HomeRoute("employees")
    data object EmployeeDetail : HomeRoute("employeeDetail/{$EMPLOYEE_ID_ARGUMENT}"){

        fun createRoute(employeeId: Int): String {
            return route.replace("{$EMPLOYEE_ID_ARGUMENT}", employeeId.toString())
        }

        fun getArguments(defaultId: Int = DEFAULT_VALUE) = listOf(
            navArgument(EMPLOYEE_ID_ARGUMENT) {
                type = NavType.IntType
                defaultValue = defaultId
            }
        )
    }

    companion object{
        const val EMPLOYEE_ID_ARGUMENT = "employeeId"
    }
}
