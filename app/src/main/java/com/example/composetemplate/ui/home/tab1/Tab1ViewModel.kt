package com.example.composetemplate.ui.home.tab1

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import timber.log.Timber
import javax.inject.Inject

data class Tab1UiState(
    val text: String
)

@HiltViewModel
class Tab1ViewModel @Inject constructor() : ViewModel() {

    var uiState by mutableStateOf(Tab1UiState(""))
        private set

    fun init() {
        Timber.d("Tab1ViewModel.init()")
        uiState = Tab1UiState("Tab1")
    }
}