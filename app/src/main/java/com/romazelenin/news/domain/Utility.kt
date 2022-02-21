package com.romazelenin.news.domain

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import coil.compose.rememberImagePainter
import coil.size.Scale
import com.romazelenin.news.R
import java.net.URL
import java.text.DateFormat
import java.util.*

fun Date.formatToString(locale: Locale): String {
    val dateFormatter = DateFormat.getDateInstance(DateFormat.DEFAULT, locale)
    return dateFormatter.format(this)
}

@Composable
fun URL?.loadImage(): Painter? {
    return this?.let {
        rememberImagePainter(data = toString(),
            builder = {
                crossfade(true)
                scale(Scale.FILL)
            })
    }
}