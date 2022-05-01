package com.osinit.internship.di

import com.osinit.internship.api.CurrencyApi
import com.osinit.internship.database.dao.CurrencyInfoDao
import com.osinit.internship.repository.CurrencyRepository
import com.osinit.internship.repository.CurrencyRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {

    single { provideCurrencyRepositoryImpl(currencyApi = get(), currencyInfoDao = get()) }
}

fun provideCurrencyRepositoryImpl(
    currencyApi: CurrencyApi,
    currencyInfoDao: CurrencyInfoDao
): CurrencyRepository {
    return CurrencyRepositoryImpl(currencyApi, currencyInfoDao)
}