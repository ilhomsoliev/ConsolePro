package com.ilhomsoliev.consolepro.app.di

import com.ilhomsoliev.consolepro.presentation.home.viewmodel.HomeViewModel
import com.ilhomsoliev.consolepro.presentation.page.viewmodel.PageDetailsViewModel
import com.ilhomsoliev.consolepro.presentation.search.viewmodel.SearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { HomeViewModel(get(), get()) }
    viewModel { PageDetailsViewModel(get(), get()) }
    viewModel { SearchViewModel(get()) }
}