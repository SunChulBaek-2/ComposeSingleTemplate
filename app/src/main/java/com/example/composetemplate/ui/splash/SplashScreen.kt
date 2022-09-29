package com.example.composetemplate.ui.splash

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.composetemplate.R
import com.example.composetemplate.ui.theme.ComposeTemplateTheme
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    viewModel: SplashViewModel = viewModel(),
    onTimeout: () -> Unit
) {
    BackHandler {
        // nothing to do
    }
    // 일정시간 이후 다음화면으로 이동
    val currentOnTimeout by rememberUpdatedState(onTimeout)
    LaunchedEffect(true) {
        viewModel.init(R.string.app_name)
        delay(3000)
        currentOnTimeout()
    }

    val uiState = viewModel.uiState
    val backgroundColor = MaterialTheme.colorScheme.primary
    val contentColor = contentColorFor(backgroundColor)
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
    ) {
        Text(
            modifier = Modifier.align(Alignment.Center),
            text = uiState.stringId?.let { stringResource(uiState.stringId) } ?: "",
            color = contentColor,
            style = MaterialTheme.typography.titleLarge
        )
    }
}