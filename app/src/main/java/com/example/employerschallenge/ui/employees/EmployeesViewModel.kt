package com.example.employerschallenge.ui.employees

import androidx.compose.foundation.lazy.LazyListState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.employerschallenge.core.coroutines.CoroutinesDispatchers
import com.example.employerschallenge.domain.GetEmployeesUseCase
import com.example.employerschallenge.domain.model.Employee
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

@HiltViewModel
class EmployeesViewModel @Inject constructor(
    private val getEmployeesUseCase: GetEmployeesUseCase,
    private val coroutinesDispatchers: CoroutinesDispatchers
) : ViewModel() {

    val listState = LazyListState()

    private val _employeesUiState = MutableStateFlow(EmployeesUiState())
    val employeesUiState = _employeesUiState.asStateFlow()

    private val _navigateToEmployeeDetailEvent = Channel<Int>()
    val navigateToEmployeeDetailEvent = _navigateToEmployeeDetailEvent.receiveAsFlow()

    fun getEmployees() {
        if (employeesUiState.value.employees != null || employeesUiState.value.isLoading) return
        viewModelScope.launch(coroutinesDispatchers.io) {
            emitEmployeesUiState(isLoading = true)
            getEmployeesUseCase.fetchEmployees().collect {
                getEmployeesSuccess(it)
                getEmployeesError(it)
            }
        }
    }

    private fun getEmployeesSuccess(result: Result<List<Employee>>) = result.onSuccess {
        viewModelScope.launch { listState.scrollToItem(0) }
        emitEmployeesUiState(employees = it)
    }

    private fun getEmployeesError(result: Result<List<Employee>>) = result.onFailure {
        emitEmployeesUiState(employees = null, error = it)
        it.printStackTrace()
    }

    private fun emitEmployeesUiState(
        isLoading: Boolean = false,
        employees: List<Employee>? = null,
        error: Throwable? = null
    ) {
        _employeesUiState.value = EmployeesUiState(isLoading = isLoading, employees = employees, error = error)
    }

    fun openEmployeeDetail(employeeId: Int) = viewModelScope.launch(coroutinesDispatchers.main) {
        _navigateToEmployeeDetailEvent.send(employeeId)
    }
}
