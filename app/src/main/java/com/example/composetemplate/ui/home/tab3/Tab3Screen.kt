package com.example.composetemplate.ui.home.tab3

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
fun Tab3Screen(
    homeViewModel: HomeViewModel,
    viewModel: Tab3ViewModel = hiltViewModel(),
    showSnackbar: (String) -> Unit,
    navigate: (String) -> Unit
) {
    val uiState = viewModel.uiState

    LaunchedEffect(true) {
        viewModel.init()
    }

    LaunchedEffect(homeViewModel.uiState) {
        if (homeViewModel.uiState.reselect == "tab3") {
            Timber.d("[템플릿] Tab3 reselected")
            // TODO : 탭 재선택 시 동작 (ex. 최상단 스크롤)
            showSnackbar("Tab3 리셀렉")
        }
    }

    DisposableEffect(true) {
        onDispose {
            Timber.d("[템플릿] Tab3Screen.onDispose()")
            // 재선택 -> 다른탭 -> 해당탭 이동 시 다시 재선택된 것 처럼 동작하여 아래라인 필요
            homeViewModel.reselect("")
        }
    }

    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.Green.copy(0.3f))) {
        Button(
            modifier = Modifier.align(Alignment.Center),
            onClick = { showSnackbar("탭3 클릭") }
        ) {
            Text(uiState.text)
        }
    }
}