package com.example.composetemplate.ui.splash

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
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
        viewModel.init()
        delay(3000)
        currentOnTimeout()
    }
    val uiState = viewModel.uiState
    Box(modifier = Modifier.fillMaxSize()) {
        Text(modifier = Modifier.align(Alignment.Center), text = uiState.text)
    }
}