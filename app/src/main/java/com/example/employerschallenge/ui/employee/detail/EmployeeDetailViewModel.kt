package com.example.employerschallenge.ui.employee.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.employerschallenge.core.coroutines.CoroutinesDispatchers
import com.example.employerschallenge.domain.GetEmployeeDetailUseCase
import com.example.employerschallenge.domain.model.Employee
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class EmployeeDetailViewModel @Inject constructor(
    private val getEmployeeDetailUseCase: GetEmployeeDetailUseCase,
    private val coroutinesDispatchers: CoroutinesDispatchers
) : ViewModel() {

    private val _employeeDetailUiState = MutableStateFlow(EmployeeDetailUiState())
    val employeeDetailUiState = _employeeDetailUiState.asStateFlow()

    fun getEmployeeDetail(employeeId: Int) {
        if (employeeDetailUiState.value.employee != null || employeeDetailUiState.value.isLoading) return
        viewModelScope.launch(coroutinesDispatchers.io) {
            emitEmployeeDetailUiState(isLoading = true)
            getEmployeeDetailUseCase.fetchEmployeeDetail(employeeId).collect {
                getEmployeeDetailSuccess(it)
                getEmployeeDetailError(it)
            }
        }
    }

    private fun getEmployeeDetailSuccess(result: Result<Employee>) = result.onSuccess {
        emitEmployeeDetailUiState(employee = it)
    }

    private fun getEmployeeDetailError(result: Result<Employee>) = result.onFailure {
        it.printStackTrace()
        emitEmployeeDetailUiState(error = it)
    }

    private fun emitEmployeeDetailUiState(
        isLoading: Boolean = false,
        employee: Employee? = null,
        error: Throwable? = null
    ) {
        _employeeDetailUiState.value = EmployeeDetailUiState(isLoading = isLoading, employee = employee, error = error)
    }
}
