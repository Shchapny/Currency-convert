package com.osinit.internship.util

import com.osinit.internship.data.CurrencyInfo
import com.osinit.internship.database.entity.CurrencyInfoEntity
import java.util.*

fun List<CurrencyInfoEntity>.toCurrencyInfo() = map { it.currencyInfo() }

fun List<CurrencyInfo>.toCurrencyInfoEntity(date: Date) = map { CurrencyInfoEntity.fromCurrencyInfo(it, date) }