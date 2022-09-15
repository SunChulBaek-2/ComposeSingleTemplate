package com.example.composetemplate.ui.tab4

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun Tab4Screen(
    viewModel: Tab4ViewModel = viewModel()
) {
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.Blue)) {}
}