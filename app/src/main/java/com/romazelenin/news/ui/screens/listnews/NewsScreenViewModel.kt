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

    private val _query = MutableStateFlow("")
    private val _category = MutableStateFlow(ArticleCategory.GENERAL)
    private val _country = MutableStateFlow(Country.US)
    private val _listNewsState = MutableStateFlow<AppState>(AppState.Starting)
    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing = _isRefreshing.asStateFlow()

    init {
        viewModelScope.launch {
            _query.collectLatest {
                _isRefreshing.value = true
                repository.getNews(_query.value, _category.value, _country.value)
                    .collect { _listNewsState.value = it }
                _isRefreshing.value = false
            }
        }
    }

    fun refresh() {
        _isRefreshing.value = true
        val prevQuery = _query.value
        _query.value = " "
        _query.value = prevQuery
    }

    fun getNews(
        query: String,
        category: ArticleCategory = ArticleCategory.GENERAL,
        country: Country = Country.US
    ): Flow<AppState> {
        this._query.value = query
        this._category.value = category
        this._country.value = country
        return _listNewsState
    }

}
