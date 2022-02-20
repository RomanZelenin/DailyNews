package com.romazelenin.news.domain

import java.text.DateFormat
import java.util.*

fun Date.formatToString(locale: Locale): String {
    val dateFormatter = DateFormat.getDateInstance(DateFormat.DEFAULT, locale)
    return dateFormatter.format(this)
}