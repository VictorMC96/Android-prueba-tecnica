package com.example.employerschallenge.ui.employee.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.twotone.Person
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.example.employerschallenge.R
import com.example.employerschallenge.core.extensions.empty
import com.example.employerschallenge.data.datasource.exception.DataException.EmployeesDetailException
import com.example.employerschallenge.domain.model.Employee
import com.example.employerschallenge.ui.employees.givenEmployee
import com.example.employerschallenge.ui.theme.EmployeesChallengeTheme
import com.example.employerschallenge.ui.views.CircularProgressIndicatorFixMax
import com.example.employerschallenge.ui.views.EmployeesErrorScreen
import com.example.employerschallenge.ui.views.TextWithIcon

@Composable
fun EmployeeDetailScreen(
    viewModel: EmployeeDetailViewModel = hiltViewModel(),
    employeeId: Int,
    onBackClick: () -> Unit
) {
    val uiState = viewModel.employeeDetailUiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.getEmployeeDetail(employeeId)
    }

    EmployeeDetailContent(
        isLoading = uiState.value.isLoading,
        employee = uiState.value.employee,
        error = uiState.value.error,
        onBackClick = onBackClick,
        onRetry = { viewModel.getEmployeeDetail(employeeId) }
    )
}

@Composable
fun EmployeeDetailContent(
    isLoading: Boolean = false,
    employee: Employee? = null,
    error: Throwable? = null,
    onBackClick: () -> Unit = {},
    onRetry: () -> Unit = {}
) {
    Scaffold(
        topBar = { EmployeeDetailTopAppBar(onBackClick = onBackClick) },
        content =
            { paddingValues ->
                EmployeeDetail(
                    modifier = Modifier.padding(paddingValues),
                    employee = employee
                )

                EmployeesErrorScreen(
                    modifier = Modifier.padding(paddingValues),
                    error = error,
                    onRetry = onRetry
                )

                CircularProgressIndicatorFixMax(
                    modifier = Modifier.padding(paddingValues),
                    isVisible = isLoading
                )
            })
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmployeeDetailTopAppBar(onBackClick: () -> Unit = {}) {
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.primary),
        title = {
            Text(
                text = stringResource(id = R.string.detail),
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onPrimary
            )
        },
        navigationIcon = {
            IconButton(onClick = onBackClick) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    tint = MaterialTheme.colorScheme.onPrimary,
                    contentDescription = String.empty()
                )
            }
        }
    )
}

@Composable
private fun EmployeeDetail(
    modifier: Modifier = Modifier,
    employee: Employee?
) {
    if (employee == null) return
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            model = employee.profileImage,
            contentDescription = String.empty(),
            modifier = Modifier
                .width(150.dp)
                .height(250.dp)
                .clip(RoundedCornerShape(12.dp)),
            contentScale = ContentScale.Crop,
            placeholder = rememberVectorPainter(image = Icons.TwoTone.Person),
            error = rememberVectorPainter(image = Icons.TwoTone.Person),
        )

        Spacer(modifier = Modifier.width(24.dp))

        TextWithIcon(
            style = MaterialTheme.typography.titleLarge,
            text = stringResource(id = R.string.id, employee.id),
        )

        TextWithIcon(
            style = MaterialTheme.typography.titleMedium,
            icon = Icons.Default.Face,
            text = stringResource(id = R.string.name, employee.name)
        )

        TextWithIcon(
            style = MaterialTheme.typography.bodyLarge,
            color = getSalaryColor(employee.salary),
            icon = Icons.Default.CheckCircle,
            text = stringResource(id = R.string.salary, employee.salary)
        )

        TextWithIcon(
            style = MaterialTheme.typography.bodyLarge,
            color = getAgeColor(employee.age),
            icon = Icons.Default.DateRange,
            text = stringResource(id = R.string.age, employee.age)
        )
    }
}

private fun getSalaryColor(salary: Int) = if (salary > 10000) Color.Green else Color.Red

private fun getAgeColor(age: Int) = if (age in 26..34) Color.Green else Color.Red

@Preview(showBackground = true)
@Composable
fun EmployeeDetailContentUiStateLoadingPreview() {
    EmployeesChallengeTheme {
        EmployeeDetailContent(
            isLoading = true
        )
    }
}

@Preview(showBackground = true)
@Composable
fun EmployeeDetailContentUiStateSuccessPreview() {
    EmployeesChallengeTheme {
        EmployeeDetailContent(
            employee = givenEmployee()
        )
    }
}

@Preview(showBackground = true)
@Composable
fun EmployeeDetailContentUiStateErrorPreview() {
    EmployeesChallengeTheme {
        EmployeeDetailContent(
            error = EmployeesDetailException()
        )
    }
}
