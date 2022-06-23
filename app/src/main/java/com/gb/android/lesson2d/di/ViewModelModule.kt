package com.gb.android.lesson2d.di

import com.gb.android.lesson2d.ui.TranslationSearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        TranslationSearchViewModel(repository = get())
    }
}