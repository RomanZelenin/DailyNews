package com.romazelenin.news.di

import android.content.Context
import com.romazelenin.news.MainActivity
import dagger.BindsInstance
import dagger.Component
import javax.inject.Scope

@Scope
@Retention
annotation class ApplicationScope

@ApplicationScope
@Component(modules = [NetworkModule::class, RepositoryModule::class])
abstract class AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }

    abstract fun inject(mainActivity: MainActivity)
}