package com.romazelenin.news

import android.content.Context
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
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.edit
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.romazelenin.news.domain.Repository
import com.romazelenin.news.ui.screens.listnews.ListNewsScreen
import com.romazelenin.news.ui.screens.listnews.NewsScreenViewModel
import com.romazelenin.news.ui.theme.NewsTheme
import javax.inject.Inject

class MainActivity : ComponentActivity() {

    @Inject
    lateinit var repository: Repository

    val vm by viewModels<NewsScreenViewModel> {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return NewsScreenViewModel(repository) as T
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (application as NewsApplication).appComponent.inject(this)

        setContent {
            val currentThemeIsDark =
                LocalContext.current.getSharedPreferences("settings", Context.MODE_PRIVATE)
                    .getBoolean("darkTheme", isSystemInDarkTheme())
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
                        onClickChangeTheme = {
                            isDarkTheme.value = !isDarkTheme.value
                            applicationContext.getSharedPreferences(
                                "settings",
                                Context.MODE_PRIVATE
                            ).edit {
                                putBoolean("darkTheme", isDarkTheme.value)
                            }
                        })
                }
            }

        }
    }
}

