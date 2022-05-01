package com.osinit.internship.di

import com.osinit.internship.data.CurrencyInfo
import com.osinit.internship.viewmodel.CurrenciesDetailsViewModel
import com.osinit.internship.viewmodel.CurrenciesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    viewModel { CurrenciesViewModel(database = get()) }

    viewModel { (currencyInfo: CurrencyInfo) ->
        CurrenciesDetailsViewModel(currencyInfo = currencyInfo)
    }
}