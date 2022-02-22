package com.romazelenin.news

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.romazelenin.news.data.datasource.remote.ImplApiNewsService
import com.romazelenin.news.data.ImplRepository
import com.romazelenin.news.data.datasource.local.ArticleDb
import com.romazelenin.news.ui.screens.listnews.ListNewsScreen
import com.romazelenin.news.ui.screens.listnews.NewsScreenViewModel
import com.romazelenin.news.ui.theme.NewsTheme

class MainActivity : ComponentActivity() {

    val vm by viewModels<NewsScreenViewModel> {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                val articleDao =
                    Room.inMemoryDatabaseBuilder(applicationContext, ArticleDb::class.java).build()
                        .getArticleDao()
                return NewsScreenViewModel(ImplRepository(ImplApiNewsService(), articleDao)) as T
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val currentThemeIsDark = isSystemInDarkTheme()
            val isDarkTheme = rememberSaveable { mutableStateOf(currentThemeIsDark) }
            val uiMode =
                LocalConfiguration.current.uiMode and Configuration.UI_MODE_NIGHT_MASK.inv()
            if (!isDarkTheme.value) {
                LocalConfiguration.current.uiMode = (uiMode or Configuration.UI_MODE_NIGHT_NO)
            } else {
                LocalConfiguration.current.uiMode = (uiMode or Configuration.UI_MODE_NIGHT_YES)
            }
            CompositionLocalProvider(LocalConfiguration provides LocalConfiguration.current) {
                NewsTheme {
                    ListNewsScreen(
                        viewModel = vm,
                        onClickChangeTheme = { isDarkTheme.value = !isDarkTheme.value })
                }
            }

        }
    }
}

