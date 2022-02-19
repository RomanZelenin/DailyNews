package com.romazelenin.news.domain.entity

import java.net.URL
import java.util.*

data class NewsItem(
    val title: String,
    val description: String,
    val publishedAt: Date,
    val source: String? = null,
    val imgUrl: URL
)
