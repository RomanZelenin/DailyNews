package com.romazelenin.news

import android.app.Application
import com.romazelenin.news.di.DaggerAppComponent

class NewsApplication : Application() {

    val appComponent = DaggerAppComponent.factory().create(this)

}