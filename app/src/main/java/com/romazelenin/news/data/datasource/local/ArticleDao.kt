package com.romazelenin.news.data.datasource.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.romazelenin.news.domain.entity.NewsItem
import kotlinx.coroutines.flow.Flow

@Dao
interface ArticleDao {

    @Insert(onConflict = REPLACE)
    suspend fun insertArticles(articles: List<NewsItem>)

    @Query("Delete From NewsItem")
    suspend fun deleteAllArticles()

    @Query("Select * From NewsItem")
    fun getAllArticles(): Flow<List<NewsItem>>
}