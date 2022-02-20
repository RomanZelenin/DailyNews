package com.romazelenin.news.domain

import com.romazelenin.news.domain.entity.NewsItem
import kotlinx.coroutines.flow.Flow

interface ApiNewsService {
    fun getMostPopularArticles(
        query: String,
        category: ArticleCategory = ArticleCategory.GENERAL,
        country: Country
    ): Flow<List<NewsItem>>
}

enum class ArticleCategory {
    BUSINESS, ENTERTAINMENT, GENERAL, HEALTH, SCIENCE, SPORTS, TECHNOLOGY;

    override fun toString(): String {
        return name.lowercase()
    }
}

enum class Country {
    RU, US;

    override fun toString(): String {
        return name.lowercase()
    }
}