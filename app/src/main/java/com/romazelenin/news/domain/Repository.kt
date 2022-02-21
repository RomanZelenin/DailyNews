package com.romazelenin.news.domain

import com.romazelenin.news.domain.entity.AppState
import kotlinx.coroutines.flow.Flow

interface Repository {

    fun getNews(query: String, category: ArticleCategory, country: Country): Flow<AppState>

}