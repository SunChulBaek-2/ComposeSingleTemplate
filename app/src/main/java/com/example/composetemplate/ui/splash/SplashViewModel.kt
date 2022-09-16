package com.example.composetemplate.ui.splash

import androidx.annotation.StringRes
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

data class SplashUiState(
    @StringRes val stringId: Int? = null
)

@HiltViewModel
class SplashViewModel @Inject constructor() : ViewModel() {

    var uiState by mutableStateOf(SplashUiState())
        private set

    fun init(stringId: Int) {
        uiState = SplashUiState(stringId)
    }
}