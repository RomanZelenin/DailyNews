package com.romazelenin.news.ui.screens.listnews

import android.content.res.Configuration
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import com.romazelenin.news.domain.entity.AppState
import com.romazelenin.news.domain.entity.NewsItem
import com.romazelenin.news.ui.ListNews
import com.romazelenin.news.ui.theme.NewsTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListNewsScreen(
    modifier: Modifier = Modifier,
    viewModel: NewsScreenViewModel
) {
    var query by rememberSaveable { mutableStateOf("") }

    Scaffold(modifier = modifier,
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
            })
        }) {
        val listNewsState by viewModel.getNews(query = query)
            .collectAsState(initial = AppState.Loading)

        when (listNewsState) {
            is AppState.Error<*> -> {}
            AppState.Loading -> {}
            is AppState.Success<*> -> {
                ListNews(news = (listNewsState as AppState.Success<List<NewsItem>>).data)
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