package com.osinit.internship.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.osinit.internship.database.entity.CurrencyInfoEntity
import kotlinx.coroutines.flow.Flow
import java.util.*

@Dao
interface CurrencyInfoDao {

    @Query("SELECT * FROM CurrencyInfoEntity WHERE date=:date")
    fun getCurrencies(date: Date): Flow<List<CurrencyInfoEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCurrencies(currencies: List<CurrencyInfoEntity>)
}