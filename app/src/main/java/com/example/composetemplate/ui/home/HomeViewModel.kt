package com.example.composetemplate.ui.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * @param reselect 재선택된 탭의 route
 * @param timeStamp 같은 이벤트가 연속으로 발생 시, 이벤트 구분을 위함 (ex. 탭1 재선택 -> 탭1 재선택)
 */
data class HomeUiState(
    val reselect: String = "",
    val timeStamp: Long = System.currentTimeMillis()
)

@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel() {

    var uiState by mutableStateOf(HomeUiState())
        private set

    fun reselect(route: String) {
        uiState = HomeUiState(route)
    }
}