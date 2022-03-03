package com.romazelenin.news.data.entity

import com.romazelenin.news.domain.entity.NewsItem
import java.net.URL
import java.text.SimpleDateFormat


data class Article(
    val source: Source,
    val author: String? = null,
    val title: String? = null,
    val description: String? = null,
    val url: String,
    val urlToImage: String? = null,
    val publishedAt: String, //"2022-02-19T18:17:00Z" UTC (+000)
    val content: String
)

fun Article.toNewsItem(): NewsItem {
    val formatter = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
    return NewsItem(
        title = title ?: "",
        description = description ?: "",
        publishedAt = formatter.parse(publishedAt)!!,
        source = source.name,
        imgUrl = if (urlToImage != null) URL(urlToImage) else null,
        url = URL(url)
    )
}