package com.romazelenin.news.di

import android.content.Context
import androidx.room.Room
import com.romazelenin.news.data.ImplRepository
import com.romazelenin.news.data.datasource.local.ArticleDao
import com.romazelenin.news.data.datasource.local.ArticleDb
import com.romazelenin.news.domain.ApiNewsService
import com.romazelenin.news.domain.Repository
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {

    @Provides
    fun provideArticleDao(context: Context): ArticleDao {
        return Room.inMemoryDatabaseBuilder(context, ArticleDb::class.java).build()
            .getArticleDao()
    }

    @ApplicationScope
    @Provides
    fun provideRepository(apiNewsService: ApiNewsService, articleDao: ArticleDao): Repository {
       return ImplRepository(apiNewsService, articleDao)
    }
}