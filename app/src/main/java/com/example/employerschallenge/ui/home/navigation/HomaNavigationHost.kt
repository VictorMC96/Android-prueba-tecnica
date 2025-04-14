package com.example.employerschallenge.ui.home.navigation

import EmployeesScreen
import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.employerschallenge.core.extensions.orDefault
import com.example.employerschallenge.ui.home.navigation.HomeRoute.Companion.EMPLOYEE_ID_ARGUMENT
import com.example.employerschallenge.ui.home.navigation.HomeRoute.EmployeeDetail
import com.example.employerschallenge.ui.home.navigation.HomeRoute.Employees
import com.example.employerschallenge.ui.employee.detail.EmployeeDetailScreen
import com.example.employerschallenge.ui.home.navigation.HomeRoute.Login
import com.example.employerschallenge.ui.home.navigation.HomeRoute.SplashScreen
import com.example.employerschallenge.ui.login.LoginScreen
import com.example.employerschallenge.ui.splashscreen.SplashScreen

@Composable
fun JarsNavHost(navHostController: NavHostController) {
    NavHost(
        navController = navHostController,
        startDestination = SplashScreen.route
    ) {
        splashScreenNav(navController = navHostController)
        loginNav(navController = navHostController)
        employeesNav(navController = navHostController)
        employeeDetailNav(navController = navHostController)
    }
}

private fun NavGraphBuilder.splashScreenNav(navController: NavHostController) {
    composable(route = SplashScreen.route) {
        SplashScreen(
            openLogin = {
                navController.navigate(Login.route) {
                    popUpTo(SplashScreen.route) {
                        inclusive = true
                    }
                }
            }
        )
    }
}

private fun NavGraphBuilder.loginNav(navController: NavHostController) {
    composable(route = Login.route) {
        LoginScreen(
            openEmployees = {
                navController.navigate(Employees.route) {
                    popUpTo(Login.route) {
                        inclusive = true
                    }
                }
            }
        )
    }
}

private fun NavGraphBuilder.employeesNav(navController: NavHostController) {
    composable(route = Employees.route) {
        EmployeesScreen(
            openEmployeeDetail = {
                navController.navigate(EmployeeDetail.createRoute(it))
            }
        )
    }
}

private fun NavGraphBuilder.employeeDetailNav(navController: NavHostController) {
    composable(
        route = EmployeeDetail.route,
        arguments = EmployeeDetail.getArguments()
    ) { navBackStackEntry ->
        val employeeId = navBackStackEntry.arguments?.getInt(EMPLOYEE_ID_ARGUMENT).orDefault()

        EmployeeDetailScreen(
            employeeId = employeeId,
            onBackClick = { navController.navigateUp() }
        )
    }
}
