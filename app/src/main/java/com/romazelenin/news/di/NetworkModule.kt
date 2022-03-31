package com.romazelenin.news.di

import com.romazelenin.news.data.datasource.remote.ImplApiNewsService
import com.romazelenin.news.domain.ApiNewsService
import dagger.Binds
import dagger.Module

@Module
abstract class NetworkModule {

    @ApplicationScope
    @Binds
    abstract fun provideApiNewsService(apiNewsService: ImplApiNewsService): ApiNewsService
}