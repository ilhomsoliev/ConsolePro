package com.ilhomsoliev.consolepro.app

import android.app.Application
import com.ilhomsoliev.consolepro.app.di.appModule
import com.ilhomsoliev.consolepro.app.di.repositoryModule
import com.ilhomsoliev.consolepro.app.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class ConsoleProApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@ConsoleProApplication)
            modules(
                listOf(
                    appModule(this@ConsoleProApplication),
                    repositoryModule,
                    viewModelModule,
                )
            )
        }
    }
}