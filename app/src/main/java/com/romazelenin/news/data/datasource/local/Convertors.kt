package com.romazelenin.news.data.datasource.local

import androidx.room.TypeConverter
import java.net.URL
import java.util.*

class Convertors {

    @TypeConverter
    fun stringToDate(time: Long): Date {
        return Date(time)
    }

    @TypeConverter
    fun dateToString(date: Date): Long {
        return date.time
    }

    @TypeConverter
    fun urlToString(url: URL?): String? {
        return url?.toString()
    }

    @TypeConverter
    fun stringToUrl(url: String?): URL? {
        return if (url != null) URL(url) else null
    }

}