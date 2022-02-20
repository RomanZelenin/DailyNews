package com.romazelenin.news.data

import com.romazelenin.news.data.entity.toNewsItem
import com.romazelenin.news.domain.ApiNewsService
import com.romazelenin.news.domain.AppState
import com.romazelenin.news.domain.ArticleCategory
import com.romazelenin.news.domain.Country
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class ImplApiNewsService : ApiNewsService {
    override fun getMostPopularArticles(
        query: String,
        category: ArticleCategory,
        country: Country
    ): Flow<AppState> {
        return flow { emit(ApiService.INSTANCE.getTopHeadlines(query, category, country)) }
            .map { response ->
                val status = response.status.split(" ")
                val statusCode = status[0]
                when (ApiService.Status.valueOf(statusCode)) {
                    ApiService.Status.ok -> {
                        val listNews = response.articles.map { it.toNewsItem() }
                        AppState.Success(listNews)
                    }
                    ApiService.Status.error -> {
                        AppState.Error(throwable = Throwable(message = status[1]), data = null)
                    }
                }
            }
    }
}