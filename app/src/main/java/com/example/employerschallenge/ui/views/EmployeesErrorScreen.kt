package com.example.employerschallenge.ui.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.employerschallenge.R
import com.example.employerschallenge.core.extensions.empty
import com.example.employerschallenge.data.datasource.exception.DataException
import com.example.employerschallenge.ui.theme.EmployeesChallengeTheme

@Composable
fun EmployeesErrorScreen(
    modifier: Modifier = Modifier,
    error: Throwable? = null,
    onRetry: () -> Unit = {}
) {
    if (error == null) return
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(32.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = Icons.Default.Warning,
            contentDescription = String.empty(),
            tint = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.size(72.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = stringResource(R.string.something_went_wrong),
            style = MaterialTheme.typography.titleMedium,
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = getErrorMessage(error),
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            modifier = Modifier.width(132.dp),
            shape = RoundedCornerShape(8.dp),
            onClick = onRetry
        ) {
            Text(
                text = stringResource(R.string.retry),
                style = MaterialTheme.typography.titleSmall,
            )
        }
    }
}

@Composable
private fun getErrorMessage(error: Throwable) = when (error) {
    is DataException.EmployeesException -> stringResource(R.string.employees_load_error)
    is DataException.EmployeesDetailException -> stringResource(R.string.employee_detail_load_error)
    else -> stringResource(R.string.default_error)
}

@Preview(showBackground = true)
@Composable
fun EmployeesErrorScreenPreview() {
    EmployeesChallengeTheme {
        EmployeesErrorScreen(
            error = DataException.EmployeesException()
        )
    }
}
