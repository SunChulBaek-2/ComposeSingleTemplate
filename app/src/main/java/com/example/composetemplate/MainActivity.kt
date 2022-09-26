package com.example.composetemplate

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.composetemplate.ui.MainScreen
import com.example.composetemplate.ui.theme.ComposeTemplateTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeTemplateTheme {
                MainScreen(
                    showToast = { text ->
                        Toast.makeText(this, text, Toast.LENGTH_SHORT).apply {
                            this.show()
                        }
                    },
                    onBack = { this.finishAffinity() }
                )
            }
        }
    }
}