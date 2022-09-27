package com.example.composetemplate.ui.home.tab2

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.composetemplate.ui.home.HomeViewModel
import timber.log.Timber

@Composable
fun Tab2Screen(
    homeViewModel: HomeViewModel,
    viewModel: Tab2ViewModel = hiltViewModel(),
    showSnackbar: (String) -> Unit,
    navigate: (String) -> Unit
) {
    val uiState = viewModel.uiState

    LaunchedEffect(true) {
        viewModel.init()
    }

    LaunchedEffect(homeViewModel.uiState) {
        if (homeViewModel.uiState.reselect == "tab2") {
            Timber.d("[템플릿] Tab2 reselected")
            // TODO : 탭 재선택 시 동작 (ex. 최상단 스크롤)
            showSnackbar("Tab2 리셀렉")
        }
    }

    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.Yellow.copy(0.3f))) {
        Button(
            modifier = Modifier.align(Alignment.Center),
            onClick = { showSnackbar("탭2 클릭") }
        ) {
            Text(uiState.text)
        }
    }
}