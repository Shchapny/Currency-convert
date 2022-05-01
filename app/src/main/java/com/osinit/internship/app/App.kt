package com.osinit.internship.app

import android.app.Application
import com.osinit.internship.di.appModule
import com.osinit.internship.di.databaseModule
import com.osinit.internship.di.networkModule
import com.osinit.internship.di.repositoryModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(appModule, networkModule, repositoryModule, databaseModule)
        }
    }
}