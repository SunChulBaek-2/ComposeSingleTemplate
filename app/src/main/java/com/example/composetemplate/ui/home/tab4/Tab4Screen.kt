package com.example.composetemplate.ui.home.tab4

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.composetemplate.ui.home.HomeViewModel
import timber.log.Timber

@Composable
fun Tab4Screen(
    homeViewModel: HomeViewModel,
    viewModel: Tab4ViewModel = hiltViewModel(),
    showSnackbar: (String) -> Unit,
    navigate: (String) -> Unit,
    onDispose: (String) -> Unit
) {
    val uiState = viewModel.uiState

    LaunchedEffect(true) {
        viewModel.init()
    }

    LaunchedEffect(homeViewModel.uiState) {
        if (homeViewModel.uiState.reselect == "tab4") {
            Timber.d("[템플릿] Tab4Screen reselected")
            // TODO : 탭 재선택 시 동작 (ex. 최상단 스크롤)
            showSnackbar("Tab4Screen 리셀렉")
        }
    }

    DisposableEffect(true) {
        onDispose { onDispose.invoke("Tab4Screen") }
    }

    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.Blue.copy(0.3f))) {
        Button(
            modifier = Modifier.align(Alignment.Center),
            onClick = { showSnackbar("Tab4Screen 클릭") }
        ) {
            Text(uiState.text)
        }
    }
}