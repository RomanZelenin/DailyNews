package com.romazelenin.news.ui.screens.listnews

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.romazelenin.news.domain.ArticleCategory
import com.romazelenin.news.domain.Country
import com.romazelenin.news.domain.Repository
import com.romazelenin.news.domain.entity.AppState
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class NewsScreenViewModel(private val repository: Repository) : ViewModel() {

    private val query = MutableStateFlow("")
    private val category = MutableStateFlow(ArticleCategory.GENERAL)
    private val country = MutableStateFlow(Country.US)

    val listNews = MutableStateFlow<AppState>(AppState.Loading)

    init {
        viewModelScope.launch {
            query.collectLatest {
                repository.getNews(query.value, category.value, country.value).collect {
                    listNews.value = it
                }
            }
        }
    }

    fun getNews(
        query: String,
        category: ArticleCategory = ArticleCategory.GENERAL,
        country: Country = Country.US
    ): Flow<AppState> {
        this.query.value = query
        this.category.value = category
        this.country.value = country
        return listNews
    }

}
