package com.example.composetemplate.ui.home.tab1

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.SubcomposeAsyncImage
import com.example.composetemplate.data.model.Photo
import com.example.composetemplate.ui.common.ErrorScreen
import com.example.composetemplate.ui.common.LoadingScreen
import com.example.composetemplate.ui.home.HomeViewModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import timber.log.Timber

@Composable
fun Tab1Screen(
    homeViewModel: HomeViewModel,
    viewModel: Tab1ViewModel = hiltViewModel(),
    showSnackbar: (String) -> Unit,
    navigate: (String) -> Unit
) {
    val uiState = viewModel.uiState
    val listState = rememberLazyListState()

    LaunchedEffect(true) {
        viewModel.init()
    }

    LaunchedEffect(homeViewModel.uiState) {
        if (homeViewModel.uiState.reselect == "tab1") {
            Timber.d("[템플릿] Tab1 reselected")
            // TODO : 탭 재선택 시 동작 (ex. 최상단 스크롤)
            listState.animateScrollToItem(0)
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
    ) {
        if (uiState.isLoading) {
            LoadingScreen()
        } else if (uiState.isError) {
            ErrorScreen()
        } else {
            SwipeRefresh(
                state = uiState.swipeRefreshState,
                onRefresh = { viewModel.init(forced = true) }
            ) {
                LazyColumn(modifier = Modifier.fillMaxSize(), state = listState) {
                    items(
                        count = uiState.photos.size,
                        key = { index -> uiState.photos[index].id },
                        itemContent = { index ->
                            photoItem(uiState.photos[index]) {
                                showSnackbar("$index 번 째 아이템 클릭")
                                val encoded = android.util.Base64.encodeToString(uiState.photos[index].url.toByteArray(), android.util.Base64.DEFAULT)
                                navigate("photo/${uiState.photos[index].title}/$encoded")
                            }
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun photoItem(item: Photo, onClick: () -> Unit) {
    val backgroundColor = MaterialTheme.colors.background
    val contentColor = contentColorFor(backgroundColor)
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .background(backgroundColor)
            .clickable {
                onClick.invoke()
            },
    ) {
        SubcomposeAsyncImage(
            modifier = Modifier.size(100.dp),
            model = item.thumbnailUrl,
            loading = {
                CircularProgressIndicator(modifier = Modifier.padding(25.dp))
            },
            contentDescription = "thumbnail"
        )
        Text(
            modifier = Modifier
                .padding(start = 100.dp)
                .padding(10.dp),
            text = item.title,
            color = contentColor
        )
    }
}