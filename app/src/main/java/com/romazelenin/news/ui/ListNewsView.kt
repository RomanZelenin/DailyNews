package com.romazelenin.news.ui

import android.content.res.Configuration
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import com.romazelenin.news.domain.entity.NewsItem
import com.romazelenin.news.domain.formatToString
import com.romazelenin.news.domain.loadImage
import com.romazelenin.news.ui.theme.NewsTheme
import java.util.*

@OptIn(ExperimentalCoilApi::class)
@Composable
fun ListNews(modifier: Modifier = Modifier, news: List<NewsItem>) {
    LazyColumn(modifier = modifier.padding(horizontal = 16.dp)) {
        item { Spacer(modifier = Modifier.height(16.dp)) }
        itemsIndexed(news) { index, newsItem ->
            NewsCard(
                modifier = Modifier,
                title = newsItem.title,
                description = newsItem.description,
                publishedAt = newsItem.publishedAt.formatToString(LocalConfiguration.current.locales[0]),
                image = newsItem.imgUrl.loadImage(),
                source = newsItem.source
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO or Configuration.UI_MODE_TYPE_NORMAL
)
@Composable
fun ListNewsPreview() {
    val listNews = listOf<NewsItem>(
        NewsItem(
            title = "Uncharted’ Mining \$45M 4-Day, ‘Dog’ Laps Up \$14M At Presidents Day Weekend Box Office – Saturday Update - Deadline",
            description = "SATURDAY AM: See, streamers, people do like to go to the movies: Sony’s Uncharted is overperforming past its mid \$30M projections over 4-days with a \$45M take. Even though the movie stars Spider-Man: No Way Home‘s Tom Holland, this videogame adaptation was",
            publishedAt = Date(),
            source = "Deadline",
            imgUrl = null
        ),
        NewsItem(
            title = "Uncharted’ Mining \$45M 4-Day, ‘Dog’ Laps Up \$14M At Presidents Day Weekend Box Office – Saturday Update - Deadline",
            description = "SATURDAY AM: See, streamers, people do like to go to the movies: Sony’s Uncharted is overperforming past its mid \$30M projections over 4-days with a \$45M take. Even though the movie stars Spider-Man: No Way Home‘s Tom Holland, this videogame adaptation was",
            publishedAt = Date(),
            source = "Deadline",
            imgUrl = null
        ),
    )
    NewsTheme {
        ListNews(news = listNews)
    }
}


@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL
)
@Composable
fun ListNewsDarkPreview() {
    ListNewsPreview()
}