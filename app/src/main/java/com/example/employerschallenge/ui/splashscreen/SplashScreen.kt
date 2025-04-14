package com.example.employerschallenge.ui.splashscreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.employerschallenge.R
import com.example.employerschallenge.core.extensions.empty
import com.example.employerschallenge.ui.theme.EmployeesChallengeTheme
import kotlinx.coroutines.delay

private const val DELAY_SPLASH = 3000L

@Composable
fun SplashScreen(
    onSplashFinished: () -> Unit = {}
) {
    LaunchedEffect(Unit) {
        delay(DELAY_SPLASH)
        onSplashFinished()
    }

    Scaffold(content = { paddingValues ->
        Column(
            modifier = Modifier.fillMaxSize().padding(paddingValues),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                modifier = Modifier.size(150.dp),
                contentDescription = String.empty(),
                painter = rememberAsyncImagePainter(R.mipmap.ic_launcher),
            )
            Spacer(modifier = Modifier.height(32.dp))
            Text(
                text = stringResource(R.string.splash_screen),
                style = MaterialTheme.typography.titleLarge
            )
        }
    })
}

@Preview(showBackground = true)
@Composable
fun SplashScreenPreview() {
    EmployeesChallengeTheme {
        SplashScreen()
    }
}
