package com.example.composetemplate.ui.home.tab4

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.composetemplate.BuildConfig
import com.example.composetemplate.event.NavItemReselectEvent
import com.example.composetemplate.util.EventBus

@Composable
fun Tab4Screen(
    route: String,
    viewModel: Tab4ViewModel = hiltViewModel(),
    showSnackbar: (String) -> Unit,
    navigate: (String) -> Unit
) {
    val uiState = viewModel.uiState
    val reselectEvent by EventBus.subscribe<NavItemReselectEvent>().collectAsState(NavItemReselectEvent())

    LaunchedEffect(true) {
        viewModel.init()
    }

    LaunchedEffect(reselectEvent) {
        if (reselectEvent.route == route) {
            // TODO : 탭 재선택 시 동작 (ex. 최상단 스크롤)
            showSnackbar("$route 리셀렉")
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Blue.copy(0.3f))
    ) {
        Column(Modifier.align(Alignment.Center)) {
            androidx.compose.material3.Button(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                onClick = { showSnackbar("$route 클릭") }
            ) {
                androidx.compose.material3.Text(text = uiState.text,)
            }
            androidx.compose.material3.Text(text = "App ID : ${BuildConfig.APPLICATION_ID}")
            androidx.compose.material3.Text(text = "Version Code = ${BuildConfig.VERSION_CODE}")
            androidx.compose.material3.Text(text = "Version Name = ${BuildConfig.VERSION_NAME}")
        }
    }
}