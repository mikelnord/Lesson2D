package com.gb.android.lesson2d.di

import com.gb.android.lesson2d.data.Repository
import com.gb.android.lesson2d.data.remote.RemoteDataSource
import org.koin.dsl.module

val repoModule = module {
    single {
        RemoteDataSource(retrofit = get())
    }
    factory {
        Repository(remoteDataSource = get())
    }
}