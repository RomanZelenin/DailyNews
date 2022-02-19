package com.romazelenin.news.ui

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.romazelenin.news.R
import com.romazelenin.news.ui.theme.NewsTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsCard(
    modifier: Modifier = Modifier.border(
        width = 1.dp,
        color = Color.LightGray,
        RoundedCornerShape(5.dp)
    ),
    title: String,
    description: String,
    publishedAt: String,
    source: String? = null,
    image: Painter
) {
    Card(
        modifier = modifier
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(bottomEnd = 8.dp, bottomStart = 8.dp)),
                painter = image,
                contentScale = ContentScale.FillBounds,
                contentDescription = null
            )
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = title,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.titleLarge
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = description, maxLines = 4, overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.bodyLarge
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.End,
                    text = publishedAt,
                    style = MaterialTheme.typography.labelSmall
                )
                source?.let {
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.End,
                        text = it,
                        style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold)
                    )
                }
            }
        }
    }
}


@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO or Configuration.UI_MODE_TYPE_NORMAL
)
@Composable
fun NewsCardPreview() {
    NewsTheme {
        NewsCard(
            title = "Uncharted’ Mining \$45M 4-Day, ‘Dog’ Laps Up \$14M At Presidents Day Weekend Box Office – Saturday Update - Deadline",
            description = "SATURDAY AM: See, streamers, people do like to go to the movies: Sony’s Uncharted is overperforming past its mid \$30M projections over 4-days with a \$45M take. Even though the movie stars Spider-Man: No Way Home‘s Tom Holland, this videogame adaptation was",
            image = painterResource(id = R.drawable.test_news_image),
            publishedAt = "01.05.2022",
            source = "Deadline"
        )
    }
}

@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL
)
@Composable
fun NewsCardDarkPreview() {
    NewsCardPreview()
}