package com.gb.android.lesson2d

import android.app.Application
import com.gb.android.lesson2d.di.networkModule
import com.gb.android.lesson2d.di.repoModule
import com.gb.android.lesson2d.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level


class AppClass : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@AppClass)
            androidLogger(if (BuildConfig.DEBUG) Level.ERROR else Level.NONE)
            modules(networkModule, repoModule, viewModelModule)
        }
    }
}
