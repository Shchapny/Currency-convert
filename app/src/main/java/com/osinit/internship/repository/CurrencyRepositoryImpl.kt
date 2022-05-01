package com.osinit.internship.repository

import com.osinit.internship.api.CurrencyApi
import com.osinit.internship.data.CurrencyInfo
import com.osinit.internship.database.dao.CurrencyInfoDao
import com.osinit.internship.util.apiDate
import com.osinit.internship.util.toCurrencyInfo
import com.osinit.internship.util.toCurrencyInfoEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.util.*

class CurrencyRepositoryImpl(
    private val currencyApi: CurrencyApi,
    private val currencyInfoDao: CurrencyInfoDao
) : CurrencyRepository {

    override suspend fun updateCurrencies(date: Date) {
        currencyApi.getCurrencies(date.apiDate()).apply {
            currencyInfoDao.insertCurrencies(
                this.currencies.values.toList().toCurrencyInfoEntity(date)
            )
        }
    }

    override fun getCurrencies(date: Date): Flow<List<CurrencyInfo>> {
        return currencyInfoDao.getCurrencies(date).map { it.toCurrencyInfo() }
    }
}