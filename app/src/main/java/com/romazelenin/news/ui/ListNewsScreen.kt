package com.romazelenin.news.ui

import android.content.res.Configuration
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.romazelenin.news.R
import com.romazelenin.news.domain.entity.NewsItem
import com.romazelenin.news.domain.formatToString
import com.romazelenin.news.ui.theme.NewsTheme
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListNewsScreen(modifier: Modifier = Modifier, listNews: List<NewsItem>) {
    Scaffold(modifier = modifier,
        topBar = {
            CenterAlignedTopAppBar(title = {
                Text(
                    text = "News",
                    style = MaterialTheme.typography.titleLarge.copy(fontFamily = FontFamily.Serif)
                )
            }, actions = {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(imageVector = Icons.Default.Search, contentDescription = null)
                }
            })
        }) {
        LazyColumn(modifier = modifier.padding(horizontal = 16.dp)) {
            itemsIndexed(listNews) { index, newsItem ->
                NewsCard(
                    modifier = Modifier.border(
                        width = 1.dp,
                        color = Color.LightGray,
                        RoundedCornerShape(5.dp)
                    ),
                    title = newsItem.title,
                    description = newsItem.description,
                    publishedAt = newsItem.publishedAt.formatToString(),
                    image = newsItem.imgUrl?.let { rememberImagePainter(data = it.toString()) }
                        ?: painterResource(id = R.drawable.test_news_image),
                    source = newsItem.source
                )
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO or Configuration.UI_MODE_TYPE_NORMAL
)
@Composable
fun ListNewsScreenPreview() {
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
        ListNewsScreen(listNews = listNews)
    }
}

@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL
)
@Composable
fun ListScreenDarkPreview() {
    ListNewsScreenPreview()
}