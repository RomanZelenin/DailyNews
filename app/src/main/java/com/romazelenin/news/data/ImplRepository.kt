package com.romazelenin.news.data

import com.romazelenin.news.data.datasource.local.ArticleDao
import com.romazelenin.news.domain.ApiNewsService
import com.romazelenin.news.domain.ArticleCategory
import com.romazelenin.news.domain.Country
import com.romazelenin.news.domain.Repository
import com.romazelenin.news.domain.entity.AppState
import com.romazelenin.news.domain.entity.NewsItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class ImplRepository @Inject constructor(
    private val apiService: ApiNewsService,
    private val articleDao: ArticleDao
) :
    Repository {

    private val _articles = articleDao.getAllArticles().map { AppState.Success(it) }

    override fun getNews(
        query: String,
        category: ArticleCategory,
        country: Country
    ): Flow<AppState> {


        return apiService.getMostPopularArticles(query, category, country)
            .onEach {
                if (it is AppState.Success<*>) {
                    articleDao.insertArticles(it.data as List<NewsItem>)
                } else {

                }
            }


    }
}