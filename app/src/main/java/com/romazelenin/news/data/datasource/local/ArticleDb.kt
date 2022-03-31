package com.romazelenin.news.data.datasource.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.romazelenin.news.domain.entity.NewsItem

@Database(entities = [NewsItem::class], version = 1)
@TypeConverters(Convertors::class)
abstract class ArticleDb : RoomDatabase() {
    abstract fun getArticleDao(): ArticleDao
}