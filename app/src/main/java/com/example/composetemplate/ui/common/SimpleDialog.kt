package com.example.composetemplate.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun SimpleDialog(
    title: String? = null,
    message: String = "",
    okText: String = "확인",
    cancelText: String? = null,
    onDismissRequest: () -> Unit = { },
    onOkClick: () -> Unit = { },
    onCancelClick: () -> Unit = { }
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0x33000000))
    ) {
        AlertDialog(modifier = Modifier.align(Alignment.Center),
            onDismissRequest = onDismissRequest,
            title = if (title != null) {
                { Text(text = title) }
            } else null,
            text = { Text(text = message) },
            confirmButton = {
                TextButton(onClick = onOkClick) {
                    Text(text = okText)
                }
            }, dismissButton = if (cancelText != null) {
                {
                    TextButton(onClick = onCancelClick) {
                        Text(text = cancelText)
                    }
                }
            } else null)
    }
}