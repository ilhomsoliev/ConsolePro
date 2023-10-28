package com.ilhomsoliev.consolepro.app.di

import android.content.Context
import com.ilhomsoliev.consolepro.data.DataStoreManager
import com.ilhomsoliev.consolepro.data.local.ApplicationDatabase
import com.ilhomsoliev.consolepro.data.local.getDatabaseInstance
import com.ilhomsoliev.consolepro.data.sync.PageSynchronizer
import com.ilhomsoliev.consolepro.domain.DownloadPages
import com.ilhomsoliev.consolepro.domain.ExtractPages
import com.ilhomsoliev.consolepro.domain.MarkdownParser
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.BrowserUserAgent
import okio.FileSystem
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

fun appModule(context: Context) = module {
    single<ApplicationDatabase> { getDatabaseInstance(androidContext()) }
    dataStore()
    single { DownloadPages(get(), get()) }
    single<FileSystem> { FileSystem.SYSTEM }
    single { HttpClient(OkHttp) { BrowserUserAgent() } }
    single { MarkdownParser() }

    single { get<ApplicationDatabase>().pageDao() }
    single { PageSynchronizer(get(), get(), get(), get()) }
    single { ExtractPages(get()) }

}

fun dataStore() = module {
    single { DataStoreManager(androidContext()) }
}