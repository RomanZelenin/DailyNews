package com.romazelenin.news.data.datasource.remote

import com.romazelenin.news.data.entity.toNewsItem
import com.romazelenin.news.domain.ApiNewsService
import com.romazelenin.news.domain.entity.AppState
import com.romazelenin.news.domain.ArticleCategory
import com.romazelenin.news.domain.Country
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class ImplApiNewsService @Inject constructor() : ApiNewsService {
    override fun getMostPopularArticles(
        query: String,
        category: ArticleCategory,
        country: Country
    ): Flow<AppState> {
        return flow {
            emit(AppState.Loading)
            val articles = getArticles(query, category, country)
            emit(articles)
        }.catch { e -> emit(AppState.Error(throwable = e, e.message)) }
    }

    private suspend fun getArticles(
        query: String,
        category: ArticleCategory,
        country: Country
    ): AppState {
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
        return result
    }
}