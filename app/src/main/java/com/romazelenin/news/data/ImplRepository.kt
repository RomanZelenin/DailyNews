package com.romazelenin.news.data

import com.romazelenin.news.domain.ApiNewsService
import com.romazelenin.news.domain.ArticleCategory
import com.romazelenin.news.domain.Country
import com.romazelenin.news.domain.Repository
import com.romazelenin.news.domain.entity.AppState
import kotlinx.coroutines.flow.Flow

class ImplRepository(private val apiService: ApiNewsService) : Repository {
    override fun getNews(query: String, category: ArticleCategory, country: Country): Flow<AppState> {
        return apiService.getMostPopularArticles(query, category, country)
    }
}