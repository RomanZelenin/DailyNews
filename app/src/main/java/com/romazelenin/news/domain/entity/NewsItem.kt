package com.romazelenin.news.domain.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.net.URL
import java.util.*

@Entity
data class NewsItem(
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "publishedAt") val publishedAt: Date,
    @ColumnInfo(name = "source") val source: String? = null,
    @ColumnInfo(name = "urlToImage") val imgUrl: URL? = null,
    @ColumnInfo(name = "url") val url: URL,
    @PrimaryKey val id: Int = (((title + imgUrl?.toString()) ?: "")).hashCode(),
)
