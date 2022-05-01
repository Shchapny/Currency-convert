package com.osinit.internship.di

import android.content.Context
import androidx.room.Room
import com.osinit.internship.database.db.AppDatabase
import org.koin.dsl.module

val databaseModule = module {

    single { provideDb(context = get()) }
    single { provideDao(database = get()) }
}

fun provideDb(context: Context): AppDatabase {
    return Room.databaseBuilder(context, AppDatabase::class.java, "internship_db")
        .build()
}

fun provideDao(database: AppDatabase) = database.currencyInfoDao()