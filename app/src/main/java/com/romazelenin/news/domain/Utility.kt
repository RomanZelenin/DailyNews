package com.romazelenin.news.domain

import android.content.Context
import android.net.Uri
import androidx.browser.customtabs.CustomTabColorSchemeParams
import androidx.browser.customtabs.CustomTabsIntent
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.toArgb
import coil.compose.rememberImagePainter
import coil.size.Scale
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


fun URL.launchInAppBrowser(context: Context, toolbarColor: Color) {
    val colorSchemeParams =
        CustomTabColorSchemeParams.Builder().setToolbarColor(toolbarColor.toArgb())
            .build()
    val customTabsIntent =
        CustomTabsIntent.Builder().setDefaultColorSchemeParams(colorSchemeParams)
            .build()
    customTabsIntent.launchUrl(context, Uri.parse(this.toString()))
}