package com.osinit.internship.database.entity

import androidx.room.Entity
import com.osinit.internship.data.CurrencyInfo
import java.util.*

@Entity(primaryKeys = ["date", "charCode"])
data class CurrencyInfoEntity(
    val id: String,
    val numCode: String,
    val charCode: String,
    val nominal: Int,
    val name: String,
    val value: Double,
    val previous: Double,
    val date: Date
) {
    companion object {
        fun fromCurrencyInfo(currencyInfo: CurrencyInfo, date: Date): CurrencyInfoEntity = with(currencyInfo) {
            CurrencyInfoEntity(id, numCode, charCode, nominal, name, value, previous, date = date)
        }
    }

    fun currencyInfo(): CurrencyInfo = with(this) {
        CurrencyInfo(id, numCode, charCode, nominal, name, value, previous)
    }
}