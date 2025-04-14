package com.example.employerschallenge.ui.login

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.rememberAsyncImagePainter
import com.example.employerschallenge.R
import com.example.employerschallenge.ui.theme.EmployeesChallengeTheme
import com.example.employerschallenge.ui.views.CircularProgressIndicatorFixMax
import kotlinx.coroutines.flow.collectLatest

@Composable
fun LoginScreen(
    viewModel: LoginViewModel = hiltViewModel(),
    openEmployees: () -> Unit
) {
    val loadingUiState = viewModel.loadingUiState.collectAsStateWithLifecycle()
    val validUserEvent = viewModel.validUseEvent
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        validUserEvent.collectLatest {
            if (it) {
                openEmployees()
            } else {
                Toast.makeText(context, context.getString(R.string.invalid_user), Toast.LENGTH_SHORT).show()
            }
        }
    }
    LoginContent(
        isLoading = loadingUiState.value,
        login = viewModel::login
    )
}

@Composable
fun LoginContent(
    isLoading: Boolean = false,
    login: (String) -> Unit = {}
) {
    var userId by remember { mutableStateOf("") }
    Scaffold(content = { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = rememberAsyncImagePainter(R.mipmap.ic_launcher),
                contentDescription = null,
                modifier = Modifier.size(120.dp)
            )

            Spacer(modifier = Modifier.height(32.dp))

            Text(
                text = stringResource(R.string.enter_your_user_id),
                fontSize = 20.sp,
                color = androidx.compose.ui.graphics.Color.Unspecified
            )

            Spacer(modifier = Modifier.height(24.dp))

            OutlinedTextField(
                textStyle = MaterialTheme.typography.titleSmall,
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                value = userId,
                placeholder = {
                    Text(
                        style = MaterialTheme.typography.bodyLarge,
                        text = stringResource(R.string.input_text)
                    )
                },
                onValueChange = { userId = it }
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                modifier = Modifier
                    .height(48.dp)
                    .width(160.dp),
                shape = RoundedCornerShape(8.dp),
                onClick = { login(userId) }
            ) {
                Text(
                    text = stringResource(R.string.login),
                    style = MaterialTheme.typography.titleMedium,
                )
            }
        }

        CircularProgressIndicatorFixMax(
            modifier = Modifier.padding(paddingValues),
            isVisible = isLoading
        )
    })
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    EmployeesChallengeTheme {
        LoginContent()
    }
}
