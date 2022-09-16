package com.example.composetemplate

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.composetemplate.ui.home.HomeScreen
import com.example.composetemplate.ui.theme.ComposeTemplateTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeTemplateTheme {
                HomeScreen()
            }
        }
    }
}