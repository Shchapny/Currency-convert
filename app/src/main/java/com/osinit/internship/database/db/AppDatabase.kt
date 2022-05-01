package com.osinit.internship.database.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.osinit.internship.database.dao.CurrencyInfoDao
import com.osinit.internship.database.entity.CurrencyInfoEntity
import com.osinit.internship.database.typeconvert.DateTypeConvert

@Database(entities = [CurrencyInfoEntity::class], version = 1)
@TypeConverters(DateTypeConvert::class)
abstract class AppDatabase: RoomDatabase() {

    abstract fun currencyInfoDao(): CurrencyInfoDao
}