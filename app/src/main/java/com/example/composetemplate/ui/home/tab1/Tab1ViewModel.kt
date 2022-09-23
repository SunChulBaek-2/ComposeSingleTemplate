package com.example.composetemplate.ui.home.tab1

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.composetemplate.data.Photo
import com.example.composetemplate.domain.GetPhotosParam
import com.example.composetemplate.domain.GetPhotosUseCase
import com.example.composetemplate.util.onMain
import dagger.hilt.android.lifecycle.HiltViewModel
import timber.log.Timber
import javax.inject.Inject

data class Tab1UiState(
    val isLoading: Boolean,
    val isError: Boolean,
    val photos: List<Photo>
)

@HiltViewModel
class Tab1ViewModel @Inject constructor(
    private val getPhotosUseCase: GetPhotosUseCase
) : ViewModel() {

    var uiState by mutableStateOf(Tab1UiState(false, false, listOf()))
        private set

    fun init() = onMain {
        uiState = Tab1UiState( true, false, listOf())
        getPhotosUseCase(GetPhotosParam()).collect { result ->
            when {
                result.isSuccess -> {
                    Timber.d("Get Photo success")
                    uiState = Tab1UiState(false, false, result.getOrDefault(listOf()))
                }
                result.isFailure -> {
                    Timber.d("Get Photo failed")
                    uiState = Tab1UiState(false,  true, result.getOrDefault(listOf()))
                }
            }
        }
    }
}