package com.osinit.internship.repository

import com.osinit.internship.data.CurrencyInfo
import kotlinx.coroutines.flow.Flow
import java.util.*

interface CurrencyRepository {

    suspend fun updateCurrencies(date: Date)

    fun getCurrencies(date: Date): Flow<List<CurrencyInfo>>
}