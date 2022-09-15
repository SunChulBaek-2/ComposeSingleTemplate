package com.example.composetemplate.ui.tab1

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun Tab1Screen(
    viewModel: Tab1ViewModel = viewModel()
) {
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.Red)) {}
}