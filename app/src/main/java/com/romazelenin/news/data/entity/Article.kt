package com.romazelenin.news.data.entity

import android.util.Log
import com.romazelenin.news.domain.entity.NewsItem
import java.net.URL
import java.text.SimpleDateFormat

data class Article(
    val source: Source,
    val author: String? = null,
    val title: String?,
    val description: String? = null,
    val uri: String,
    val urlToImage: String?,
    val publishedAt: String, //"2022-02-19T18:17:00Z" UTC (+000)
    val content: String
)

fun Article.toNewsItem(): NewsItem {
    val formatter = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
    Log.d("!!", this.toString())
    return NewsItem(
        title = title ?: "",
        description = description ?: "",
        publishedAt = formatter.parse(publishedAt)!!,
        source = source.name,
        imgUrl = if (urlToImage != null) URL(urlToImage) else null
    )
}