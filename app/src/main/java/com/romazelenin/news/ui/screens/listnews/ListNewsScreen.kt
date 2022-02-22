package com.romazelenin.news.ui.screens.listnews

import android.content.res.Configuration
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.romazelenin.news.R
import com.romazelenin.news.domain.entity.AppState
import com.romazelenin.news.domain.entity.NewsItem
import com.romazelenin.news.ui.ListNews
import com.romazelenin.news.ui.theme.NewsTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListNewsScreen(
    modifier: Modifier = Modifier,
    viewModel: NewsScreenViewModel,
    onClickChangeTheme: () -> Unit = {},
) {
    var query by rememberSaveable { mutableStateOf("") }
    val snackbarHostState = remember { SnackbarHostState() }


    Scaffold(modifier = modifier,
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        topBar = {
            CenterAlignedTopAppBar(title = {
                Text(
                    text = "News",
                    style = MaterialTheme.typography.titleLarge.copy(fontFamily = FontFamily.Serif)
                )
            }, actions = {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(imageVector = Icons.Default.Search, contentDescription = null)
                }
                IconButton(onClick = onClickChangeTheme) {
                    val iconId = if (isSystemInDarkTheme()) R.drawable.ic_outline_wb_sunny_24 else
                        R.drawable.ic_baseline_nights_stay_24
                    Icon(painter = painterResource(id = iconId), contentDescription = null)
                }
            })
        }) {

        val listNewsState by viewModel.getNews(query = query)
            .collectAsState(initial = AppState.Starting)
        val isRefreshing by viewModel.isRefreshing.collectAsState()

        SwipeRefresh(
            state = rememberSwipeRefreshState(isRefreshing = isRefreshing),
            onRefresh = { viewModel.refresh() }) {
            when (listNewsState) {
                AppState.Starting -> {}
                AppState.Loading -> {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .verticalScroll(rememberScrollState())
                    )
                }
                is AppState.Success<*> -> {
                    ListNews(news = (listNewsState as AppState.Success<List<NewsItem>>).data)
                }
                is AppState.Error<*> -> {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .verticalScroll(rememberScrollState())
                    )
                    LaunchedEffect(true) {
                        snackbarHostState.showSnackbar("Error loading news")
                    }
                }
            }
        }
    }
}

@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO or Configuration.UI_MODE_TYPE_NORMAL
)
@Composable
fun ListNewsScreenPreview() {
    NewsTheme {
        //ListNewsScreen()
    }
}

@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL
)
@Composable
fun ListScreenDarkPreview() {
    ListNewsScreenPreview()
}