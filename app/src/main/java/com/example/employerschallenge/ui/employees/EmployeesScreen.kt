import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.twotone.Person
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.example.employerschallenge.R
import com.example.employerschallenge.core.extensions.empty
import com.example.employerschallenge.data.datasource.exception.DataException
import com.example.employerschallenge.domain.model.Employee
import com.example.employerschallenge.ui.employees.EmployeesViewModel
import com.example.employerschallenge.ui.employees.givenEmployee
import com.example.employerschallenge.ui.employees.givenEmployees
import com.example.employerschallenge.ui.theme.EmployeesChallengeTheme
import com.example.employerschallenge.ui.views.CircularProgressIndicatorFixMax
import com.example.employerschallenge.ui.views.EmployeesErrorScreen
import kotlinx.coroutines.flow.collectLatest

@Composable
fun EmployeesScreen(
    viewModel: EmployeesViewModel = hiltViewModel(),
    openEmployeeDetail: (id: Int) -> Unit
) {
    val uiState = viewModel.employeesUiState.collectAsStateWithLifecycle()
    val navigateToEmployeesDetailEvent = viewModel.navigateToEmployeeDetailEvent

    LaunchedEffect(Unit) {
        viewModel.getEmployees()
    }

    LaunchedEffect(navigateToEmployeesDetailEvent) {
        viewModel.navigateToEmployeeDetailEvent.collectLatest {
            openEmployeeDetail(it)
        }
    }

    EmployeesContent(
        isLoading = uiState.value.isLoading,
        employees = uiState.value.employees,
        error = uiState.value.error,
        listState = viewModel.listState,
        onRetry = viewModel::getEmployees,
        onEmployeeClick = viewModel::openEmployeeDetail
    )
}

@Composable
private fun EmployeesContent(
    listState: LazyListState = LazyListState(),
    isLoading: Boolean = false,
    employees: List<Employee>? = null,
    error: Throwable? = null,
    onRetry: () -> Unit = {},
    onEmployeeClick: (id: Int) -> Unit = {},
) {
    Scaffold(
        topBar = {
            EmployeesTopAppBar()
        },
        content = { paddingValues ->
            EmployeesList(
                modifier = Modifier.padding(paddingValues),
                listState = listState,
                employees = employees,
                onEmployeeClick = onEmployeeClick
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
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmployeesTopAppBar() {
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.primary),
        title = {
            Text(
                text = stringResource(id = R.string.employees),
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onPrimary
            )
        }
    )
}

@Composable
fun EmployeesList(
    modifier: Modifier = Modifier,
    listState: LazyListState,
    employees: List<Employee>?,
    onEmployeeClick: (id: Int) -> Unit = {}
) {
    if (employees == null) return

    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(vertical = 8.dp),
        state = listState
    ) {
        items(
            items = employees,
            key = { it.id }
        ) {
            EmployeeItem(
                employee = it,
                onEmployeeClick = onEmployeeClick
            )
        }
    }
}

@Composable
fun EmployeeItem(
    employee: Employee,
    onEmployeeClick: (id: Int) -> Unit = {}
) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        onClick = { onEmployeeClick(employee.id) }
    ) {
        Row(modifier = Modifier.padding(12.dp)) {
            AsyncImage(
                modifier = Modifier
                    .width(100.dp)
                    .height(130.dp)
                    .clip(RoundedCornerShape(12.dp)),
                contentDescription = String.empty(),
                contentScale = ContentScale.Crop,
                placeholder = rememberVectorPainter(image = Icons.TwoTone.Person),
                error = rememberVectorPainter(image = Icons.TwoTone.Person),
                model = employee.profileImage,
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(
                    text = stringResource(id = R.string.id, employee.id),
                    style = MaterialTheme.typography.titleLarge
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = stringResource(id = R.string.name, employee.name),
                    style = MaterialTheme.typography.titleSmall
                )
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = stringResource(id = R.string.salary, employee.salary),
                    style = MaterialTheme.typography.bodyMedium
                )
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = stringResource(id = R.string.age, employee.age),
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = 4,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun EmployeeItemPreview() {
    EmployeesChallengeTheme {
        EmployeeItem(
            employee = givenEmployee()
        )
    }
}

@Preview(showBackground = true)
@Composable
fun EmployeesContentUiStateLoadingPreview() {
    EmployeesChallengeTheme {
        EmployeesContent(
            isLoading = true
        )
    }
}

@Preview(showBackground = true)
@Composable
fun EmployeesContentUiStateSuccessPreview() {
    EmployeesChallengeTheme {
        EmployeesContent(
            employees = givenEmployees(),
        )
    }
}

@Preview(showBackground = true)
@Composable
fun EmployeesContentUiStateErrorPreview() {
    EmployeesChallengeTheme {
        EmployeesContent(
            error = DataException.EmployeesException()
        )
    }
}
