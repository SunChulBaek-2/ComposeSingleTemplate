package com.example.composetemplate.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun Tab1Screen() {
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.Red)) {}
}