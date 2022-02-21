package com.romazelenin.news.data

import com.romazelenin.news.data.entity.toNewsItem
import com.romazelenin.news.domain.ApiNewsService
import com.romazelenin.news.domain.entity.AppState
import com.romazelenin.news.domain.ArticleCategory
import com.romazelenin.news.domain.Country
import kotlinx.coroutines.flow.*

class ImplApiNewsService : ApiNewsService {
    override fun getMostPopularArticles(
        query: String,
        category: ArticleCategory,
        country: Country
    ): Flow<AppState> {
        return flow {
            val response = ApiService.INSTANCE.getTopHeadlines(query, category, country)
            val status = response.status.split(" ")
            val statusCode = status[0]
            val result = when (ApiService.Status.valueOf(statusCode)) {
                ApiService.Status.ok -> {
                    val listNews = response.articles.map { it.toNewsItem() }
                    AppState.Success(listNews)
                }
                ApiService.Status.error -> {
                    AppState.Error(throwable = Throwable(message = status[1]), data = null)
                }
            }
            emit(result)
        }.onStart { emit(AppState.Loading) }
            .catch { e -> emit(AppState.Error(throwable = e, e.message)) }

    }
}